package com.example.zeros.finaltest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zeros.finaltest.R;
import com.example.zeros.finaltest.utils.Company;

import java.util.List;

public class CompanyAdapter extends ArrayAdapter<Company> {

    Context mContext;
    List<Company> mList;
    LayoutInflater inf;

    public CompanyAdapter(Context context, List<Company> list){
        super(context, R.layout.company_list_item);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }



    public View getView(int position,View conVertView,  ViewGroup parent) {

        View row = conVertView;

        if(row==null){
            row = inf.inflate(R.layout.company_list_item,null);
        }


        ImageView logoView = row.findViewById(R.id.compayLogoImg);
        TextView nameCompany = row.findViewById(R.id.compayNameTxt);
        TextView companyId = row.findViewById(R.id.compayIdTxt);

        Company data = mList.get(position);


        Glide.with(mContext).load(data.getLogo()).into(logoView);

        nameCompany.setText(data.getName());

        companyId.setText(data.getId());




        return row;

    }
}
