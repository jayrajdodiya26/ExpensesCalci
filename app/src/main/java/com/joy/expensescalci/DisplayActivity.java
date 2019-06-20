package com.joy.expensescalci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class DisplayActivity extends AppCompatActivity {
    TextView medit;
    Button mBtn;
    Toolbar mdisplayTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mdisplayTool=findViewById(R.id.display_activity_toolbar);
        setSupportActionBar(mdisplayTool);
        getSupportActionBar().setTitle("ExpensesCalci");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        medit=findViewById(R.id.display_text_view);
        mBtn=findViewById(R.id.go_back_btn);

        Intent intent=getIntent();
        String tot=intent.getStringExtra("Total");
        medit.setText("Total Expenses are " + tot + " Rupees ");

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goback=new Intent(DisplayActivity.this,MainActivity.class);
                startActivity(goback);
                finish();
            }
        });

    }
}
