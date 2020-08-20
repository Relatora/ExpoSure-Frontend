package com.example.exposure.Activity.Organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.exposure.R;


public class OrganizerMainActivity extends AppCompatActivity {
    private Button btnShowAllEvents;
    private  Button btnCreateEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oragnizer_main);

        // Inflate All Event Activity
        btnShowAllEvents = findViewById(R.id.btnShowAllEvents);
        btnCreateEvent = findViewById(R.id.btnCreateEvent);

        btnShowAllEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShowAllEventsActivity();
            }
        });

        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddEventActivity();
            }
        });
    }

    public void openShowAllEventsActivity (){
        Intent intent = new Intent(this, ListAllEventsActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    public void openAddEventActivity (){
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}
