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
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.ChooseCategoryTypeActivity;
import com.example.quanlychitieu.models.CategoryType;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class CreateTransactionFragment extends Fragment implements ActivityResultCaller {
    private static final int REQUEST_CODE_SELECT_CATEGORY = 1;
    LinearLayout calendarView, linearLayoutCreateTransactionCategoryType;
    TextView txtCalendarDateTime, createTransactionCategoryTypeName;
    ImageView createTransactionCategoryTypeImage;
    SharedPreferences sharedPreferences;

    ImageView pictureImg, gallery, camera;

    Button btnSave, btnDeletePic;

    Uri uri;

    int SELECT_IMAGE_CODE = 1;

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
        handleOpenPicTureAndCamera();
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

    private void handleOpenPicTureAndCamera(){
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
            }
        });

        btnDeletePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pictureImg.setImageDrawable(null);
                uri = null;
            }
        });
    }

    private void initializeElement(View view) {
        calendarView = view.findViewById(R.id.calendarDateTime);
        pictureImg = view.findViewById(R.id.imageView2);
        gallery = view.findViewById(R.id.imgGallery);
        camera = view.findViewById(R.id.imgCamera);
        btnSave = view.findViewById(R.id.btnSave);
        btnDeletePic = view.findViewById(R.id.btnDeletePicture);
        txtCalendarDateTime = view.findViewById(R.id.dateTimeCalendar);
        linearLayoutCreateTransactionCategoryType = view.findViewById(R.id.linearLayoutCreateTransactionCategoryType);

        createTransactionCategoryTypeImage = view.findViewById(R.id.createTransactionCategoryTypeImage);
        createTransactionCategoryTypeName = view.findViewById(R.id.createTransactionCategoryTypeName);
    }


//    private void navigateToCategoriesFragment() {
//        CategoryTypeFragment categoryFragment = new CategoryTypeFragment();
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frame_layout, categoryFragment)
//                .addToBackStack(null) // Optional, for back stack handling
//                .commit();
//    }

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
//        if (requestCode == REQUEST_CODE_SELECT_CATEGORY && resultCode == RESULT_OK) {
//
//            assert data != null;
//            Parcelable parcelable = data.getParcelableExtra("categoryType");
//            CategoryType categoryType = Parcels.unwrap(parcelable);
//
//            RequestOptions requestOptions = new RequestOptions()
//                    .placeholder(R.drawable.app_icon_background)
//                    .error(R.drawable.app_icon_background);
//
//            Glide.with(requireActivity()).load(categoryType.getImageLink())
//                    .apply(requestOptions).diskCacheStrategy(DiskCacheStrategy.ALL).into(createTransactionCategoryTypeImage);
//
//            createTransactionCategoryTypeName.setText(categoryType.getName());
//        }

        if(requestCode == 1){
            uri = data.getData();
            pictureImg.setImageURI(uri);
        } else {
            Toast.makeText(getContext(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

}