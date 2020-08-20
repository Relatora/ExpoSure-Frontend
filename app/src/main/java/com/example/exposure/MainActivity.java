package com.example.exposure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.exposure.Activity.Business.BusinessMainActivity;
import com.example.exposure.Activity.Organizer.OrganizerMainActivity;
import com.example.exposure.Activity.Visitor.VisitorMainActivity;

/**
 * Application Entry Point
 */
public class MainActivity extends AppCompatActivity {

    // Declaring button variables
    private Button btnOrganizer;
    private Button btnBusiness;
    private Button btnVisitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connecting buttons code with interface
        btnOrganizer = findViewById(R.id.btnOrganizer);
        btnBusiness =  findViewById(R.id.btnBusiness);
        btnVisitor =  findViewById(R.id.btnVisitor);

        // setting up button OnClick listener
        btnOrganizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrganizerMainActivity();
            }
        });

        // setting up button OnClick listener
        btnBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBusinessMainActivity();
            }
        });

        // setting up button OnClick listener
        btnVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVisitorMainActivity();
            }
        });
    }

    // Organizer Program Flow is complete - Read and write to api
    public void openOrganizerMainActivity (){
        // Declaring intent to inflate target activity
        Intent intent = new Intent(this, OrganizerMainActivity.class);
        // fire up intent
        startActivity(intent);
        //control activity transition animation
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    // Business - INCOMPLETE - Work in progress
    public void openBusinessMainActivity (){
        // Declaring intent to inflate target activity
        Intent intent = new Intent(this, BusinessMainActivity.class);
        // fire up intent
        startActivity(intent);
        //control activity transition animation
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    // Product  - INCOMPLETE - Work in progress
    public void openVisitorMainActivity (){
        // Declaring intent to inflate target activity
        Intent intent = new Intent(this, VisitorMainActivity.class);
        // fire up intent
        startActivity(intent);
        //control activity transition animation
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
