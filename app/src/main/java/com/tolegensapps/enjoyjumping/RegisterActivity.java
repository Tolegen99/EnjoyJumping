package com.tolegensapps.enjoyjumping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private final static java.text.SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat("yyyy/MM/dd");

    private EditText inputUserName;
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView linkGoToBack;

    private Button btnReg;

    private String userName;
    private String email;
    private String password;

    private final LoadingDialog mLoadingDialog = new LoadingDialog(RegisterActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initUI();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToWelcomeActInt();
    }

    private void initUI() {
        inputUserName = (EditText) findViewById(R.id.inputUserNameReg);
        inputEmail = (EditText) findViewById(R.id.inputEmailReg);
        inputPassword = (EditText) findViewById(R.id.inputPasswordReg);
        btnReg = (Button) findViewById(R.id.btnReg);
        linkGoToBack = (TextView) findViewById(R.id.goToBack);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterButtonClicked();
            }
        });

        linkGoToBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWelcomeActInt();
            }
        });
    }

    private void onRegisterButtonClicked() {

        mLoadingDialog.startLoadingDialog();

        BackendlessUser user = new BackendlessUser();  //Объект user для регистрации(Логин, пароль)

        String userNameText = inputUserName.getText().toString().trim();
        String emailText = inputEmail.getText().toString().trim();
        String passwordText = inputPassword.getText().toString().trim();

//        Проверка не пустые ли поля заполнения данных при регистрации.

        if (emailText.isEmpty()) {
            Toast.makeText(this, "Field 'email' cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        } else
            email = emailText;

        if (passwordText.isEmpty()) {
            Toast.makeText(this, "Field 'password' cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        } else
            password = passwordText;

        if (userNameText.isEmpty()) {
            Toast.makeText(this, "Field 'name' cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        } else
            userName = userNameText;

        if (email != null) {
            user.setEmail(email);
        }

        if (password != null) {
            user.setPassword(password);
        }

        if (userName != null) {
            user.setProperty("userName", userName);
        }

//        Регистрация клиента

        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(final BackendlessUser createdUser) {
                mLoadingDialog.dismissDialog();
                Resources resources = getResources();
                String message = String.format(resources.getString(R.string.registration_success_message), resources.getString(R.string.app_name));
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setMessage(message).setTitle(R.string.registration_success)
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                goToWelcomeActInt();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                }

            @Override
            public void handleFault(BackendlessFault fault) {
                mLoadingDialog.dismissDialog();
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setMessage(fault.getMessage()).setTitle(R.string.registration_error)
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                goToWelcomeActInt();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void goToWelcomeActInt() {
        Intent intentGoToWelcomeActivity = new Intent(RegisterActivity.this, WelcomeActivity.class);
        intentGoToWelcomeActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentGoToWelcomeActivity);
        finish();
    }
}
