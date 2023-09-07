package com.example.lab1;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private boolean isValidPassword(String password) {
        if (password.length() < 10) return false;
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[@#$%&()\\[\\]{}_=<>+\\-!?*/|:;.,'\"~^].*");
        return hasUppercase && hasLowercase && hasDigit && hasSpecial;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox showPasswordCheckBox = findViewById(R.id.showPasswordCheckBox);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);


        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Pour afficher le mot de passe
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    // Pour masquer le mot de passe
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                // Pour garder le curseur à la fin du texte
                passwordEditText.setSelection(passwordEditText.length());
            }
        });
    }

    public void validatePassword(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        TextView textView = findViewById(R.id.validateMessage);
        if (isValidPassword(password) && email.equals("hugomontreuil25@gmail.com")) {
            textView.setText(R.string.password_and_email_valid);  // défini dans strings.xml
            textView.setTextColor(getResources().getColor(R.color.blue));
        } else {
            textView.setText(R.string.password_or_email_invalid);  // défini dans strings.xml
            textView.setTextColor(getResources().getColor(R.color.red));
        }
    }
}