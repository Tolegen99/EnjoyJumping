package com.tolegensapps.enjoyjumping;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.backendless.Backendless;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tolegensapps.enjoyjumping.presentation.profile.ProfileFragment;
import com.tolegensapps.enjoyjumping.presentation.timetable.TimetableFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    BottomAppBar bottomAppBar;
    private String objectId;
    public static FloatingActionButton btnScanCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objectId = Backendless.UserService.loggedInUser();

        startProfile(objectId);
        initUIBehaviour();
    }

    private void startProfile(String objectId) {
        Bundle bundle = new Bundle();
        bundle.putString("objectId", objectId);
        Fragment selectedFragment = new ProfileFragment();
        selectedFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
    }

    private void initUIBehaviour() {
        btnScanCode = findViewById(R.id.btnScanCode);
        bottomAppBar = findViewById(R.id.bottomAppBar);



        btnScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScanCodeActivity.class));
            }
        });

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_schedule:
                        selectedFragment = new TimetableFragment();
                        break;
                    case R.id.navigation_profile:
                        selectedFragment = new ProfileFragment();
                        break;
                }
                if (selectedFragment != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("objectId", objectId);
                    selectedFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                }
                return true;
            }
        });
    }
}