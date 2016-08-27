package com.viveksb007.androidterminal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    EditText mUserName;
    Button mLoginTerminal;
    String userName;
    String FIREBASE_URL = "https://admob-app-id-2073089105.firebaseio.com/";
    boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), getString(R.string.app_id));
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mUserName = (EditText) findViewById(R.id.etUserName);
        mLoginTerminal = (Button) findViewById(R.id.btnLoginTerminal);
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        userName = sharedPreferences.getString("username", "");
        firstTime = sharedPreferences.getBoolean("firstTime", true);
        mUserName.setText(userName);
        mLoginTerminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserName.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "WTF? UserName please", Toast.LENGTH_SHORT).show();
                } else {
                    if (firstTime) {
                        saveUserName();
                    }
                    Intent i = new Intent(getApplicationContext(), Terminal.class);
                    i.putExtra("Username", mUserName.getText().toString());
                    startActivity(i);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
        } else if (id == R.id.about) {
            DialogFragment aboutDialog = new about();
            aboutDialog.show(getSupportFragmentManager(), "ATerminal");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveUserName() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", mUserName.getText().toString());
        editor.putBoolean("firstTime", false);
        editor.commit();
    }

}
