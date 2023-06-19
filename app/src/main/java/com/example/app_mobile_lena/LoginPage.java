package com.example.app_mobile_lena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;

    public class LoginPage extends AppCompatActivity {

    TextView tvForgetPassword;
    ImageButton btnBack;
    EditText etEmail;
    EditText etPassword;
    Button btnLogin;


    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private void startForgotPasswordActivity(View view){
        Intent intent = new Intent(view.getContext(),ForgetPasswordActivity.class);
        startActivity(intent);
    }

    private void startHomeActivity(View view){

    }


    private void logIn(String email, String password){
        User user = new User();
        CollectionReference usersRef = db.collection("users");
        Query query = usersRef
                .whereEqualTo("email", email)
                .whereEqualTo("password",password);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot : task.getResult()){
                        if(email.equals(documentSnapshot.getString("email"))){
                            Gson gson = new Gson();
                            String email = documentSnapshot.getString("email");
                            String password = documentSnapshot.getString("password");
                            String dob = documentSnapshot.getString("dob");
                            ArrayList<CartItems> cart =  (ArrayList<CartItems>) documentSnapshot.get("cart");
                            user.setEmail(email);
                            user.setPassword(password);
                            user.setID(documentSnapshot.getId());
                            user.setCart(cart);
                            Log.d("IM IN TASK",user.getEmail().toString());
                            Intent intent = new Intent(LoginPage.this,MainActivity.class);
                            SharedPreferences sharedPref = getSharedPreferences("CURRENT_USER",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            String userStr = gson.toJson(user);
                            Log.d("USER LOGGED IN",userStr);
                            editor.putString("userObject",userStr);
                            editor.commit();
                            startActivity(intent);
                            finish();
                        }
                    }
                }
                if(task.getResult().size() == 0 ){

                }
            }
        });
    }

    private boolean isValid(String email, String password){
        if(email.isEmpty() || password.isEmpty()) return false;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnBack = findViewById(R.id.btnBack);

        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.editTextTextEmailAddress);
        etPassword = findViewById(R.id.editTextTextPassword);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForgotPasswordActivity(view);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                logIn(email,password);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }
}
