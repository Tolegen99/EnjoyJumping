package com.tolegensapps.enjoyjumping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserTokenStorageFactory;

public class WelcomeActivity extends AppCompatActivity {

    private final static String APPLICATION_ID = "E4C4DA74-3C08-A467-FF5C-BCB54B096600";
    private final static String SECRET_KEY = "16737C3E-7FCF-4AB8-8589-6BE76F4FF57A";
    private final static String SERVER_URL = "https://eu-api.backendless.com";
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView linkGoToRegister;

    private final LoadingAlertDialog mLoadingAlertDialog = new LoadingAlertDialog(WelcomeActivity.this);

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(this, APPLICATION_ID, SECRET_KEY);

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

        mLoadingAlertDialog.startLoadingDialog();

        Backendless.UserService.login(email.toString(), password.toString(), new AsyncCallback<BackendlessUser>() {
            public void handleResponse(BackendlessUser user) {
                mLoadingAlertDialog.dismissDialog();
                Intent intentToProfile = new Intent(WelcomeActivity.this, MainActivity.class);
                intentToProfile.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intentToProfile);
                finish();
            }

            public void handleFault(BackendlessFault fault) {
                mLoadingAlertDialog.dismissDialog();
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