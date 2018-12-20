package com.itikkits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * * Created by Ahmed Adel Al-Desouqy on 15-Dec-18.
 */
public class HomeActivity extends Activity {

    private Button addBalanceButton;
    private TextView balanceValue, remainingRidesValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        balanceValue = findViewById(R.id.balance_value);
        remainingRidesValue = findViewById(R.id.remaining_rides_value);

        addBalanceButton = findViewById(R.id.add_balance_button);
        addBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRechargingOptions();
            }
        });

        int userBalance = getUserBalanceFromSharedPreferences();

        Intent intent = getIntent();
        if(intent!=null) {
            boolean recharged = intent.getBooleanExtra("recharged", false);
            if(recharged) {
                userBalance += 750;
                updateUserBalanceInSharedPreferences(userBalance);
            }
        }

        balanceValue.setText(userBalance + " p");
        remainingRidesValue.setText(userBalance/15 + " Rides");
    }

    private void updateUserBalanceInSharedPreferences(int userBalance) {
        SharedPreferenceManager.saveInt("user_balance", userBalance, this);
    }

    private int getUserBalanceFromSharedPreferences() {
        return SharedPreferenceManager.loadInt("user_balance", this);
    }

    private void navigateToRechargingOptions() {
        Intent intent = new Intent(this, SelectRechargingAmountActivity.class);
        startActivity(intent);
    }
}
