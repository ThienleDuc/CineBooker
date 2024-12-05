package com.example.cinebooker.PhanCongQuoc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinebooker.R;

public class register extends AppCompatActivity {
    private boolean isPasswordVisible = false; // Track password visibility
    private boolean isReenterPasswordVisible = false; // Track re-enter password visibility

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView quayLai = findViewById(R.id.back_register);
        quayLai.setOnClickListener(v -> onBackPressed());

        Button dangKy = findViewById(R.id.register_btn);



        EditText passwordInput_1 = findViewById(R.id.password_input);
        EditText confirmPasswordInput = findViewById(R.id.reenter_password_input);

        final boolean[] isPasswordVisible = {false};

        passwordInput_1.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (passwordInput_1.getRight() - passwordInput_1.getPaddingRight())) {
                    // Toggle password visibility
                    if (isPasswordVisible[0]) {
                        passwordInput_1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordInput_1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_remove_red_eye_24, 0);
                    } else {
                        passwordInput_1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordInput_1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.icons8_eye, 0);
                    }
                    isPasswordVisible[0] = !isPasswordVisible[0];
                    // Reset cursor position
                    passwordInput_1.setSelection(passwordInput_1.getText().length());
                    return true;
                }
            }
            return false;
        });

        // Toggle confirm password visibility
        confirmPasswordInput.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (confirmPasswordInput.getRight() - confirmPasswordInput.getPaddingRight())) {
                    // Toggle confirm password visibility
                    if (isPasswordVisible[0]) {
                        confirmPasswordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        confirmPasswordInput.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_remove_red_eye_24, 0);
                    } else {
                        confirmPasswordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        confirmPasswordInput.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.icons8_eye, 0);
                    }
                    isPasswordVisible[0] = !isPasswordVisible[0];
                    // Reset cursor position
                    confirmPasswordInput.setSelection(confirmPasswordInput.getText().length());
                    return true;
                }
            }
            return false;
        });

        dangKy.setOnClickListener(v -> {
            // Collect user input
            EditText usernameInput = findViewById(R.id.username_input);
            EditText emailInput = findViewById(R.id.email_input);
            EditText passwordInput = findViewById(R.id.password_input);
            EditText reenterPasswordInput = findViewById(R.id.reenter_password_input);

            String username = usernameInput.getText().toString();
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            String reenterPassword = reenterPasswordInput.getText().toString();

            // Validation checks
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || reenterPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(reenterPassword)) {
                Toast.makeText(this, "Mật khẩu nhập lại không khớp!", Toast.LENGTH_SHORT).show();
            } else {
                // Save user data to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("username", username);
                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply();

                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

                // Navigate to login screen
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }


}