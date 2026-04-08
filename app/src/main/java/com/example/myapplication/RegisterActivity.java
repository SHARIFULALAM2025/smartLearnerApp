package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    private EditText etName, etId, etEmail, etPassword;
    private Button btnRegister;
    private TextView tvBackLogin;
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // ID কানেক্ট করা
        etName = findViewById(R.id.etRegName);
        etId = findViewById(R.id.etRegId);
        etEmail = findViewById(R.id.etRegEmail);
        etPassword = findViewById(R.id.etRegPassword);
         // XML এ এই আইডি থাকতে হবে
        btnRegister = findViewById(R.id.btnRegisterSubmit);
        tvBackLogin = findViewById(R.id.tvBackToLogin);

        // ডাটাবেস ইনিশিয়ালাইজ করা
        myDb = new DatabaseHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String id = etId.getText().toString();
                String email = etEmail.getText().toString();
                String password=etPassword.getText().toString();


                if (name.isEmpty() || id.isEmpty() || email.isEmpty() || password.isEmpty() ) {
                    Toast.makeText(RegisterActivity.this, "Please fill all info", Toast.LENGTH_SHORT).show();
                } else {
                    // ১. ডাটাবেসে ডাটা ইনসার্ট করা
                    boolean isInserted = myDb.insertData(name, id, email,password);

                    if (isInserted) {
                        Toast.makeText(RegisterActivity.this, "Registration & SQL Save Successful", Toast.LENGTH_SHORT).show();

                        // ২. ড্যাশবোর্ডে যাওয়ার জন্য Intent (ডাটা পাস করা হচ্ছে)
                        Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                        intent.putExtra("u_name", name);
                        intent.putExtra("u_id", id);
                        intent.putExtra("u_email", email);
                        intent.putExtra("u_password", password);


                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Database Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}