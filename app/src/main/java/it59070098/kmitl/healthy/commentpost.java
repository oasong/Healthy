package it59070098.kmitl.healthy;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class commentpost extends AppCompatActivity {
    //    private ListView jsonListview;
//    private ArrayList<String> exData;
    private ProgressDialog progressDialog;
    private ArrayList<postclass> exData = new ArrayList<>();
    private ListView jsonListview;

    private String id;
    private String title;
    private String body;
    private String name;
    private String email;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentpost);

        Bundle bundle = getIntent().getExtras();
        final String text = bundle.getString("theID");

        initBackbtn();

        jsonListview = findViewById(R.id.comt_listview);
        exData.clear();

        Log.d("CHECKIN", text);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(commentpost.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Downloading..");
                progressDialog.show();
                Log.d("CHECKIN", "first step");
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Log.d("CHECKIN", "2nd Step");
                    URL url = new URL("https://jsonplaceholder.typicode.com/posts/"+text+"/comments"); ///"+text+"comments
                    URLConnection urlConnection = url.openConnection();
                    HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                    httpURLConnection.setAllowUserInteraction(false);
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    Log.d("CHECKIN", "3rd Step");

                    InputStream inputStream = null;
                    if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        inputStream = httpURLConnection.getInputStream();
                    }

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream
                            , "iso-8859-1"), 8);

                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null){
                        stringBuilder.append(line + "\n");
                    }
                    inputStream.close();
                    Log.d("JSON Result", stringBuilder.toString());

//                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
//                    JSONArray exArray = jsonObject.getJSONArray("contacts");
                    JSONArray exArray = new JSONArray(stringBuilder.toString());
                    for (int i =0 ; i< exArray.length();i++){
                        JSONObject jsonObj = exArray.getJSONObject(i);
//                        exData.add(jsonObj.getString("title"));
                        id = jsonObj.getString("postId");
                        title = jsonObj.getString("id");
                        body = jsonObj.getString("body");
                        name = jsonObj.getString("name");
                        email = jsonObj.getString("email");
                        postclass posts = new postclass(id, title, body, name, email);
                        exData.add(posts);
                    }







                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                postAdapter postAdapter = new postAdapter(commentpost.this, R.layout.postadapter, exData);
                jsonListview.setAdapter(postAdapter);
                progressDialog.dismiss();
            }
        }.execute();



    }

    private void initBackbtn() {
        Button backBtn = findViewById(R.id.comment_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(commentpost.this, post.class));
            }
        });
    }
}
