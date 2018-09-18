package it59070098.kmitl.healthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bmipage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmipage);

        final EditText heightbmi = (EditText) findViewById(R.id.heightbmi);
        final EditText weightbmi = (EditText) findViewById(R.id.weightbmi);
        final TextView bmishow = (TextView) findViewById(R.id.bmishow);

        Button bmicalbtn = (Button) findViewById(R.id.bmicalbtn);
        bmicalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float h = Integer.parseInt(heightbmi.getText().toString());
                float w = Integer.parseInt(weightbmi.getText().toString());
                float result = w / ((h/100)*(h/100));
                String resultformat = String.format("%.2f", result);
                bmishow.setText(resultformat+"");

            }
        });

        Button backbtn = (Button) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stIntent = new Intent(getApplicationContext(), menupage.class);
                startActivity(stIntent);
            }
        });
    }
}
