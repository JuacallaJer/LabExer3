package com.juacalla.labexer3;

import android.os.Environment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    Button displaySP, displayIS, displayIC, displayEC ,displayES, displayEP,  prev;
    TextView tvdisplay;
    FileInputStream input;
    BufferedReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvdisplay=(TextView) findViewById(R.id.textView3);
        displaySP=(Button) findViewById(R.id.button8);
        displayIS=(Button) findViewById(R.id.button9);
        displayIC=(Button) findViewById(R.id.button10);
        displayEC=(Button) findViewById(R.id.button11);
        displayES=(Button) findViewById(R.id.button12);
        displayEP=(Button) findViewById(R.id.button13);
        prev=(Button) findViewById(R.id.button14);
    }
    public void PrevAct(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void DisplaySP(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String data = preferences.getString("data","");
        //String filename = preferences.getString("filename","");
        tvdisplay.setText(" " + data );
    }
    public void DisplayIS(View view) {
        File dir = getFilesDir();
        File file = new File(dir,"TextFile1.txt");
        Read(file);

    }
    public void DisplayIC(View view) {
        File dir = getCacheDir();
        File file = new File(dir,"TextFile2.txt");
        Read(file);
    }
    public void DisplayEC(View view) {
        File dir = getExternalCacheDir();
        File file = new File(dir,"TextFile3.txt");
        Read(file);
    }
    public void DisplayES(View view) {
        File dir = getExternalFilesDir("MyDir");
        File file = new File(dir,"TextFile4.txt");
        Read(file);
    }
    public void DisplayEP(View view) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir,"TextFile5.txt");
        Read(file);
    }
    public void Read(File file)
    {
        FileInputStream fileInputStream=null;

        try {
            fileInputStream = new FileInputStream(file);
            int read=-1;
            StringBuffer stringBuffer = new StringBuffer();
            while ((read=fileInputStream.read())!=-1)
            {
                stringBuffer.append((char)read);
            }
            tvdisplay.setText(stringBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null)
            {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
