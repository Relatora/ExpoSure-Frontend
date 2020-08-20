package com.example.exposure.Activity.Business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.exposure.Activity.Organizer.ListAllEventsActivity;
import com.example.exposure.R;

public class BusinessMainActivity extends AppCompatActivity {

    private Button btnBusinessShowAllProducts, btnBusinessAddProducts, btnBusinessViewAllEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_main);

        // Connecting buttons code with interface
        btnBusinessShowAllProducts = findViewById(R.id.btnBusinessShowAllProducts);
        btnBusinessAddProducts = findViewById(R.id.btnBusinessAddProducts);
        btnBusinessViewAllEvents = findViewById(R.id.btnBusinessViewAllEvents);

        // setting up button OnClick listener
        btnBusinessShowAllProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShowAllProducts();
            }
        });

        // setting up button OnClick listener
        btnBusinessAddProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddProduct();
            }
        });

        // setting up button OnClick listener
        btnBusinessViewAllEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewAllEvents();
            }
        });
    }

    // Inflate ListAllProductsActivity
    private void openShowAllProducts() {
        Intent intent = new Intent(this, ListAllProductsActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    // Inflate AddProductActivity
    private void openAddProduct() {
        //TODO: Complete the logic here
    }

    // Inflate ListAllEventsActivity
    private void openViewAllEvents() {
        Intent intent = new Intent(this, ListAllEventsActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
