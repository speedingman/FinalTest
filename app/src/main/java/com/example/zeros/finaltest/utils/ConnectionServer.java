package com.example.zeros.finaltest.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectionServer {

    //    서버통신은 대부분 하나의 서버에 요청을 날림.
//    서버의 주소 url을 변수로 보관.
    private final static String serverURL = "http://13.124.249.254/";

    //    서버 통신은 이 ConnectServer 클래스가 전담.
//    응답이 돌아온 후의 데이터 반영 : Activity들이 처리.
//    응답을 Activity단으로 넘겨주기 위한 조치.
    public interface  JsonResponseHandler {
        void onResponse(JSONObject json);
    }


    public static void  postRequestSignIn(Context context,
                                          String user_id, String password,
                                          final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

//        POST 메쏘드는 FormBody에 필요 데이터를 첨부.
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", user_id)
                .add("password", password)
                .build();

//        요청 자체를 생성. Request

        Request request = new Request.Builder()
                .url(serverURL + "auth")
                .post(requestBody)
                .build();


//        client를 이용해 실제로 서버에 접근
//        newCall 뒤로는 서버가 돌려주는 response에 대한 처리.

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("서버연결실패", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String body = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    if (handler != null) {
                        handler.onResponse(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public static void getRequestCompeny(Context context, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

//        이번에 사용하는 메쏘드는 GET

        HttpUrl.Builder urlBuilder = HttpUrl.parse(serverURL+"info/company").newBuilder();
//        urlBuilder.addEncodedQueryParameter("from", "2018-01-01");
//        urlBuilder.addEncodedQueryParameter("to", "2018-12-31");

        String requestUrl = urlBuilder.build().toString();
        Log.d("요청URL", requestUrl);

        Request request = new Request.Builder()
                .url(requestUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();

                try {
                    JSONObject json = new JSONObject(body);
                    if (handler != null) {
                        handler.onResponse(json);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });




    }


}

