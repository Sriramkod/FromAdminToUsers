package com.example.medicinereminde;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.Random;

import static com.example.medicinereminde.App.CHANNEL_1_ID;

public class Alaram extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String keyid = intent.getStringExtra("People");
        ArrayList<String> listData = new ArrayList<>();


            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            int notoficationId = intent.getIntExtra("NotifiactionId", 0);
            String message = intent.getStringExtra("todo");
            Intent mainIntent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);
        //    PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);



            Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.rm)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentTitle("Pill Reminder")
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();
            Random rr =new Random();
            int rrr = rr.nextInt(1000);
            notificationManager.notify(rrr, notification);

        /*String keyid = intent.getStringExtra("People");
        Toast.makeText(context, "Hii ra this is:"+keyid, Toast.LENGTH_SHORT).show();
   */
        //}
       /* else
        {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            int notoficationId = intent.getIntExtra("NotifiactionId", 0);
            String message = intent.getStringExtra("todo");
            Intent mainIntent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);
            listData = intent.getStringArrayListExtra("list");
            Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentTitle("From Sriram group")
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();

            notificationManager.notify(1, notification);
            try
            {
                SmsManager smsMgrVar = SmsManager.getDefault();
                //smsMgrVar.sendTextMessage("7780498174", null, "hii sriram "+message, null, null);
                //smsMgrVar.sendTextMessage("9700111520", null, "hii Aravind: "+message, null, null);
                for(int i=0;i<listData.size();i++)
                    smsMgrVar.sendTextMessage(listData.get(i).toString(), null, "hii bro: "+message, null, null);
                Toast.makeText(context, "Message Sent",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception ErrVar)
            {
                Toast.makeText(context,ErrVar.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
                ErrVar.printStackTrace();
            }
        }*/
    }
}