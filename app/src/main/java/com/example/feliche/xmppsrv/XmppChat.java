package com.example.feliche.xmppsrv;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import servicio.XmppService;

/**
 * Created by feliche on 30/08/15.
 */
public class XmppChat extends Fragment implements View.OnClickListener{
    View v;
    EditText etDestino;
    EditText etMensajes;
    Button btnEnviar;
    private WakefulBroadcastReceiver mReceiver;
    private String log;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        v = inflater.inflate(R.layout.chat,container,false);
        log = "";
        etDestino = (EditText)v.findViewById(R.id.etChatTo);
        etMensajes = (EditText)v.findViewById(R.id.etChatMensaje);
        btnEnviar = (Button)v.findViewById(R.id.btnChatSend);

        etDestino.setText("feliche@feliche.ddns.net");
        btnEnviar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        mReceiver = new WakefulBroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                switch (action){
                    case XmppService.NEW_MESSAGE:
                        String from = intent.getStringExtra(XmppService.BUNDLE_FROM_XMPP);
                        String message = intent.getStringExtra(XmppService.BUNDLE_MESSAGE_BODY);
                        log = from + ": " + message + "\n" + log;
                        Vibrator vibrator = (Vibrator)getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                        vibrator.vibrate(1000);
                        break;
                    case XmppService.NEW_ROSTER:
                        ArrayList<String> roster = intent.
                                getStringArrayListExtra(XmppService.BUNDLE_ROSTER);
                        if(roster == null){
                            return;
                        }
                        for(String s: roster){
                            log = s + "\n" + log;
                        }
                        break;
                }
                etMensajes.setText(log);
            }
        };
        IntentFilter filter = new IntentFilter(XmppService.NEW_ROSTER);
        filter.addAction(XmppService.NEW_MESSAGE);
        getActivity().registerReceiver(mReceiver, filter);
    }

    @Override
    public void onPause(){
        super.onPause();
        //getActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(XmppService.SEND_MESSAGE);
        intent.setPackage(getActivity().getPackageName());
        intent.putExtra(XmppService.BUNDLE_MESSAGE_BODY, etMensajes.getText().toString());
        intent.putExtra(XmppService.BUNDLE_TO, etDestino.getText().toString());
        getActivity().sendBroadcast(intent);
    }
}

