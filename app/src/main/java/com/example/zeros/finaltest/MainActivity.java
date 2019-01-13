package com.example.zeros.finaltest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private android.widget.Button ListViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

        ListViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,CompanyListActivity.class);
                startActivity(intent);
//                Intent intent = new Intent(mContext, BankListActivity.class);
//                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        this.ListViewBtn = (Button) findViewById(R.id.ListViewBtn);

    }
}
