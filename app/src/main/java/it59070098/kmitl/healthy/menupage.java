package it59070098.kmitl.healthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class menupage extends AppCompatActivity {

    ArrayList<String> menu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menu.clear();
        menu.add("BMI");
        menu.add("Weight");
        menu.add("Sleep");
        menu.add("Sign Out");
        menu.add("Post");

        ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(
                menupage.this, android.R.layout.simple_list_item_1, menu);
        ListView menuList = (ListView) findViewById(R.id.menulist);
        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent stIntent = new Intent(getApplicationContext(), bmipage.class);
                    startActivity(stIntent);
                }else if (position == 1){
                    Intent stIntent = new Intent(getApplicationContext(), weightpage.class);
                    startActivity(stIntent);
                }else if (position == 2){
                    Intent stIntent = new Intent(getApplicationContext(), sleep.class);
                    startActivity(stIntent);
                }else if (position == 3){
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signOut();
                    Intent stIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(stIntent);
                }else if (position == 4){
                    Intent stIntent = new Intent(getApplicationContext(), post.class);
                    startActivity(stIntent);
                }
            }
        });


    }
}
