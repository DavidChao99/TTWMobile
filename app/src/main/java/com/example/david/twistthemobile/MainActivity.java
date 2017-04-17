package com.example.david.twistthemobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.button;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();

        Button button = (Button) findViewById(R.id.switchButton);
        assert button != null;
        button.setOnClickListener(mSwitchListener);

    }

    public void writeUser(View v) {
        EditText meditText = (EditText) findViewById(R.id.usernameText);
        String username = meditText.getText().toString();

        meditText = (EditText) findViewById(R.id.passwordText);
        String password = meditText.getText().toString();

        writeNewUser("Id", username , password);
    }

    private void writeNewUser(String userId, String name, String password) {
        User user = new User(name, password);
        mDatabase.child("users").child(userId).setValue(user);
    }

    View.OnClickListener mSwitchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, TimerActivity.class));
        }
    };

}
