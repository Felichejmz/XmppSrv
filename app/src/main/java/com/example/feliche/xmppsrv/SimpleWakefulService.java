package com.example.feliche.xmppsrv;

import android.app.IntentService;
import android.content.Intent;
import android.os.Vibrator;

/**
 * Created by feliche on 16/09/15.
 */
public class SimpleWakefulService extends IntentService{

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SimpleWakefulService() {
        super("SimpleWakefulService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }
}
