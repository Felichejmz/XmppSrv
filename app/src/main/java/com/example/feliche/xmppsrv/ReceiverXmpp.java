package com.example.feliche.xmppsrv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;

import servicio.XmppService;

public class ReceiverXmpp extends BroadcastReceiver {
    private String log;
    /*
    static final String TAG = "smack.BroadcastReceiver";
    private static int networkType = -1;

    public static void initNetworkStatus(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        networkType = -1;
        if(networkInfo != null){
            Log.d(TAG, "Init: Active NetworkInfo: "+ networkInfo.toString());
            if(networkInfo.isConnected()){
                networkType = networkInfo.getType();
            }
        }
        Log.d(TAG,"initiNetworkStatus -> " + networkType);
    }
    */
    public ReceiverXmpp() {
        log = "";
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        /*
        Log.d(TAG, "onReceiver " + intent);

        if(intent.getAction().equals(Intent.ACTION_SHUTDOWN)){
            Log.d(TAG, "System shutdown, stopping SAMCK");
            Intent xmppServiceIntent = new Intent(context, XmppService.class);
            context.stopService(xmppServiceIntent);
        }else
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            boolean connstartup = PreferenceManager.getDefaultSharedPreferences(context)
                    .getBoolean(PreferenceConstants.CONN_STARTUP, false);
            if (!connstartup) // ignore event, we are not running
                return;
            // prepare intent
            Intent xmppServiceIntent = new Intent(context, XmppService.class);

            // there are three possible situations here: disconnect, reconnect, connection change
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            boolean isConnected = (networkInfo != null) && (networkInfo.isConnected() == true);
            boolean wasConnected = (networkType != -1);
            if (wasConnected && !isConnected) {
                Log.d(TAG, "we got disconnected");
                networkType = -1;
                xmppServiceIntent.setAction("disconnect");
            } else
            if (isConnected && (networkInfo.getType() != networkType)) {
                Log.d(TAG, "we got (re)connected: " + networkInfo.toString());
                networkType = networkInfo.getType();
                xmppServiceIntent.setAction("reconnect");
            } else
            if (isConnected && (networkInfo.getType() == networkType)) {
                Log.d(TAG, "we stay connected, sending a ping");
                xmppServiceIntent.setAction("ping");
            } else
                return;
            context.startService(xmppServiceIntent);
        }
        */
    }
}
