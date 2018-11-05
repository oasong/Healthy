package it59070098.kmitl.healthy;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import it59070098.kmitl.healthy.Database.dbhelp;

public class sleepform extends AppCompatActivity {
    private static final String TAG = "Sleep Form";
    private dbhelp db = new dbhelp(this);
    private Calendar calendar;
    private TextView formdate;
    private TextView formstart;
    private TextView formend;
    private Button addbtn;
    private boolean haveExtra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleepform);

        Button sleepformback = findViewById(R.id.sleepformback);
        sleepformback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stin = new Intent(getApplicationContext(), sleep.class);
                startActivity(stin);
            }
        });

        formdate = findViewById(R.id.sleepformdate);
        formstart = findViewById(R.id.sleepformstart);
        formend = findViewById(R.id.sleepformend);
        addbtn = findViewById(R.id.sleepformadd);

        formstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(formstart);
            }
        });
        formend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(formend);
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (haveExtra) {
                    updateData();
                } else {
                    saveData();
                }
                finish();
            }
        });
        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
        formdate.setText(format.format(new Date()));

        getExtra();

    }

    private void setTime(final TextView textView) {
        // get current time
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // show time picker dialog
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d("Time Picker", "hour = " + hourOfDay + "|| minute = " + minute);
                String time = String.format("%02d:%02d", hourOfDay, minute);
                textView.setText(time);
            }
        };

        TimePickerDialog timePicker = new TimePickerDialog(this, listener, hour, minute, true);
        timePicker.show();
    }

    private void saveData() {
        sleepitem item = new sleepitem();
        item.setDateString(formdate.getText().toString());
        item.setStartTime(formstart.getText().toString());
        item.setEndTime(formend.getText().toString());
        item.setDuration(calculateTime());
        db.insertSleepTime(item);
        Log.d(TAG, "data saved");
        Toast.makeText(sleepform.this, "saved data", Toast.LENGTH_LONG).show();
    }

    private void updateData() {
        sleepitem item = (sleepitem) getIntent().getSerializableExtra("item");
        item.setDateString(formdate.getText().toString());
        item.setStartTime(formstart.getText().toString());
        item.setEndTime(formend.getText().toString());
        item.setDuration(calculateTime());
        db.update(item);
        Log.d(TAG, "item id" + item.getId());
        Toast.makeText(sleepform.this, "saved data", Toast.LENGTH_LONG).show();
    }

    private String calculateTime() {
        /*
         * Reference url : https://www.mkyong.com/java/how-to-calculate-date-time-difference-in-java
         */
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String start = formstart.getText().toString();
        String stop = formend.getText().toString();
        Date date1;
        Date date2;
        String duration;
        String hDuration = "";
        String mDuration = "";

        try {
            date1 = format.parse(start);
            date2 = format.parse(stop);
            DateTime dateTime1 = new DateTime(date1);
            DateTime dateTime2 = new DateTime(date2);

            int hour = Hours.hoursBetween(dateTime1, dateTime2).getHours() % 24;
            int minute = Minutes.minutesBetween(dateTime1, dateTime2).getMinutes() % 60;
            Log.d("Sleep Form", dateTime1.toString() + " " + dateTime2.toString());

            if (hour < 0) {
                hDuration = String.format("%02d", 24 + hour);
            } else {
                hDuration = String.format("%02d", hour);
            }
            if (minute < 0) {
                mDuration = String.format("%02d", 60 + minute);
            } else {
                mDuration = String.format("%02d", minute);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        duration = String.format("%s:%s", hDuration, mDuration);
        Log.d(TAG, duration);
        return duration;
    }

    private void getExtra() {
        sleepitem item = (sleepitem) getIntent().getSerializableExtra("item");
        if (item != null) {
            haveExtra = true;
            formdate.setText(item.getDateString());
            formstart.setText(item.getStartTime());
            formend.setText(item.getEndTime());
        }
    }
}
