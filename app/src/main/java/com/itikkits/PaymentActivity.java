package com.itikkits;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * * Created by Ahmed Adel Al-Desouqy on 20-Dec-18.
 */
public class PaymentActivity extends Activity {

    LinearLayout backContainer;
    ImageButton backButton;
    LinearLayout checkBoxContainer;
    ImageView checkBox;
    EditText creditCard, expiryDate, securityCode;
    Button submitButton;

    private static final char SPACE = ' ';
    private static final char SLASH = '/';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initViews();
        wireViews();
        fillCreditCardDataIfSaved();
    }

    private void fillCreditCardDataIfSaved() {
        submitButton.setEnabled(false);
        submitButton.setBackgroundColor(getResources().getColor(R.color.button_color_disabled));

        String savedCreditCard = SharedPreferenceManager.loadString("credit_card", this);
        if(savedCreditCard == null || savedCreditCard.isEmpty() || savedCreditCard.length()!=19) {
            return;
        } else {
            creditCard.setText(savedCreditCard);
            expiryDate.setText(SharedPreferenceManager.loadString("expiry_date", this));
            securityCode.setText(SharedPreferenceManager.loadString("security_code", this));
            submitButton.setEnabled(true);
            submitButton.setBackgroundColor(getResources().getColor(R.color.button_color_enabled));
            checkBox.setImageDrawable(getDrawable(R.drawable.checkbox_on));
            checkBox.setTag(R.drawable.checkbox_on);
        }
    }

    private void initViews() {
        backContainer = findViewById(R.id.back_container);
        backButton = findViewById(R.id.back_button);
        submitButton = findViewById(R.id.submit_button);
        checkBoxContainer = findViewById(R.id.checkbox_container);
        creditCard = findViewById(R.id.credit_card);
        expiryDate = findViewById(R.id.expiry_date);
        securityCode = findViewById(R.id.security_code);
        checkBox = findViewById(R.id.checkbox_image);
        // set tag for check box reversing
        checkBox.setTag(R.drawable.checkbox_off);
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

        checkBoxContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseCheckBox();
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseCheckBox();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simulatePayment();
            }
        });

        creditCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                submitButton.setEnabled(false);
                submitButton.setBackgroundColor(getResources().getColor(R.color.button_color_disabled));

                // Remove spacing char
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    final char c = s.charAt(s.length() - 1);
                    if (SPACE == c) {
                        s.delete(s.length() - 1, s.length());
                    }
                }
                // Insert char where needed.
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    char c = s.charAt(s.length() - 1);
                    // Only if its a digit where there should be a SPACE we insert a SPACE
                    if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(SPACE)).length <= 3) {
                        s.insert(s.length() - 1, String.valueOf(SPACE));
                    }
                }

                if(s.length()==19) {
                    expiryDate.requestFocus();
                    if(expiryDate.getText().length()==5 && securityCode.getText().length()==3) {
                        submitButton.setEnabled(true);
                        submitButton.setBackgroundColor(getResources().getColor(R.color.button_color_enabled));
                    }
                }
            }
        });

        expiryDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                submitButton.setEnabled(false);
                submitButton.setBackgroundColor(getResources().getColor(R.color.button_color_disabled));

                if(s.length()==1) {
                    if(s.charAt(0)!='0' && s.charAt(0)!='1') {
                        s.delete(s.length() - 1, s.length());
                    }
                }

                if(s.length()==2) {
                    if(s.charAt(0)=='1') {
                        if(s.charAt(1)!='0' && s.charAt(1)!='1' && s.charAt(1)!='2') {
                            s.delete(s.length() - 1, s.length());
                        }
                    } else if(s.charAt(0)=='0') {
                        if(s.charAt(1)=='0') {
                            s.delete(s.length() - 1, s.length());
                        }
                    }
                }

                // Remove spacing char
                if (s.length()==3 && s.charAt(s.length()-1)==SLASH) {
                    s.delete(s.length() - 1, s.length());
                }
                // Insert char where needed.
                if (s.length()==3) {
                    s.insert(s.length() - 1, String.valueOf(SLASH));
                }

                if(s.length()==4) {
                    if(s.charAt(3)!='1' && s.charAt(3)!='2') {
                        s.delete(s.length() - 1, s.length());
                    }
                }

                if(s.length()==5) {
                    if(s.charAt(3)=='1') {
                        if(s.charAt(4)!='9') {
                            s.delete(s.length() - 1, s.length());
                        } else {
                            securityCode.requestFocus();
                        }
                    } else if (s.charAt(3)=='2') {
                        securityCode.requestFocus();
                    }

                    if(creditCard.getText().length()==19 && securityCode.getText().length()==3) {
                        submitButton.setEnabled(true);
                        submitButton.setBackgroundColor(getResources().getColor(R.color.button_color_enabled));
                    }
                }
            }
        });

        securityCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                submitButton.setEnabled(false);
                submitButton.setBackgroundColor(getResources().getColor(R.color.button_color_disabled));

                if(s.length()==3 && creditCard.getText().length()==19 && expiryDate.getText().length()==5) {
                    submitButton.setEnabled(true);
                    submitButton.setBackgroundColor(getResources().getColor(R.color.button_color_enabled));
                }
            }
        });
    }

    private void simulatePayment() {
        saveUserCreditCardIfRequested();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Validating your payment..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(progressDialog.isShowing()) {
                    try {
                        progressDialog.dismiss();
                        navigateToHome();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 4000);
    }

    private void saveUserCreditCardIfRequested() {
        if(checkBox.getTag() != null && checkBox.getTag() instanceof Integer) {
            if((int) checkBox.getTag() == R.drawable.checkbox_on) {
                SharedPreferenceManager.saveString("credit_card", creditCard.getText().toString(), this);
                SharedPreferenceManager.saveString("expiry_date", expiryDate.getText().toString(), this);
                SharedPreferenceManager.saveString("security_code", securityCode.getText().toString(), this);
            }
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("recharged", true);
        startActivity(intent);
        finish();
    }

    private void reverseCheckBox() {
        if(checkBox.getTag() != null && checkBox.getTag() instanceof Integer) {
            if((int) checkBox.getTag() == R.drawable.checkbox_off) {
                checkBox.setImageDrawable(getDrawable(R.drawable.checkbox_on));
                checkBox.setTag(R.drawable.checkbox_on);
            } else {
                checkBox.setImageDrawable(getDrawable(R.drawable.checkbox_off));
                checkBox.setTag(R.drawable.checkbox_off);
            }
        }
    }
}
