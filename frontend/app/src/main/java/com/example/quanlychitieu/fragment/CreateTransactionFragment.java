package com.example.quanlychitieu.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.ChooseCategoryTypeActivity;
import com.example.quanlychitieu.activities.ChooseWalletActivity;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.models.Wallet;

import org.parceler.Parcels;

public class CreateTransactionFragment extends Fragment implements ActivityResultCaller {
    private static final int REQUEST_CODE_SELECT_CATEGORY = 1;
    private static final int REQUEST_CODE_SELECT_WALLET = 2;
    private static final int REQUEST_PICK_IMAGE = 102;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    SharedPreferences sharedPreferences;

    LinearLayout calendarView, linearLayoutCreateTransactionCategoryType, linearLayoutCreateTransactionWallet;
    TextView txtCalendarDateTime, txtTimerDate, createTransactionCategoryTypeName, createTransactionWalletName;
    ImageView createTransactionCategoryTypeImage, createTransactionWalletImage, pictureImg;
    Button btnSave;
    //------------------------------------
    CategoryType categoryType;
    Wallet wallet;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setTitle(R.string.transaction);
        }
        sharedPreferences = getActivity().getSharedPreferences("passingCategoryType", Context.MODE_PRIVATE);
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
        btnSave = view.findViewById(R.id.btnSave);
        txtCalendarDateTime = view.findViewById(R.id.dateTimeCalendar);
        linearLayoutCreateTransactionCategoryType = view.findViewById(R.id.linearLayoutCreateTransactionCategoryType);
        linearLayoutCreateTransactionWallet = view.findViewById(R.id.linearLayoutCreateTransactionWallet);

        createTransactionCategoryTypeImage = view.findViewById(R.id.createTransactionCategoryTypeImage);
        createTransactionCategoryTypeName = view.findViewById(R.id.createTransactionCategoryTypeName);
        createTransactionWalletImage = view.findViewById(R.id.createTransactionWalletImage);
        createTransactionWalletName = view.findViewById(R.id.createTransactionWalletName);
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
        inflater.inflate(R.menu.menu_save, menu);
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
}
