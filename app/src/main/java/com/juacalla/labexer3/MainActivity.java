package com.juacalla.labexer3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText et_data, et_filename;
    Button btnSP, btnIS, btnIC, btnEC, btnES, btnEP, btnnext;
    SharedPreferences preferences;
    FileOutputStream output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_data=(EditText) findViewById(R.id.editText);
        et_filename=(EditText) findViewById(R.id.editText2);
        btnSP=(Button) findViewById(R.id.button);
        btnIS=(Button) findViewById(R.id.button2);
        btnIC=(Button) findViewById(R.id.button3);
        btnEC=(Button) findViewById(R.id.button4);
        btnES=(Button) findViewById(R.id.button5);
        btnEP=(Button) findViewById(R.id.button6);
        btnnext=(Button) findViewById(R.id.button7);
        preferences= getPreferences(Context.MODE_PRIVATE);
    }

    public void NextAct(View view){
        Intent intent=new Intent(this, SecondActivity.class);
        startActivity(intent);


    }
    public void SharedPref(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data", et_data.getText().toString());
        editor.putString("filename", et_filename.getText().toString());
        editor.commit();
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();

    }
    public void IntStore(View view) {
        String text=et_data.getText().toString();
        File dir = getFilesDir();
        File file = new File(dir,"TextFile1.txt");
        writeData(file, text);
    }
    public void IntCache(View view) {
        String text=et_data.getText().toString();
        File dir = getCacheDir();
        File file = new File(dir,"TextFile2.txt");
        writeData(file, text);

    }
    public void ExCache(View view) {
        String text=et_data.getText().toString();
        File dir = getExternalCacheDir();
        File file = new File(dir,"TextFile3.txt");
        writeData(file, text);
        Toast.makeText(this,"Data saved to "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();

    }
    public void ExStore(View view) {
        String text=et_data.getText().toString();
        File dir = getExternalFilesDir("MyDir");
        File file = new File(dir,"TextFile4.txt");
        writeData(file, text);
        Toast.makeText(this,"Data saved to "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();

    }
    public void ExPub(View view) {
        String text= et_data.getText().toString();
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir,"TextFile5.txt");
        writeData(file, text);
        Toast.makeText(this,"Data saved to "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();


    }
    public void writeData(File file,String text)
    {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream!=null)
            {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Toast.makeText(this,"Data saved to "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();

    }
}
