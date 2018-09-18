package it59070098.kmitl.healthy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.app.PendingIntent.getActivity;

public class registerpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText useridregister = (EditText) findViewById(R.id.useridregister);
        final EditText userpasswordregister = (EditText) findViewById(R.id.userpasswordregister);
        final EditText reuserpasswordregister = (EditText) findViewById(R.id.reuserpasswordregister);



        final Button registerbtn = (Button) findViewById(R.id.registerbtn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useridstr = useridregister.getText().toString();
                String userpasswordstr = userpasswordregister.getText().toString();
                String reuserpasswordstr = reuserpasswordregister.getText().toString();
                if (useridstr.isEmpty() || userpasswordstr.isEmpty() ||  reuserpasswordstr.isEmpty()){
                    Toast.makeText(registerpage.this, "กรุณาระบุข้อมูลให้ครบ", Toast.LENGTH_LONG).show();
                }else if ( !userpasswordstr.equals(reuserpasswordstr) ){
                    Toast.makeText(registerpage.this, "password not match", Toast.LENGTH_LONG).show();
                }else if ( userpasswordstr.equals(reuserpasswordstr) && reuserpasswordstr.length() < 6 ){
                    Toast.makeText(registerpage.this, "Password length should be greater than 5", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(registerpage.this,"coming to login page...",Toast.LENGTH_LONG).show();
                    createAccount(useridstr,userpasswordstr);
                }
            }
        });




    }

    void createAccount(String user, String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d("Register", ""+authResult);
                FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
                sendVerifiedEmail(curUser);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.d("Register", "Fail : "+e);
                Toast.makeText(registerpage.this, e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    void sendVerifiedEmail(final FirebaseUser _user) {
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener < Void > () {
            @Override public void onSuccess(Void aVoid) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                Toast.makeText(registerpage.this,"Send Verified Email",Toast.LENGTH_LONG).show();

                goToLoginFragment();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override public void onFailure(@NonNull Exception e) {
                Toast.makeText(registerpage.this,e.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    void goToLoginFragment(){
        Intent sIntent = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(sIntent);
    }
}
