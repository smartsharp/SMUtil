package com.example.smutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sm.SM2Util;
import com.example.sm.SM3Util;

public class MainActivity extends AppCompatActivity {


    private Button button;
    private Button button1;
    private Button button2;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    private String content;
    private byte[] cipherBytes;

    String privateKey = "cKwtbFCaURkAoCREdYOIKzngWwybP8er4gYz234gOqY=";
    String publicKey = "BNa6GBPM3SPpYXze00+OcoltK08XbeWifpzF1sEbpGR00P3ae5rCw7fWXNCmW6FHVCgciDHoJybVKxPmCJLcqKg=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.bt);
        textView = findViewById(R.id.tv);

        button1 = findViewById(R.id.bt1);
        button2 = findViewById(R.id.bt2);
        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("SM3加密结果 : " + SM3Util.getSM3("qwe"));
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = SM2Util.getEncryption("qwe", privateKey, publicKey);
                textView1.setText("SM2加密结果 : " + content);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText("SM2解密结果 : " + SM2Util.getDecrypt(content, privateKey, publicKey));
            }
        });
    }

}

