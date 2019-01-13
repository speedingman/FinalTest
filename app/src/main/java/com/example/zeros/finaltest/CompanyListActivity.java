package com.example.zeros.finaltest;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.zeros.finaltest.adapter.CompanyAdapter;
import com.example.zeros.finaltest.utils.Company;
import com.example.zeros.finaltest.utils.ConnectionServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CompanyListActivity extends BaseActivity {

    List<Company> companyList = new ArrayList<Company>();
    CompanyAdapter mAdapter;


    private android.widget.ListView companyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campany_list);


        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        mAdapter = new CompanyAdapter(mContext,companyList);
        companyListView.setAdapter(mAdapter);

        CompanysFromServer();

    }

    void CompanysFromServer(){
        ConnectionServer.getRequestCompeny(mContext, new ConnectionServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("회사목록", json.toString());


                try {
                    int code = json.getInt("code");
                    if(code == 200){
                        JSONObject data = json.getJSONObject("data");
                        JSONArray companys = data.getJSONArray("company");
                        for(int i=0; i<companys.length();i++){

                            JSONObject companyJson = companys.getJSONObject(i);

                            Company companyObject = Company.getCompanyFromJson(companyJson);

                            companyList.add(companyObject);



                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyDataSetChanged();
                            }
                        });


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void bindViews() {


        this.companyListView = (ListView) findViewById(R.id.companyListView);
    }
}
