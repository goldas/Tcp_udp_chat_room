package com.example.goldas_hsu.tcp_udp_chat_room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by goldas_hsu on 2017/10/3.
 */

public class Ip_port_activity extends Activity {
    private EditText ip, port, name;
    private Button tcp, udp;
    private String getIP, getPorts, getUDPIP, getUDPPort, getName1;
    private int getPort, getUDPPORTS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ip = (EditText)findViewById(R.id.IP_insert);
        port = (EditText)findViewById(R.id.port_insert);
        tcp = (Button)findViewById(R.id.TCP_btn);
        udp = (Button)findViewById(R.id.UDP_btn);
        name = (EditText)findViewById(R.id.name_ins);


        tcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(ip==null || port==null){
                    Toast.makeText(v.getContext(), "Get the fuck out.", Toast.LENGTH_LONG);
                }else {
                    Intent intent1 = new Intent(Ip_port_activity.this, MainActivity.class);
                    getIP = ip.getText().toString().trim();

                    getName1 = name.getText().toString().trim();
                    getPorts = port.getText().toString();
                    getPort = Integer.parseInt(getPorts);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("IP", getIP);
                    bundle1.putInt("port", getPort);
                    bundle1.putString("Name",getName1);
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                }



            }
        });
        udp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(ip==null || port==null){
                    Toast.makeText(v.getContext(), "Get the fuck out.", Toast.LENGTH_LONG);
                }else {
                    Intent intent2 = new Intent(Ip_port_activity.this, udp_part.class);
                    getUDPIP = ip.getText().toString().trim();
                    getName1 = name.getText().toString().trim();
                    getUDPPort = port.getText().toString().trim();
                    getUDPPORTS = Integer.parseInt(getUDPPort);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("IP", getUDPIP);
                    bundle2.putInt("port", getUDPPORTS);
                    bundle2.putString("Name",getName1);
                    intent2.putExtras(bundle2);
                    startActivity(intent2);
                }



            }
        });

    }

}
