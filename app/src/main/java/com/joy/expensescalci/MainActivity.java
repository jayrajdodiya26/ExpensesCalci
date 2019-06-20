package com.joy.expensescalci;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Toolbar mtool;
    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private EditText ed4;
    private TextView tv1;
    private Button cal;
    private TextView tv2;
    private Integer a;
    private Integer b;
    private Integer c;
    private Integer d;
    private Integer e;
    private Integer f;

    //SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtool=findViewById(R.id.main_app_layout_toolbar);
        setSupportActionBar(mtool);
        getSupportActionBar().setTitle("ExpensesCalci");


        ed1 =(EditText)findViewById(R.id.food_txt);
        ed2=(EditText)findViewById(R.id.shop_txt);
        ed3=(EditText)findViewById(R.id.fuel_txt);
        ed4=(EditText)findViewById(R.id.other_txt);
        tv1=(TextView)findViewById(R.id.exp_display);
        cal=(Button)findViewById(R.id.cal_btn);
       // sharedPreferences = getSharedPreferences("Expences", Context.MODE_PRIVATE);
        //tv1.setText("Previous Expenses was " + String.valueOf(sharedPreferences.getInt("Exp",0)));
          readData();
         // realtotal();




        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateField();




                int i=Integer.valueOf(ed1.getText().toString())+Integer.valueOf(ed2.getText().toString())+
                        Integer.valueOf(ed3.getText().toString())+Integer.valueOf(ed4.getText().toString());

                Toast.makeText(MainActivity.this,"Your Expenses are:" + String.valueOf(i),Toast.LENGTH_LONG).show();
               tv1.setText( String.valueOf(i));
                saveData();

               // c=a+b;
               // saveTotal();






                //SharedPreferences.Editor edit=sharedPreferences.edit();

                //edit.putInt("Exp",i);
                //edit.apply();

            }
        });
    }

    private void ValidateField() {
        if(TextUtils.isEmpty(ed1.getText().toString()) | TextUtils.isEmpty(ed2.getText().toString()) | TextUtils.isEmpty(ed3.getText().toString()) |
        TextUtils.isEmpty(ed4.getText().toString())){
            Toast.makeText(MainActivity.this,"Please enter fields correctly. Try Again!!",Toast.LENGTH_LONG).show();
        }
        else {
            TransferData();
        }
    }

    private void TransferData() {
       int a=Integer.parseInt(ed1.getText().toString());
       int b=Integer.parseInt(ed2.getText().toString());
       int c=Integer.parseInt(ed3.getText().toString());
       int d=Integer.parseInt(ed4.getText().toString());

       int e=a+b+c+d;
       String cd=String.valueOf(e);
       Intent transfer=new Intent(MainActivity.this,DisplayActivity.class);
       transfer.putExtra("Total",cd);
       startActivity(transfer);
    }

    public void saveData(){
        //e=Integer.parseInt(tv1.getText().toString());
        //f=d+e;
       // String str=String.valueOf(f);


        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput("exp2.txt",MODE_PRIVATE);
            fileOutputStream.write(tv1.getText().toString().getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readData(){
        try {
            FileInputStream fileInputStream=openFileInput("exp2.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String data=bufferedReader.readLine();

            if(!data.isEmpty()){
                tv1.setText(data);


            }
            else
                {
                tv1.setText("0");
            }
            d=Integer.parseInt(tv1.getText().toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //total expenses calculator
    public void saveTotal(){


        a=Integer.parseInt(tv1.getText().toString());


             c=a+b;
        try {
            FileOutputStream fo=openFileOutput("total.txt",MODE_PRIVATE);
            fo.write(c);
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void realtotal(){
        try {
            FileInputStream fi = openFileInput("total.txt");
            InputStreamReader ir=new InputStreamReader(fi);
            BufferedReader bf1=new BufferedReader(ir);
            String total=bf1.readLine();
            b=Integer.parseInt(total);


            if(!total.isEmpty()){
                tv2.setText(b);


            }
            else{
                tv2.setText("0");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
