package com.java.mapcollapsinglayout;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;
import com.sleepbot.datetimepicker.time.TimePickerDialog.OnTimeSetListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class createEventFragment1 extends Fragment implements OnDateSetListener, OnTimeSetListener {

    private String eventType;
    private ImageView imageView;
    private View view;
    private static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";
    private DatePickerDialog datePickerDialogStart;
    private TimePickerDialog timePickerDialogStart;
    private DatePickerDialog datePickerDialogEnd;
    private TimePickerDialog timePickerDialogEnd;
    private EditText startDate;
    private EditText endDate;
    private View viewToIdentifyTimePicker;
    private Bitmap eventImage;
    private EditText eventName;
    private EditText EventDescription;
    private EditText EventCapacity;
    private CheckBox EventsVolatile;
    private Spinner EvenrType;
    private Spinner EventVisibilityMiles;
    private Button createBtn;


    public createEventFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_event_fragment1, container, false);


        imageView = (ImageView) view.findViewById(R.id.location_map_view);
        imageView.setVisibility(View.GONE);

        eventName = (EditText) view.findViewById(R.id.event_name);
        EventDescription  = (EditText) view.findViewById(R.id.public_event_description);
        startDate = (EditText) view.findViewById(R.id.public_event_start_date);
        endDate = (EditText) view.findViewById(R.id.public_event_end_date);
        EventCapacity = (EditText) view.findViewById(R.id.public_event_capacity);
        EventCapacity = (EditText) view.findViewById(R.id.public_event_capacity);
        EvenrType = (Spinner) view.findViewById(R.id.public_event_type);
        EventVisibilityMiles = (Spinner) view.findViewById(R.id.public_event_visiblity_miles);
        createBtn = (Button) view.findViewById(R.id.public_create_event);

        final Calendar calendar = Calendar.getInstance();
        datePickerDialogStart = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), false);
        timePickerDialogStart = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY) ,calendar.get(Calendar.MINUTE), false, false);

        datePickerDialogEnd = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), false);
        timePickerDialogEnd = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY) ,calendar.get(Calendar.MINUTE), false, false);

        startDate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // datePickerDialog.setVibrate(isVibrate());
                datePickerDialogStart.setYearRange(calendar.get(Calendar.YEAR), calendar.get(Calendar.YEAR) + 1);
                datePickerDialogStart.setFirstDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
                datePickerDialogStart.setCloseOnSingleTapDay(isCloseOnSingleTapDay());
                datePickerDialogStart.show(getActivity().getSupportFragmentManager(), DATEPICKER_TAG);

                viewToIdentifyTimePicker = v;

            }

        });

        endDate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // datePickerDialog.setVibrate(isVibrate());
                datePickerDialogEnd.setYearRange(calendar.get(Calendar.YEAR), calendar.get(Calendar.YEAR) + 1);
                datePickerDialogEnd.setFirstDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
                datePickerDialogEnd.setCloseOnSingleTapDay(isCloseOnSingleTapDay());
                datePickerDialogEnd.show(getActivity().getSupportFragmentManager(), DATEPICKER_TAG);
                viewToIdentifyTimePicker = v;

            }

        });

        createBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // datePickerDialog.setVibrate(isVibrate());
               createEentObject();

            }

        });
        return view;
    }

    public Events createEentObject() {

        return null;
    }

    

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

        if(datePickerDialog.equals(datePickerDialogStart)) {
            startDate.setText(year + "-" + month + "-" + day);
            timePickerDialogStart.setCloseOnSingleTapMinute(isCloseOnSingleTapDay());
            timePickerDialogStart.show(getFragmentManager(), TIMEPICKER_TAG);
        }
        else {
            endDate.setText(year + "-" + month + "-" + day);
            timePickerDialogEnd.setCloseOnSingleTapMinute(isCloseOnSingleTapDay());
            timePickerDialogEnd.show(getFragmentManager(), TIMEPICKER_TAG);
        }

    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String time = "  At " + hourString + ":" + minuteString + "";

        if(viewToIdentifyTimePicker.getId() == R.id.public_event_start_date)
            startDate.append(time);
        else
            endDate.append(time);

    }

    private boolean isCloseOnSingleTapDay() {
        return false;
    }

    private boolean isVibrate() {
        return false;
    }


    @Subscribe
    public void getEventImage(Bitmap eventImage) {
        this.eventImage = eventImage;
    }
}
