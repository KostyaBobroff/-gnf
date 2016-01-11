package com.example.costa.epeleptclock;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by Costa on 03.01.16.
 */
public class AnalogClockWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        super.onUpdate(context, appWidgetManager, appWidgetIds);

        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch MainActivity
            Intent intent = new Intent(context,MainActivity.class);

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

            // This is needed to make this intent different from its previous intents
            intent.setData(Uri.parse("tel:/" + (int) System.currentTimeMillis()));

            // Creating a pending intent, which will be invoked when the user
            // clicks on the widget
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Get the layout for the App Widget
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            //  Attach an on-click listener to the clock
            views.setOnClickPendingIntent(R.id.widget_aclock, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
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

         //   Toast.makeText(getApplicationContext(), "SMS Sent!",

          //          Toast.LENGTH_LONG).show();

        }
        catch (Exception e) {

         //   Toast.makeText(getApplicationContext(),

         //           "SMS faild, please try again later!",

          //          Toast.LENGTH_LONG).show();

            e.printStackTrace();

        }

    }
}
