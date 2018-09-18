package it59070098.kmitl.healthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addweightpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addweightpage);

        Button backBtn1 = (Button) findViewById(R.id.backbtn1);
        backBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stIntent = new Intent(getApplicationContext(), weightpage.class);
                startActivity(stIntent);
            }
        });

        final EditText date_weight = (EditText) findViewById(R.id.date_weight);
        final EditText weight_weight = (EditText) findViewById(R.id.weight_weight);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
//        String currentdate = sdf.format(new Date());
//        date_weight.setText();

        Button donebtn = (Button) findViewById(R.id.donebtn);
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (date_weight.getText().toString().isEmpty() || weight_weight.getText().toString().isEmpty()) {
                    Toast.makeText(addweightpage.this, "Enter date and weight...", Toast.LENGTH_LONG).show();
                } else {
                    String datestr = date_weight.getText().toString();
                    float weightflt = Float.parseFloat(weight_weight.getText().toString());
                    Toast.makeText(addweightpage.this, "Saving data...", Toast.LENGTH_LONG).show();
                    setObjectToFireBase(new Weight(datestr,weightflt,""));

                }
            }
        });

    }

    void setObjectToFireBase (Weight weight){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("myfitness")
                .document(auth.getUid())
                .collection("weight")
                .document(weight.getDate()).set(weight)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        goToWeightFragment();
                    }
                });
    }

    void goToWeightFragment () {
        Intent stIntent = new Intent(getApplicationContext(), weightpage.class);
        startActivity(stIntent);
    }


}
