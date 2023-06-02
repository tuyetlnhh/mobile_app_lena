package com.example.app_mobile_lena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPage extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private boolean isValidEmail(String email) {
        // Biểu thức chính quy để kiểm tra định dạng email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Tạo đối tượng Pattern từ biểu thức chính quy
        Pattern pattern = Pattern.compile(emailRegex);

        // Tạo đối tượng Matcher để so khớp địa chỉ email với biểu thức chính quy
        Matcher matcher = pattern.matcher(email);

        // Kiểm tra xem địa chỉ email có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }
    private boolean validate(users user){
        if(!isValidEmail(user.getEmail()))
            return false;
        if(user.getGender().equals(""))
            return false;
        if(user.getPassword().length() < 6)
            return false;
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button myButton = findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText inpEmail = findViewById(R.id.editTextTextEmailAddress);
                EditText inpPassword = findViewById(R.id.editTextTextPassword);
                EditText inpDate = findViewById(R.id.editTextDate);
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                CheckBox checkBox = findViewById(R.id.checkBox3);
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                Log.d("TAG", "GENDER: " + Integer.toString(selectedRadioButtonId));

                String email = inpEmail.getText().toString();
                String password = inpPassword.getText().toString();
                String date = inpDate.getText().toString();
                String gender = "";

                switch (selectedRadioButtonId){
                    case 2131231190:
                        gender = "Nam";
                        break;
                    case 2131231192:
                        gender = "Nữ";
                        break;
                    case 2131231193:
                        gender = "Khác";
                    default:
                        gender = "";
                        break;
                }


                // Create a new user with a first and last name
                Map<String, Object> dbUser= new HashMap<>();
                dbUser.put("dob", date);
                dbUser.put("email", email);
                dbUser.put("password", password);
                dbUser.put("gender", gender);

                users user = new users();
                user.setDate(date);
                user.setEmail(email);
                user.setPassword(password);
                user.setGender(gender);
                if(validate(user) && checkBox.isChecked()){
                    db.collection("users")
                            .add(dbUser)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Toast.makeText(getApplicationContext(), "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(v.getContext(), pre_login.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error adding document", e);
                                }
                            });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Vui lòng kiểm tra lại và điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }
// Add a new document with a generated ID

            }
        });




    }
}