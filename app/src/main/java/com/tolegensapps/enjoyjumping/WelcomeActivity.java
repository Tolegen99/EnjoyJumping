package com.tolegensapps.enjoyjumping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserTokenStorageFactory;
import com.tolegensapps.enjoyjumping.R;
import com.tolegensapps.enjoyjumping.RegisterActivity;

public class WelcomeActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPassword;
    private TextView linkGoToRegister;

    private final LoadingDialog mLoadingDialog = new LoadingDialog(WelcomeActivity.this);

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Backendless.initApp(this, "56E21695-ED53-450B-9C95-851952ED7136", "EB9A0F63-6CF2-496F-A8B4-B80D0D6ED6E2");

        String userToken = UserTokenStorageFactory.instance().getStorage().get();

        if (userToken != null && !userToken.equals("")) {
            Intent intentToProfile = new Intent(WelcomeActivity.this, MainActivity.class);
            intentToProfile.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intentToProfile);
            finish();
        }

        initUI();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onPause();
    }

    private void initUI() {
        inputEmail = (EditText) findViewById(R.id.inputEmailLogin);
        inputPassword = (EditText) findViewById(R.id.inputPasswordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        linkGoToRegister = (TextView) findViewById(R.id.goToRegister);
        linkGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGoToRegister = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intentGoToRegister);
            }
        });

    }

    public void onClickLogin(View view) {
        CharSequence email = inputEmail.getText();
        CharSequence password = inputPassword.getText();

        mLoadingDialog.startLoadingDialog();

        Backendless.UserService.login(email.toString(), password.toString(), new AsyncCallback<BackendlessUser>() {
            public void handleResponse(BackendlessUser user) {
                mLoadingDialog.dismissDialog();
                Intent intentToProfile = new Intent(WelcomeActivity.this, MainActivity.class);
                intentToProfile.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intentToProfile);
                finish();
            }

            public void handleFault(BackendlessFault fault) {
                mLoadingDialog.dismissDialog();
                AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                builder.setMessage(fault.getMessage()).setTitle(R.string.login_error)
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }, true);
    }
}