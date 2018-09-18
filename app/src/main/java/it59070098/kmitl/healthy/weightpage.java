package it59070098.kmitl.healthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class weightpage extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ArrayList<Weight> weights = new ArrayList<>();
    ListView weightlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightpage);

        getData();
        weightlist = (ListView) findViewById(R.id.weightlist);
        weights.clear();

        Button addweightbtn = (Button) findViewById(R.id.addweightbtn);
        addweightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stIntent = new Intent(getApplicationContext(), addweightpage.class);
                startActivity(stIntent);
            }
        });

        Button backbtn2 = (Button) findViewById(R.id.backbtn2);
        backbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stIntent = new Intent(getApplicationContext(), menupage.class);
                startActivity(stIntent);
            }
        });




    }

    private void getData() {
        db.collection("myfitness")
                .document(auth.getUid())
                .collection("weight")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Weight obj = document.toObject(Weight.class);
                            weights.add(obj);
                        }

                        weightadapter weightAdapter = new weightadapter(weightpage.this,
                                R.layout.addweightmini, weights);
                        weightlist.setAdapter(weightAdapter);
                    }


                });
    }
}
