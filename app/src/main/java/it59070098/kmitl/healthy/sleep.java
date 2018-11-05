package it59070098.kmitl.healthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import it59070098.kmitl.healthy.Database.dbhelp;

public class sleep extends AppCompatActivity {
    private static final String TAG = "Sleep Fragment";

    private RecyclerView sleepItemList;
    private sleepAdapter sleepTimeAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<sleepitem> sleepTimeItems;

    private dbhelp db;

    public sleep() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        db = new dbhelp(sleep.this);
        sleepTimeItems = new ArrayList<>();
        sleepTimeItems.addAll(db.getAllItem());

        initRecyclerView();
        initAddSleepButton();
        initBackButton();
    }

    private void initRecyclerView(){
        sleepItemList = findViewById(R.id.sleeplist);
        sleepItemList.setHasFixedSize(true);

        sleepTimeAdapter = new sleepAdapter(sleep.this, sleepTimeItems);
        layoutManager = new LinearLayoutManager(sleep.this);

        sleepItemList.setAdapter(sleepTimeAdapter);
        sleepItemList.setLayoutManager(layoutManager);

        sleepTimeAdapter.setOnItemClickListener(new sleepAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                editItem(position);
            }
        });
    }

    private void initAddSleepButton() {
        Button addSleepButton = findViewById(R.id.addsleepbtn);
        addSleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sleep.this, sleepform.class));
            }
        });
    }

    private void initBackButton(){
        Button backButton = findViewById(R.id.sleepbackbtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sleep.this, menupage.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        updateDataSet();
    }

    private void updateDataSet(){
        sleepTimeItems.clear();
        sleepTimeItems.addAll(db.getAllItem());
        sleepTimeAdapter.notifyDataSetChanged();
    }

    private void editItem(int position){
        sleepitem items = sleepTimeItems.get(position);
        Intent intent = new Intent(sleep.this, sleepform.class);
        intent.putExtra("item", items);
        startActivity(intent);
    }
}
