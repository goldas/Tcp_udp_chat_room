package com.example.goldas_hsu.tcp_udp_chat_room;

import android.accounts.OnAccountsUpdateListener;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.nio.BufferUnderflowException;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {

    Socket s;

    private EditText textbox;
    private TextView chatbox;
    private Button send;
    private String IP, Name;
    private Integer port;
    String content;
    public Handler handler = new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);
        textbox = (EditText) findViewById(R.id.text_area);
        chatbox = (TextView) findViewById(R.id.chat_box);
        send = (Button) findViewById(R.id.send);
        Bundle bundle1 = getIntent().getExtras();
        IP = bundle1.getString("IP");
        port = bundle1.getInt("port");
        Name = bundle1.getString("Name");


        Thread t = new Thread(readData);
        t.start();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.isConnected()){
                    BufferedWriter bw;
                    try{
                        bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                        String T1 = Name +" : " + textbox.getText().toString() + "\n";
                        bw.write(T1);
                        bw.flush();
                    } catch (IOException e) {

                    }
                    textbox.setText("");
                }
            }
        });
    }
    private Runnable updateText = new Runnable() {
        @Override
        public void run() {
            chatbox.append(content + "\n");

        }
    };
    private Runnable readData = new Runnable() {
        @Override
        public void run() {
            InetAddress IA;
            try{
                IA = InetAddress.getByName(IP);
                s = new Socket(IA, port);
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                while(s.isConnected()){
                    content = br.readLine();
                    if(content!=null)
                        handler.post(updateText);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };



}

