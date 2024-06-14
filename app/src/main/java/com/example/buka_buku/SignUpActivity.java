package com.example.buka_buku;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.buka_buku.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPass, etConfPass;
    private Button btSignup;
    private TextView txSignin;
    private FirebaseAuth cAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsername = (EditText) findViewById(R.id.etUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPassw);
        etConfPass = (EditText) findViewById(R.id.etConfPassw);
        btSignup = (Button) findViewById(R.id.btSignUp);
        txSignin = (TextView) findViewById(R.id.txSignIn);
        cAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance("https://buka-buku-919aa-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateForm()) {
                    return;
                }
                signupUser(etEmail.getText().toString(), etPass.getText().toString());
            }
        });

        txSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currUser = cAuth.getCurrentUser();
        updateUI(currUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please SignIn First!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateForm() {
        boolean result = true;

        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError("Required");
            result = false;
        } else {
            etUsername.setError(null);
        }

        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError("Required");
            result = false;
        } else {
            etEmail.setError(null);
        }

        if (TextUtils.isEmpty(etPass.getText().toString())) {
            etPass.setError("Required");
            result = false;
        } else {
            etPass.setError(null);
        }

        if (!etPass.getText().toString().equals(etConfPass.getText().toString())) {
            etConfPass.setError("Password not match");
            result = false;
        } else {
            etConfPass.setError(null);
        }

        return result;
    }

    private void signupUser(String email, String password) {
        cAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = cAuth.getCurrentUser();
                            writeNewUser(user.getUid(), etUsername.getText().toString(), email, "", "");
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void writeNewUser(String userId, String name, String email, String studentId, String phoneNumber) {
        User user = new User(name, email, studentId, phoneNumber);
        databaseReference.child("users").child(userId).setValue(user);
    }
}
