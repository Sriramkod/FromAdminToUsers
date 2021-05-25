package com.example.firedetection;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class FcmInstanceIdService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        //super.onNewToken(s);
        String recent_token = String.valueOf(FirebaseMessaging.getInstance().getToken());
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                       // String msg = getString(getString(R.string.FCM_TOKEN), token);
                      // String msg = getString(R.string.FCM_TOKEN),token);
                         Log.d("..............", token);
       //                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(getString(R.string.FCM_TOKEN),s);
                        editor.commit();
                    }
                });

        Log.d("NEW_TOKEN",s);
    }

}
