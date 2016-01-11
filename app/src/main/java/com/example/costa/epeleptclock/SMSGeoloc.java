package com.example.costa.epeleptclock;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Costa on 04.01.16.
 */
public class SMSGeoloc extends Activity {
    private int mAppWidgetId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
          Bundle extras = intent.getExtras();

           if (extras != null) {
               mAppWidgetId = extras.getInt(
                       AppWidgetManager.EXTRA_APPWIDGET_ID,
                       AppWidgetManager.INVALID_APPWIDGET_ID);
           }
        //   }else{
        Toast.makeText(getApplicationContext(), "Yeah!",

                Toast.LENGTH_LONG).show();
       // Intent intent = getIntent();
        //  Bundle extras = intent.getExtras();
        Send(this);
    }

    public void Send(Context context){
        String num="89273801845";
        LocationService.getLocationManager(context);
        String str=new Double( LocationService.location.getLatitude()).toString();
        String crf=new Double( LocationService.location.getLongitude()).toString();
        // String sms = "SOS,Help!"+"https://www.google.ru/maps/@"+str+","+crf;//+"data=!3m1!4b1!4m2!3m1!1s0x4141a9907e7ebaaf:0x7c46bedfd387addc";;
        //    String sms="https://www.google.ru/maps/@"+str+","+crf+".16z/data=!4m2!3m1!1s0x4141a9907e7ebaaf:0x7c46bedfd387addc";
        String sms="https://maps.yandex.ru/"+crf+","+str;
        try {

            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(num, null, sms, null, null);

            Toast.makeText(getApplicationContext(), "SMS Sent!",

                    Toast.LENGTH_LONG).show();

        }
        catch (Exception e) {

            Toast.makeText(getApplicationContext(),

                    "SMS faild, please try again later!",

                    Toast.LENGTH_LONG).show();

            e.printStackTrace();

        }

    }
    }

