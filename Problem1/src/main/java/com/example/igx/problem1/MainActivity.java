package com.example.igx.problem1;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.provider.Telephony.Sms;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity /* implements Something1, Something2 */ {

    private SensorManager sensorManager;
    static SmsManager smsManager;
    SensorEventListener direct;
    Sensor directSensor;
    TextView direct_x, direct_y, direct_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        directSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        direct = new directListener();

        direct_x = (TextView) findViewById(R.id.directX);
        direct_y = (TextView) findViewById(R.id.directY);
        direct_z = (TextView) findViewById(R.id.directZ);


        final Button btn_getLocation = (Button) findViewById(R.id.btn_getLocation);
        final Button btn_getSensors = (Button) findViewById(R.id.btn_getSensors);
        final Button btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);

        final TextView text_selectedData = (TextView) findViewById(R.id.text_selectedData);
        final TextView text_selectedType = (TextView) findViewById(R.id.text_selectedType);
        final EditText edit_phoneNumber = (EditText) findViewById(R.id.edit_phoneNumber);

        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_selectedType.setText(btn_getLocation.getText());
            }
        });

        btn_getSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_selectedType.setText(btn_getSensors.getText());
            }
        });

        btn_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //smsManager.sendTextMessage(edit_phoneNumber.getText().toString(), null, text_selectedData.getText().toString(), null, null);
                Toast.makeText(MainActivity.this, "Successfully Sended!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onResume() {
        super.onResume();

        sensorManager.registerListener(direct, directSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(direct);    // unregister acceleration listener
    }


    private class directListener implements SensorEventListener {
        public void onSensorChanged(SensorEvent event) {
            direct_x.setText(Float.toString(event.values[0]));
            direct_y.setText(Float.toString(event.values[1]));
            direct_z.setText(Float.toString(event.values[2]));
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}