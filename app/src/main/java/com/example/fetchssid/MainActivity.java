package com.example.fetchssid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fetchssid.DataStorage.DatabaseHandler;
import com.example.fetchssid.Model.SsidData;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText enterSSID, enterPassword;
    Button addBtn, recoverBtn;
    TextView Display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterSSID = findViewById(R.id.SSID_et);
        enterPassword = findViewById(R.id.Pass_et);
//        enterSSID.setText("SPTLYT_2023_");
        addBtn = findViewById(R.id.addBtn);
        recoverBtn = findViewById(R.id.recoverBtn);

        Display = findViewById(R.id.Display);



        addBtn.setOnClickListener(view -> {

            String AccessCode = enterPassword.getText().toString();
            String SSIDCode = enterSSID.getText().toString();


            DatabaseHandler db = new DatabaseHandler(this);

            Log.d("insert","Inserting");

            db.AddData(new SsidData(AccessCode,SSIDCode));

            Log.d("Reading","Reading Data");
            List<SsidData> data = db.getData();
            for (SsidData sd:data){
                String log = "Id:" + sd.getId() + ",AccessCode: "
                        + sd.getAccessCode() + ", SSID: " +
                        sd.getSSID();
                Display.setText(log);
                Log.d("Data: ",log);

            }
        });

        recoverBtn.setOnClickListener(view -> {
            String ssId = enterSSID.getText().toString();
            DatabaseHandler db = new DatabaseHandler(this);
            List<SsidData> data = db.fetchAccessCode(ssId);
//            List<SsidData> data = db.getData();
            for (SsidData sd:data){
                String log = sd.getAccessCode();
                enterPassword.setText(log);
                Display.setText(log);

                Log.d("test Acces",log);}
            Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show();

        });

    }
}