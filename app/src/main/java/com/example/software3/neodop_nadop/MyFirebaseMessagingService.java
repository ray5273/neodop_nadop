package com.example.software3.neodop_nadop;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.support.constraint.Constraints.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            //helper_uid,help_info
   //         String requestname = remoteMessage.getData().get("type");
   //         Log.d("type",requestname);
            if(remoteMessage.getData().get("type").equals("request")) {
                Intent intent = new Intent(getApplicationContext(), AcceptActivity.class);
                String uid = remoteMessage.getData().get("helpee_uid");
                String helpinfo = remoteMessage.getData().get("help_info");

                Log.d("string helpinfo", uid);
                Log.d("string help", helpinfo);

                intent.putExtra("help_info", helpinfo);
                intent.putExtra("helpee_uid", uid);
                startActivity(intent);
            }else if(remoteMessage.getData().get("type").equals("match_success")){
                String uid = remoteMessage.getData().get("helper_uid");
                Intent intent = new Intent(getApplicationContext(),ConnectedActivity.class);
                intent.putExtra("useruid",uid);
                startActivity(intent);
            }

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                //scheduleJob();
            } else {
                // Handle message within 10 seconds
                //handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
