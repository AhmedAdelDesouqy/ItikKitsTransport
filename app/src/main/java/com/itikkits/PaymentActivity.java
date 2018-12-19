package com.itikkits;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * * Created by Ahmed Adel Al-Desouqy on 20-Dec-18.
 */
public class PaymentActivity extends Activity {

    LinearLayout backContainer;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initViews();
        wireViews();
    }

    private void initViews() {
        backContainer = findViewById(R.id.back_container);
        backButton = findViewById(R.id.back_button);
    }

    private void wireViews() {
        // back
        backContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
