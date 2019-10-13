package com.elephant.examplequiza;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashSheetAPI extends AsyncTask<String, Void , String> {

    private Listener listener;
    private String urlSt;

    public SplashSheetAPI(String urlSt){
        this.urlSt = urlSt;
    }

    @Override
    protected String doInBackground(String... strings) {
        // 使用するサーバーのURLに合わせる

        HttpURLConnection httpConn = null;
        String result = null;
        InputStream in;


        try{
            // URL設定
            URL url = new URL(urlSt);
            // HttpURLConnection
            httpConn = (HttpURLConnection) url.openConnection();

            // request Method
            httpConn.setRequestMethod("GET");

            // no Redirects trueにしたい
            httpConn.setInstanceFollowRedirects(true);

            // データを書き込む
            httpConn.setDoOutput(false);

            // 時間制限
            httpConn.setReadTimeout(10000);
            httpConn.setConnectTimeout(20000);
            // 接続
            httpConn.connect();


            final int status = httpConn.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                // レスポンスを受け取る処理等

                String str_json = "";
                in = httpConn.getInputStream();
                InputStreamReader objReader = new InputStreamReader(in);
                BufferedReader objBuf = new BufferedReader(objReader);
                StringBuilder strBuilder = new StringBuilder();
                String sLine;
                while((sLine = objBuf.readLine()) != null){
                    strBuilder.append(sLine);
                }
                str_json = strBuilder.toString();
                in.close();

                return str_json;
            }
            else{
                result="status="+String.valueOf(status);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
        return result;


    }

    // 非同期処理が終了後、結果をメインスレッドに返す
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (listener != null) {
            listener.onSuccess(result);
        }
    }

    void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onSuccess(String result);
    }


}
