package com.rss.rss.Dashboard.Login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rss.rss.MainActivity;
import com.rss.rss.R;
import com.rss.rss.network.NetworkCall;
import com.rss.rss.utils.Session;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    //Widgets
    private MaterialButton nextButton;
    private MaterialButton cancel_button;
    private TextInputLayout passwordTextInput;
    private TextInputEditText password_edit_text,email_edit_text;
    private TextView toRegisterPage;
    private ProgressBar progressBar;
    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        passwordTextInput = findViewById(R.id.password_text_input);
        password_edit_text = findViewById(R.id.password_edit_text);
        email_edit_text = findViewById(R.id.email_edit_text);
        nextButton = findViewById(R.id.next_button);
        //cancel_button = findViewById(R.id.cancel_button);
        toRegisterPage = findViewById(R.id.toRegisterPage);

        checkbox = findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Session session = new Session(getApplicationContext());

                if (isChecked){
                    session.setRememberMe(true);
                }else {
                    session.setRememberMe(false);
                }
            }
        });

        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: ");
                
                if (!isPasswordValid(password_edit_text.getText())) {
                    passwordTextInput.setError(getString(R.string.rss_error_password));
                } else {
                    passwordTextInput.setError(null); // Clear the error
                     // Navigate to the next Fragment
                    try {
                        NetworkCall.doLogin(email_edit_text.getText().toString(),password_edit_text.getText().toString(),LoginActivity.this,LoginActivity.this,progressBar);
                    }catch (Exception e){
                        Log.d(TAG, "onClick: "+e.getMessage());
                    }
                }
            }
        });

        // Clear the error once more than 8 characters are typed.
        password_edit_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(password_edit_text.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });

        toRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        
    }
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 6;
    }

}