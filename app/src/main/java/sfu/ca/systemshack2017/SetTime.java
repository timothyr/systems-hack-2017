package sfu.ca.systemshack2017;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Zhe on 2017-01-21.
 */

public class SetTime implements TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    Calendar calendar;
    EditText editText;
    Context context;

    SetTime(EditText editText, Context context){
        this.editText = editText;
        this.editText.setOnClickListener(this);
        this.context = context;
        calendar = Calendar.getInstance();
    }

    /*
    TimePickerDialog (Context context,
                TimePickerDialog.OnTimeSetListener listener,
                int hourOfDay,
                int minute,
                boolean is24HourView)
     */
    @Override
    public void onClick( View view){
        new TimePickerDialog(context, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute){
        String myFormat = "K:mm a"; //In which you need put here
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.CANADA);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        editText.setText(simpleDateFormat.format(calendar.getTime()));

    }

    public Calendar getCalendar() {
        return calendar;
    }
}