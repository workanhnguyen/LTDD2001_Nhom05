package com.example.quanlychitieu.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.ChooseCategoryTypeActivity;
import com.example.quanlychitieu.activities.ChooseWalletActivity;
import com.example.quanlychitieu.dtos.TransactionDto;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.presenters.CreateTransactionPresenter;
import com.example.quanlychitieu.utils.CustomConstant;
import com.example.quanlychitieu.utils.DateUtil;
import com.example.quanlychitieu.views.CreateTransactionView;

import org.parceler.Parcels;

import java.text.ParseException;

public class CreateTransactionFragment extends Fragment implements ActivityResultCaller, CreateTransactionView {
    private static final int REQUEST_CODE_SELECT_CATEGORY = 1;
    private static final int REQUEST_CODE_SELECT_WALLET = 2;
    private static final int REQUEST_PICK_IMAGE = 102;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    SharedPreferences userPref;
    CreateTransactionPresenter createTransactionPresenter;
    CategoryType categoryType;
    Wallet wallet;
    // -------------------------------------------------------------------
    LinearLayout calendarView, linearLayoutCreateTransactionCategoryType, linearLayoutCreateTransactionWallet;
    TextView txtCalendarDateTime, txtTimerDate, createTransactionCategoryTypeName, createTransactionWalletName, createTransactionAlert;
    EditText createTransactionDescription, createTransactionBalance;
    ImageView createTransactionCategoryTypeImage, createTransactionWalletImage, pictureImg;
    Button btnCreateTransaction;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setTitle(R.string.create_transaction);
        }

        createTransactionPresenter = new CreateTransactionPresenter(this);
        userPref = getActivity().getSharedPreferences("loggingUser", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_transaction, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        initializeElement(view);
        handleShowCalendar();
        handleSwitchToChooseCategoryTypeActivity();
        handleSwitchToChooseWalletActivity();
        handleCreateTransaction();
    }

    private void handleCreateTransaction() {
        btnCreateTransaction.setOnClickListener(v -> {
            createTransactionAlert.setVisibility(View.GONE);

            String transactionBalance = createTransactionBalance.getText().toString().trim();
            String transactionDescription = createTransactionDescription.getText().toString().trim();
            String transactionCreatedDate = txtCalendarDateTime.getText().toString().trim();

            String alertString = validateTransactionInput(transactionBalance, transactionDescription, transactionCreatedDate, categoryType, wallet);

            if (alertString.trim().isEmpty()) {
                btnCreateTransaction.setEnabled(false);
                btnCreateTransaction.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.dark_grey)));
                btnCreateTransaction.setText(getString(R.string.creating));

                try {
                    TransactionDto transactionDto = new TransactionDto();
                    transactionDto.setTotal(Long.parseLong(transactionBalance));
                    transactionDto.setDescription(transactionDescription);
                    transactionDto.setImage("");
                    transactionDto.setWalletId(wallet.getId());
                    transactionDto.setCategoryTypeId(categoryType.getId());
                    transactionDto.setCreatedDate(DateUtil.convertDateToSeconds(DateUtil.parseStringToDate(transactionCreatedDate, CustomConstant.DATE_FORMAT_dd_MM_yyyy_hh_mm_a)));

                    createTransactionPresenter.addNewTransaction(transactionDto);
                } catch (ParseException e) {
                    createTransactionAlert.setVisibility(View.VISIBLE);
                    createTransactionAlert.setText(e.toString());

                    btnCreateTransaction.setEnabled(true);
                    btnCreateTransaction.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.primary)));
                    btnCreateTransaction.setText(getString(R.string.create_transaction));
                }
            } else {
                createTransactionAlert.setVisibility(View.VISIBLE);
                createTransactionAlert.setText(alertString);

                btnCreateTransaction.setEnabled(true);
                btnCreateTransaction.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.primary)));
                btnCreateTransaction.setText(getString(R.string.create_transaction));
            }
        });
    }

    private String validateTransactionInput(String transactionBalance, String transactionDescription, String transactionCreatedDate, CategoryType categoryType, Wallet wallet) {
        if (transactionBalance.isBlank() || transactionDescription.isBlank() || transactionCreatedDate.isBlank() || categoryType == null || wallet == null)
            return getString(R.string.not_empty_field);
        return "";
    }

    private void handleSwitchToChooseWalletActivity() {
        linearLayoutCreateTransactionWallet.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChooseWalletActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT_WALLET);
        });
    }

    private void handleSwitchToChooseCategoryTypeActivity() {
        linearLayoutCreateTransactionCategoryType.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChooseCategoryTypeActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT_CATEGORY);
        });
    }

    private void handleShowCalendar() {
        calendarView.setOnClickListener(v -> showCalendar());
    }

    private void initializeElement(View view) {
        calendarView = view.findViewById(R.id.calendarDateTime);
        pictureImg = view.findViewById(R.id.imgGallery);
        btnCreateTransaction = view.findViewById(R.id.btnCreateTransaction);
        txtCalendarDateTime = view.findViewById(R.id.dateTimeCalendar);
        linearLayoutCreateTransactionCategoryType = view.findViewById(R.id.linearLayoutCreateTransactionCategoryType);
        linearLayoutCreateTransactionWallet = view.findViewById(R.id.linearLayoutCreateTransactionWallet);

        createTransactionCategoryTypeImage = view.findViewById(R.id.createTransactionCategoryTypeImage);
        createTransactionCategoryTypeName = view.findViewById(R.id.createTransactionCategoryTypeName);
        createTransactionWalletImage = view.findViewById(R.id.createTransactionWalletImage);
        createTransactionWalletName = view.findViewById(R.id.createTransactionWalletName);
        createTransactionBalance = view.findViewById(R.id.createTransactionBalance);
        createTransactionDescription = view.findViewById(R.id.createTransactionDescription);
        createTransactionAlert = view.findViewById(R.id.createTransactionAlert);
    }


    public void showCalendar() {
        CalendarFragment calendarDialogFragment = new CalendarFragment();
        calendarDialogFragment.show(getChildFragmentManager(), "calendar_dialog");
    }

    public void setDateTime(String selectedDateTime) {
        txtCalendarDateTime.setText(selectedDateTime);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_only_title, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_CATEGORY && resultCode == RESULT_OK) {

            assert data != null;
            Parcelable parcelableCategoryType = data.getParcelableExtra("categoryType");
            categoryType = Parcels.unwrap(parcelableCategoryType);

            RequestOptions requestOptionsCategoryType = new RequestOptions()
                    .placeholder(R.drawable.app_icon_background)
                    .error(R.drawable.app_icon_background);

            Glide.with(requireActivity()).load(categoryType.getImageLink())
                    .apply(requestOptionsCategoryType).diskCacheStrategy(DiskCacheStrategy.ALL).into(createTransactionCategoryTypeImage);

            createTransactionCategoryTypeName.setText(categoryType.getName());
        } else if (requestCode == REQUEST_CODE_SELECT_WALLET && resultCode == RESULT_OK) {
            assert data != null;
            Parcelable parcelableWallet = data.getParcelableExtra("wallet");
            wallet = Parcels.unwrap(parcelableWallet);

            RequestOptions requestOptionsWallet = new RequestOptions()
                    .placeholder(R.drawable.app_icon_background)
                    .error(R.drawable.app_icon_background);

            Glide.with(requireActivity()).load(wallet.getImageLink())
                    .apply(requestOptionsWallet).diskCacheStrategy(DiskCacheStrategy.ALL).into(createTransactionWalletImage);

            createTransactionWalletName.setText(wallet.getName());
        }
    }

    @Override
    public void showAddedTransaction(Transaction transaction) {
        if (transaction != null) {
            createTransactionAlert.setVisibility(View.GONE);
            createTransactionAlert.setText("");

            createTransactionBalance.setText("");
            createTransactionDescription.setText("");
            txtCalendarDateTime.setText("");

            createTransactionCategoryTypeImage.setImageResource(R.drawable.category);
            createTransactionCategoryTypeName.setText(getString(R.string.choose_category_type));
            categoryType = null;

            createTransactionWalletImage.setImageResource(R.drawable.baseline_account_balance_wallet_24);
            createTransactionWalletName.setText(getString(R.string.choose_wallet));
            wallet = null;

            btnCreateTransaction.setText(getString(R.string.create_transaction));
            btnCreateTransaction.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.primary)));
            btnCreateTransaction.setEnabled(true);

            Toast.makeText(requireActivity(), getString(R.string.create_transaction_successful), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showAddingTransactionError() {
        createTransactionAlert.setVisibility(View.VISIBLE);
        createTransactionAlert.setText(getString(R.string.create_transaction_failed));
    }
}
