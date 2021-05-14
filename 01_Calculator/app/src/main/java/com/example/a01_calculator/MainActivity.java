package com.example.a01_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b0;
    Button clear;
    Button equal;

    Button plus;
    Button minus;
    Button div;
    Button mul;

    EditText editText;

    public static final String TAG = "cal_tag";
    String t1 = "";
    String sign = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b0 = findViewById(R.id.button0);

        clear = findViewById(R.id.button10);
        equal = findViewById(R.id.button11);

        plus = findViewById(R.id.buttonadd);
        minus = findViewById(R.id.buttonminus);
        mul = findViewById(R.id.buttonmultiply);
        div = findViewById(R.id.buttondivide);

        editText = findViewById(R.id.editTextNumber);

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               set
//            }
//        });

            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                t1 = editText.getText().toString();
                editText.setText("");
                sign = "+";
            }
        });
        minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                t1 = editText.getText().toString();
                editText.setText("");
                sign = "-";
            }
        });
        mul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                t1 = editText.getText().toString();
                editText.setText("");
                sign = "*";
            }
        });
        div.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                t1 = editText.getText().toString();
                editText.setText("");
                sign = "/";
            }
        });
    }

    protected void setText(String v) {
        editText.setText(editText.getText().toString() + v);
    }

    public void showResult(View v) {
        if( sign.isEmpty()) {
            return;
        }
        String value = editText.getText().toString();
        int a = Integer.valueOf(t1);
        int b = Integer.valueOf(value);
        int ans = 0;
        if(sign == "+") {
            ans = a + b;
        } else if( sign == "-") {
            ans = a - b;
        } else if (sign == "*") {
            ans = a * b;
        } else if (sign == "/") {
            ans = a / b;
        }
        editText.setText(Integer.toString(ans));
        sign = "";
    }

    @Override
    public void onClick(View v) {
        if (v == b1) {
            setText("1");
        } else if (v == b2) {
            setText("2");
        } else if (v == b2) {
            setText("2");
        } else if (v == b3) {
            setText("3");
        } else if (v == b4) {
            setText("4");
        } else if (v == b5) {
            setText("5");
        } else if (v == b6) {
            setText("6");
        } else if (v == b7) {
            setText("7");
        } else if (v == b8) {
            setText("8");
        } else if (v == b9) {
            setText("9");
        } else if (v == b0) {
            setText("0");
        }
    }
}