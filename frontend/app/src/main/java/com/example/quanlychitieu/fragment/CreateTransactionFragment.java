package com.example.quanlychitieu.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

import org.parceler.Parcels;

public class CreateTransactionFragment extends Fragment implements CustomSpinnerExpense.OnSpinnerEventsListener {
    private static final int REQUEST_CODE_SELECT_CATEGORY = 1;
    LinearLayout calendarView, linearLayoutCreateTransactionCategoryType;
    TextView txtCalendarDate, txtTimerDate, createTransactionCategoryTypeName;
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
        return view;
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


    @Override
    public void onPopupWindowOpened(Spinner spinner) {

    }

    @Override
    public void onPopupWindowClosed(Spinner spinner) {

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
