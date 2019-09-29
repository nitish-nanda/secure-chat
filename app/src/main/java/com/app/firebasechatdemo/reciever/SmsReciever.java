package com.app.firebasechatdemo.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class SmsReciever extends BroadcastReceiver {

    private static final String TAG = "smsReciver";
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "sms recieved");
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // get sms objects
                Object[] objects = (Object[]) bundle.get("pdus");
                assert objects != null;
                if (objects.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[objects.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < objects.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();
                Log.v(TAG, message);
//                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                // prevent any other broadcast receivers from receiving broadcast
                // abortBroadcast();
                Intent intent1 = new Intent("otp");
                intent1.putExtra("msg", message);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);
            }
        }
    }
}
