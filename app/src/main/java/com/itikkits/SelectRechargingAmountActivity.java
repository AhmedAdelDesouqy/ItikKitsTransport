package com.itikkits;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * * Created by Ahmed Adel Al-Desouqy on 19-Dec-18.
 */
public class SelectRechargingAmountActivity extends Activity {

    LinearLayout backContainer;
    ImageButton backButton;

    LinearLayout one_ride_container, metro_only_container, bus_only_container, bus_and_metro_container;
    ImageView one_ride_radio, metro_only_radio, bus_only_radio, bus_and_metro_radio;
    TextView one_ride_caption, metro_only_caption1, metro_only_caption2, bus_only_caption1, bus_only_caption2,
            bus_and_metro_caption1, bus_and_metro_caption2,
            one_ride_logo;
    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharging_options);

        initViews();
        wireViews();
    }

    private void initViews() {
        backContainer = findViewById(R.id.back_container);
        backButton = findViewById(R.id.back_button);

        // radio containers
        one_ride_container = findViewById(R.id.one_ride_container);
        metro_only_container = findViewById(R.id.metro_only_container);
        bus_only_container = findViewById(R.id.bus_only_container);
        bus_and_metro_container = findViewById(R.id.bus_and_metro_container);

        // radio buttons
        one_ride_radio = findViewById(R.id.one_ride_radio);
        metro_only_radio = findViewById(R.id.metro_only_radio);
        bus_only_radio = findViewById(R.id.bus_only_radio);
        bus_and_metro_radio = findViewById(R.id.bus_and_metro_radio);
        
        // captions
        one_ride_caption = findViewById(R.id.one_ride_caption);
        metro_only_caption1 = findViewById(R.id.metro_only_caption_1);
        metro_only_caption2 = findViewById(R.id.metro_only_caption_2);
        bus_only_caption1 = findViewById(R.id.bus_only_caption_1);
        bus_only_caption2 = findViewById(R.id.bus_only_caption_2);
        bus_and_metro_caption1 = findViewById(R.id.bus_and_metro_caption_1);
        bus_and_metro_caption2 = findViewById(R.id.bus_and_metro_caption_2);

        // logos
        one_ride_logo = findViewById(R.id.one_ride_logo);

        // button
        payButton = findViewById(R.id.pay_button);
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

        // pay button
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPaymentScreen();
            }
        });

        // one ride selected
        one_ride_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneRideSelected();
            }
        });
        one_ride_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneRideSelected();
            }
        });

        // metro only selected
        metro_only_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metroOnlySelected();
            }
        });
        metro_only_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metroOnlySelected();
            }
        });

        // bus only selected
        bus_only_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                busOnlySelected();
            }
        });
        bus_only_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                busOnlySelected();
            }
        });

        // bus and metro selected
        bus_and_metro_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                busAndMetroSelected();
            }
        });
        bus_and_metro_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                busAndMetroSelected();
            }
        });
    }

    private void navigateToPaymentScreen() {
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }

    private void oneRideSelected() {
        one_ride_container.setBackgroundResource(R.color.color_cell_background_selected);
        metro_only_container.setBackgroundColor(Color.parseColor("#00000000"));
        bus_only_container.setBackgroundColor(Color.parseColor("#00000000"));
        bus_and_metro_container.setBackgroundColor(Color.parseColor("#00000000"));

        one_ride_radio.setImageResource(R.drawable.radio_on);
        metro_only_radio.setImageResource(R.drawable.radio_off);
        bus_only_radio.setImageResource(R.drawable.radio_off);
        bus_and_metro_radio.setImageResource(R.drawable.radio_off);

        one_ride_logo.setTextColor(getResources().getColor(R.color.color_caption_selected));
        one_ride_caption.setTextColor(getResources().getColor(R.color.color_caption_selected));
        metro_only_caption1.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        metro_only_caption2.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_only_caption1.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_only_caption2.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_and_metro_caption1.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_and_metro_caption2.setTextColor(getResources().getColor(R.color.color_caption_unselected));

        payButton.setEnabled(false);
        payButton.setBackgroundColor(getResources().getColor(R.color.button_color_disabled));
    }

    private void metroOnlySelected() {
        one_ride_container.setBackgroundColor(Color.parseColor("#00000000"));
        metro_only_container.setBackgroundResource(R.color.color_cell_background_selected);
        bus_only_container.setBackgroundColor(Color.parseColor("#00000000"));
        bus_and_metro_container.setBackgroundColor(Color.parseColor("#00000000"));

        one_ride_radio.setImageResource(R.drawable.radio_off);
        metro_only_radio.setImageResource(R.drawable.radio_on);
        bus_only_radio.setImageResource(R.drawable.radio_off);
        bus_and_metro_radio.setImageResource(R.drawable.radio_off);

        one_ride_logo.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        one_ride_caption.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        metro_only_caption1.setTextColor(getResources().getColor(R.color.color_caption_selected));
        metro_only_caption2.setTextColor(getResources().getColor(R.color.color_caption_selected));
        bus_only_caption1.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_only_caption2.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_and_metro_caption1.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_and_metro_caption2.setTextColor(getResources().getColor(R.color.color_caption_unselected));

        payButton.setEnabled(true);
        payButton.setBackgroundColor(getResources().getColor(R.color.button_color_enabled));
    }

    private void busOnlySelected() {
        one_ride_container.setBackgroundColor(Color.parseColor("#00000000"));
        metro_only_container.setBackgroundColor(Color.parseColor("#00000000"));
        bus_only_container.setBackgroundResource(R.color.color_cell_background_selected);
        bus_and_metro_container.setBackgroundColor(Color.parseColor("#00000000"));

        one_ride_radio.setImageResource(R.drawable.radio_off);
        metro_only_radio.setImageResource(R.drawable.radio_off);
        bus_only_radio.setImageResource(R.drawable.radio_on);
        bus_and_metro_radio.setImageResource(R.drawable.radio_off);

        one_ride_logo.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        one_ride_caption.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        metro_only_caption1.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        metro_only_caption2.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_only_caption1.setTextColor(getResources().getColor(R.color.color_caption_selected));
        bus_only_caption2.setTextColor(getResources().getColor(R.color.color_caption_selected));
        bus_and_metro_caption1.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_and_metro_caption2.setTextColor(getResources().getColor(R.color.color_caption_unselected));

        payButton.setEnabled(false);
        payButton.setBackgroundColor(getResources().getColor(R.color.button_color_disabled));
    }

    private void busAndMetroSelected() {
        one_ride_container.setBackgroundColor(Color.parseColor("#00000000"));
        metro_only_container.setBackgroundColor(Color.parseColor("#00000000"));
        bus_only_container.setBackgroundColor(Color.parseColor("#00000000"));
        bus_and_metro_container.setBackgroundResource(R.color.color_cell_background_selected);

        one_ride_radio.setImageResource(R.drawable.radio_off);
        metro_only_radio.setImageResource(R.drawable.radio_off);
        bus_only_radio.setImageResource(R.drawable.radio_off);
        bus_and_metro_radio.setImageResource(R.drawable.radio_on);

        one_ride_logo.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        one_ride_caption.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        metro_only_caption1.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        metro_only_caption2.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_only_caption1.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_only_caption2.setTextColor(getResources().getColor(R.color.color_caption_unselected));
        bus_and_metro_caption1.setTextColor(getResources().getColor(R.color.color_caption_selected));
        bus_and_metro_caption2.setTextColor(getResources().getColor(R.color.color_caption_selected));

        payButton.setEnabled(false);
        payButton.setBackgroundColor(getResources().getColor(R.color.button_color_disabled));
    }
}
