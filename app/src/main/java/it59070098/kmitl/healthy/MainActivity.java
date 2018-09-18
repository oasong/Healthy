package it59070098.kmitl.healthy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkCurrentUser();

        final EditText userid = (EditText) findViewById(R.id.userid);
        final EditText userpassword = (EditText) findViewById(R.id.userpassword);

        TextView registerlink = (TextView) findViewById(R.id.registerlink);

        Button loginbtn = (Button) findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useridstr = userid.getText().toString();
                String userpasswordstr = userpassword.getText().toString();

                Intent startIntent = new Intent(getApplicationContext(), menupage.class);
                if (useridstr.isEmpty() || userpasswordstr.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter your email or password", Toast.LENGTH_LONG).show();
//                    startActivity(startIntent);
                }else{
                    signIn(useridstr, userpasswordstr);
                    Toast.makeText(MainActivity.this, "Please wait...", Toast.LENGTH_LONG).show();

                }

            }
        });

        registerlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), registerpage.class);
                startActivity(startIntent);
            }
        });
    }

    void signIn(String email, String password) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "fail - " + e.getMessage(), Toast.LENGTH_LONG).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                checkVerified();
            }
        });
    }

    void checkVerified() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser().isEmailVerified()) {
            goToMenu();
        } else {
            firebaseAuth.signOut();
            Toast.makeText(MainActivity.this, "Please verify your email", Toast.LENGTH_LONG).show();

        }
    }

    private void goToMenu() {
        Intent startIntent = new Intent(getApplicationContext(), menupage.class);
        startActivity(startIntent);
    }

    void checkCurrentUser(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null) {
            goToMenu();
        }
    }
}
