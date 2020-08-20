package com.example.exposure.Activity.Organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.exposure.API.IExpoEvents;
import com.example.exposure.API.RetrofitClient;
import com.example.exposure.Model.ExpoEvent;
import com.example.exposure.R;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Add ExpoEvent to database
 */
public class AddEventActivity extends AppCompatActivity {
    Context context;
    Button btnDateAddEvent, btnTimeAddEvent, btnSubmitAddEvent;
    TextView txtNameAddEvent,txtLocationAddEvent,txtContactNameAddEvent,
            txtContactPhoneAddEvent,txtContactEmailAddEvent,
            txtDateAddEvent, txtTimeAddEvent;
    Calendar calDate = Calendar.getInstance();
    Calendar calTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        // Connecting controls to logic
        txtNameAddEvent = findViewById(R.id.txtNameAddEvent);
        txtLocationAddEvent = findViewById(R.id.txtLocationAddEvent);
        txtContactNameAddEvent = findViewById(R.id.txtContactNameAddEvent);
        txtContactPhoneAddEvent = findViewById(R.id.txtContactPhoneAddEvent);
        txtContactEmailAddEvent = findViewById(R.id.txtContactEmailAddEvent);
        txtDateAddEvent = findViewById(R.id.txtDateAddEvent);
        txtTimeAddEvent = findViewById(R.id.txtTimeAddEvent);

        btnDateAddEvent = findViewById(R.id.btnDateAddEvent);
        btnTimeAddEvent = findViewById(R.id.btnTimeAddEvent);
        btnSubmitAddEvent = findViewById(R.id.btnSubmitAddEvent);

        // Click listeners
        btnDateAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });

        btnTimeAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton();
            }
        });

        btnSubmitAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmitButton();
            }
        });
    }

    /**
     * Launch organizer main activity
     */
    private void launchOrganizerMainActivity() {
        Intent intent = new Intent(this, OrganizerMainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    /**
     * Creates an instance of the API
     * @return RetrofitClient.getInstance().create(IExpoEvents.class)
     */
    private IExpoEvents getAPI() {
        return RetrofitClient.getInstance().create(IExpoEvents.class);
    }

    /**
     * Sends Event information to backend
     */
    private void handleSubmitButton(){
        btnSubmitAddEvent.onEditorAction(EditorInfo.IME_ACTION_DONE);
        launchOrganizerMainActivity();
//        "2020/12/02 13:00"
        CharSequence dateCharSequence = DateFormat.format("yyyy/MM/dd HH:mm",calDate);
        String strDate = dateCharSequence.toString();
        ExpoEvent expoEvent = new ExpoEvent(txtNameAddEvent.getText().toString(),
                txtLocationAddEvent.getText().toString(),
                txtContactNameAddEvent.getText().toString(),
                txtContactPhoneAddEvent.getText().toString(),
                txtContactEmailAddEvent.getText().toString(),
                strDate, "");
        Call<ExpoEvent> call = getAPI().createEvent(expoEvent);
        call.enqueue(new Callback<ExpoEvent>() {
            @Override
            public void onResponse(Call<ExpoEvent> call, Response<ExpoEvent> response) {

                assert response.body() != null;
                String msg = String.format("%s%s", getString(R.string.user_created_successfully_msg), response.body().getevent_id());
                Toast.makeText(AddEventActivity.this, msg, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<ExpoEvent> call, Throwable t) {

            }
        });
    }

//    private boolean isValidated(){
//        boolean result = false;
//        try{
//
//            if (inputEditText.getText().length()!=0){
//                rotate();
//                inputEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
//            }else{
//                Toast.makeText(getApplicationContext(),"stuff here",Toast.LENGTH_SHORT).show();
//            }
//        }catch (Exception e){
//            Toast toast=Toast. makeText(getApplicationContext(),e.getMessage(),Toast. LENGTH_SHORT);
//            toast. setMargin(100,200);
//            toast. show();
//            //outputEditText.setText(String.valueOf(e.getMessage()));
//        }
//        return result;
//    }

    // Creates a popup dialogue picker for date
    private void handleDateButton(){
        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                String dateString = year  + " " + month + " " + date;
                txtDateAddEvent.setText(dateString);


                calDate.set(Calendar.YEAR,year);
                calDate.set(Calendar.MONTH,month);
                calDate.set(Calendar.DATE,date);

                CharSequence dateCharSequence = DateFormat.format("EEEE, dd MMM yyyy",calDate);
                txtDateAddEvent.setText(dateCharSequence);
            }
        }, YEAR, MONTH, DATE);



        datePickerDialog.show();
    }

    // Creates a popup dialogue picker for Time
    private void handleTimeButton(){
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                calTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calTime.set(Calendar.MINUTE,minute);

                CharSequence dateCharSequence = DateFormat.format("hh:mm a",calTime);
                txtTimeAddEvent.setText(dateCharSequence);

            }
        },HOUR, MINUTE, false);
        timePickerDialog.show();
    }
}

/*
DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH);
LocalDate date = LocalDate.parse("2018-04-10T04:00:00.000Z", inputFormatter);
String formattedDate = outputFormatter.format(date);
System.out.println(formattedDate); // prints 10-04-2018
 */
