package com.example.zeros.finaltest.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Company implements Serializable {


    private int id;
    private String name;
    private String logo;

        public static Company getCompanyFromJson(JSONObject comJson){
        Company company = new Company();


            try {
                company.setId(comJson.getInt("id"));
                company.setName(comJson.getString("name"));
                company.setLogo("logo");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return company;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


}
