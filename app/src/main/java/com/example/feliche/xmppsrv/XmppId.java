package com.example.feliche.xmppsrv;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import servicio.XmppConnection;
import servicio.XmppService;

/**
 * Created by feliche on 30/08/15.
 */
public class XmppId extends Fragment implements View.OnClickListener {
    View v;
    EditText etXmId;
    EditText etXmPw;
    Button btnConnect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.login,container,false);

        String Password = PreferenceManager.getDefaultSharedPreferences(getActivity()).
                getString("xmpp_password",null);
        String Service = PreferenceManager.getDefaultSharedPreferences(getActivity()).
                getString("xmpp_user",null);

        etXmId = (EditText)v.findViewById(R.id.etXmId);
        etXmPw = (EditText)v.findViewById(R.id.etXmPw);
        btnConnect = (Button)v.findViewById(R.id.btnXmConnect);

        etXmId.setText("taxi1@feliche.ddns.net");
        etXmPw.setText("taxi1");

        btnConnect.setOnClickListener(this);

        if(!XmppService.getState().equals(XmppConnection.ConnectionState.CONNECTED)){
            btnConnect.setText("Desconectado");
        }

        if(Service != null){
            etXmId.setText(Service);
        }

        if(Password != null){
            etXmPw.setText(Password);
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnXmConnect) {
            save();
        }
    }

    public void save(){
        if(verifyXmppID(etXmId.getText().toString()) == false){
            Toast.makeText(getContext(),"Formato invalido",Toast.LENGTH_LONG).show();
            return;
        }

        if(!XmppService.getState().equals(XmppConnection.ConnectionState.CONNECTED)){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            prefs.edit()
            .putString("xmpp_user",etXmId.getText().toString())
            .putString("xmpp_password", etXmPw.getText().toString())
            .commit();

            btnConnect.setText("Conectado");
            Intent intent = new Intent(getActivity(),XmppService.class);
            getActivity().startService(intent);
        }
        else
        {
            btnConnect.setText("Desconectado");
            Intent intent = new Intent(getActivity(),XmppService.class);
            getActivity().stopService(intent);
        }
    }

    private static boolean verifyXmppID(String userId){

        try {
            String parts[] = userId.split("@");
            if (parts.length != 2)
                return false;
            if (parts[0].length() == 0) {
                return false;
            }
            if (parts[1].length() == 0) {
                return false;
            }
        }
        catch (NullPointerException e){
            return false;
        }
        return true;
    }
}
