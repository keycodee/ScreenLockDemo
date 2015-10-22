package com.example.key.screenlock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //开机启动一个Activity
        Intent app = new Intent(context,MainActivity.class);
        app.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(app);
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
