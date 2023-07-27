package com.example.quanlychitieu.fragment;

import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.app.Activity;
import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.ChooseCategoryTypeActivity;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.spinners.CustomSpinnerExpense;
import com.example.quanlychitieu.utils.PassData;

import org.parceler.Parcels;

public class CreateTransactionFragment extends Fragment implements ActivityResultCaller {
    private static final int REQUEST_CODE_SELECT_CATEGORY = 1;
    LinearLayout calendarView, linearLayoutCreateTransactionCategoryType;

    TextView txtCalendarDate;

    TextView txtTimerDate;

    ImageView pictureImg;

    private static final int REQUEST_PICK_IMAGE = 102;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;


    TextView createTransactionCategoryTypeName;
    ImageView createTransactionCategoryTypeImage;
    SharedPreferences sharedPreferences;
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

        pictureImg = view.findViewById(R.id.choosePicture);

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                Uri selectedImageUri = data.getData();
                                if (selectedImageUri != null) {
                                    ImageView imageView = view.findViewById(R.id.imageView2);
                                    imageView.setImageURI(selectedImageUri);
                                }
                            }
                        }
                    }
                });
        pictureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        return view;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        someActivityResultLauncher.launch(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        initializeElement(view);
        handleShowCalendar();
        handleSwitchToChooseCategoryTypeActivity();
//        handleShowDataToUI();
    }

    private void handleShowDataToUI() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.app_icon_background)
                .error(R.drawable.app_icon_background);

        Glide.with(requireActivity()).load(sharedPreferences.getString("imageLink", ""))
                .apply(requestOptions).diskCacheStrategy(DiskCacheStrategy.ALL).into(createTransactionCategoryTypeImage);

        createTransactionCategoryTypeName.setText(sharedPreferences.getString("name", ""));
    }

    private void handleSwitchToChooseCategoryTypeActivity() {
        linearLayoutCreateTransactionCategoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChooseCategoryTypeActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_SELECT_CATEGORY);
            }
        });
    }

    private void handleShowCalendar() {
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar();
            }
        });
    }

    private void initializeElement(View view) {
        txtCalendarDate = view.findViewById(R.id.calendarDate);
        txtTimerDate = view.findViewById(R.id.timerDate);
        calendarView = view.findViewById(R.id.calendar);
        linearLayoutCreateTransactionCategoryType = view.findViewById(R.id.linearLayoutCreateTransactionCategoryType);

        createTransactionCategoryTypeImage = view.findViewById(R.id.createTransactionCategoryTypeImage);
        createTransactionCategoryTypeName = view.findViewById(R.id.createTransactionCategoryTypeName);
    }


    public void showCalendar() {
        CalendarFragment calendarDialogFragment = new CalendarFragment();
        calendarDialogFragment.show(getChildFragmentManager(), "calendar_dialog");
    }

    public void setDateTime(String selectedDate, String selectedTime) {
        txtCalendarDate.setText(selectedDate);
        txtTimerDate.setText(selectedTime);
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
            Parcelable parcelable = data.getParcelableExtra("categoryType");
            CategoryType categoryType = Parcels.unwrap(parcelable);

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.app_icon_background)
                    .error(R.drawable.app_icon_background);

            Glide.with(requireActivity()).load(categoryType.getImageLink())
                    .apply(requestOptions).diskCacheStrategy(DiskCacheStrategy.ALL).into(createTransactionCategoryTypeImage);

            createTransactionCategoryTypeName.setText(categoryType.getName());
        }
    }
}
