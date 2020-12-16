package com.tolegensapps.enjoyjumping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private int CAMERA_PERMISSION_CODE = 1;

    ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if (ContextCompat.checkSelfPermission(ScanCodeActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission();
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(ScanCodeActivity.this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
    }

    @Override
    public void handleResult(Result result) {
        String txtResult = String.valueOf(result);
        if (txtResult.equals("EnjoyJumping")) {
            debuctOneTimeVisit();

        } else {

        }
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }


    @Override
    protected void onResume() {
        super.onResume();

        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ScanCodeActivity.this, MainActivity.class);
        startActivity(intent);
    }



    private void debuctOneTimeVisit() {
        Backendless.Data.of(BackendlessUser.class).findById(Backendless.UserService.loggedInUser(), new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser user) {
                int count = (int) user.getProperty("ticketNumberOfVisits");
                if(count > 0) {
                    count--;
                    user.setProperty("ticketNumberOfVisits", count);
                    Backendless.Data.of(BackendlessUser.class).save(user, new AsyncCallback<BackendlessUser>() {

                        @Override
                        public void handleResponse(BackendlessUser response) {
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                        }
                    });
                } else
                    Log.d("MYAPP", "Мало");
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });






    }
}