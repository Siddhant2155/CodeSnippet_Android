package com.example.fileio_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button writeBtn;
    Button readBtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        writeBtn = findViewById(R.id.writeBtn);
        readBtn = findViewById(R.id.readBtn);
        textView = findViewById(R.id.textView);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();
                if (data == null) {
                    return;
                }
                data += "\n";

                File dataDir = ContextCompat.getDataDir(MainActivity.this);
                File file = new File(dataDir, "abc.txt");

                try {
                    FileOutputStream fos = new FileOutputStream(file, true);
                    fos.write(data.getBytes());
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    editText.clearFocus();
                    editText.setText("");
                } catch (FileNotFoundException fnfe) {
                    Toast.makeText(MainActivity.this, "File Not Found", Toast.LENGTH_SHORT).show();
                } catch (IOException ioe) {
                    Toast.makeText(MainActivity.this, "Error While Writing", Toast.LENGTH_SHORT).show();
                }
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dataDir = ContextCompat.getDataDir(MainActivity.this);
                File file = new File(dataDir, "abc.txt");

                try {
                    FileInputStream fin = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fin);
                    BufferedReader br = new BufferedReader(isr);

                    StringBuffer sb = new StringBuffer();
                    String buffer = br.readLine();
                    while(buffer != null) {
                        sb.append(buffer + "\n");
                        buffer = br.readLine();
                    }
                    textView.setText(sb.toString());

                } catch (FileNotFoundException fnfe) {
                    Toast.makeText(MainActivity.this, "File Not Found", Toast.LENGTH_SHORT).show();
                } catch (IOException ioe) {
                    Toast.makeText(MainActivity.this, "Error While Reading", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}