package com.example.myapplication5;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String gender = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.btn_signup).setOnClickListener(onClickListener);
        findViewById(R.id.btn_man).setOnClickListener(onClickListener);
        findViewById(R.id.btn_woman).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_signup:
                    signUp();
                    break;
                case R.id.btn_man:
                    gender = "Man";
                    findViewById(R.id.btn_man).setBackgroundColor(Color.LTGRAY);
                    findViewById(R.id.btn_woman).setBackgroundColor(Color.WHITE);
                    break;
                case R.id.btn_woman:
                    gender = "Woman";
                    findViewById(R.id.btn_woman).setBackgroundColor(Color.LTGRAY);
                    findViewById(R.id.btn_man).setBackgroundColor(Color.WHITE);
                    break;
            }
        }
    };
    public void signUp(){ //회원가입
        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.passwordCheck)).getText().toString();
        String name = ((EditText)findViewById(R.id.et_name)).getText().toString();
        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0 && name.length() > 0 && gender.length() > 0){
            if(password.equals(passwordCheck)){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task){
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    UserInfo userInfo = new UserInfo(name, gender, "0");
                                    DatabaseReference myRef = database.getReference("Users");
                                    myRef.child(user.getUid()).child("UserInfo").setValue(userInfo);
                                    finish();
                                    startActivity(MainActivity.class);
                                    startToast("회원가입에 성공 하셨습니다.");
                                }
                                else {
                                    if(task.getException() != null){
                                        startToast(task.getException().toString());
                                    }
                                }
                            }
                        });
            }else {
                startToast("비밀번호가 일치하지 않습니다");
            }
        }
        else {
            startToast("사용자 정보를 입력해 주세요");
        }
    }
    private void startToast(String msg) //알림 메세지
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private  void startActivity(Class c) //화면이동
    {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}

