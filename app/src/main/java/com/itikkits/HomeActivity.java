package com.itikkits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();

        // check for nfc intent
        if (intent != null && NfcHelper.isOurTagConnected(intent)) {
            subtractOneRideFromUserCredit();
        }

        // check for recharging intent
        int userBalance = getUserBalanceFromSharedPreferences();
        if(intent!=null) {
            boolean recharged = intent.getBooleanExtra("recharged", false);
            if(recharged) {
                userBalance += 750;
                updateUserBalanceInSharedPreferences(userBalance);
                addBalanceButton.setEnabled(false);
                addBalanceButton.setBackgroundColor(getResources().getColor(R.color.button_color_disabled));
            }
        }

        if(userBalance == 0) {
            addBalanceButton.setEnabled(true);
            addBalanceButton.setBackgroundColor(getResources().getColor(R.color.button_color_enabled));
        } else {
            addBalanceButton.setEnabled(false);
            addBalanceButton.setBackgroundColor(getResources().getColor(R.color.button_color_disabled));
        }

        balanceValue.setText(userBalance + " p");
        remainingRidesValue.setText(userBalance/15 + " Rides");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && NfcHelper.isOurTagConnected(intent)) {
            subtractOneRideFromUserCredit();
        }
    }

    private void subtractOneRideFromUserCredit() {
        int userBalance = getUserBalanceFromSharedPreferences();
        if (userBalance != 0) {
            userBalance -= 15;
            updateUserBalanceInSharedPreferences(userBalance);
            Toast.makeText(this, "One ride deducted from your balance", Toast.LENGTH_SHORT).show();
            balanceValue.setText(userBalance + " p");
            remainingRidesValue.setText(userBalance/15 + " Rides");
            if(userBalance == 0) {
                addBalanceButton.setEnabled(true);
                addBalanceButton.setBackgroundColor(getResources().getColor(R.color.button_color_enabled));
            }
        } else {
            Toast.makeText(this, "No enough balance. Please recharge", Toast.LENGTH_SHORT).show();
        }
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
