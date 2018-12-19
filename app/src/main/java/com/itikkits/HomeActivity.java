package com.itikkits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * * Created by Ahmed Adel Al-Desouqy on 15-Dec-18.
 */
public class HomeActivity extends Activity {

    private Button addBalanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addBalanceButton = findViewById(R.id.add_balance_button);
        addBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRechargingOptions();
            }
        });
    }

    private void navigateToRechargingOptions() {
        Intent intent = new Intent(this, SelectRechargingAmountActivity.class);
        startActivity(intent);
    }
}
