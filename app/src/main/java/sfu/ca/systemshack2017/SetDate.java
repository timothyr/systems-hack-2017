package sfu.ca.systemshack2017;


import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Zhe on 2017-01-21.
 */

public class SetDate implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    Calendar calendar;
    EditText editText;
    Context context;

    SetDate(EditText editText, Context context){
        this.editText = editText;
        this.editText.setOnClickListener(this);
        this.context = context;
        calendar = Calendar.getInstance();
    }

    @Override
    public void onClick( View view){
        new DatePickerDialog(context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth){
        String myFormat = "MMM dd, yyyy"; //In which you need put here
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.CANADA);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        editText.setText(simpleDateFormat.format(calendar.getTime()));
    }

    public Calendar getCalendar() {
        return calendar;
    }
}