package com.example.belajarsharedprefence;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivty extends AppCompatActivity implements View.OnClickListener{

    AutoCompleteTextView etUserName, etPasword;
    TextView tvRegister, tvForgot;
    Button btLogin;
    SaveShared saveShared;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);

        etUserName = findViewById(R.id.etUserName);
        etPasword = findViewById(R.id.etPass);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgot = findViewById(R.id.tvForgot);
        btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgot.setOnClickListener(this);

        saveShared = new SaveShared(this);

        checkLogin();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogin:
                String userName = etUserName.getText().toString();
                String password = etPasword.getText().toString();

                if (TextUtils.isEmpty(userName)){
                    etUserName.setError("user name tidak boleh kosong");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    etPasword.setError("password tidak boleh kosong");
                    return;
                }
                if (!isValidEmail(userName)){
                    Toast.makeText(this, "email tidak valid", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Toast.makeText(this, "email tidak terdaftar, silahkan register", Toast.LENGTH_SHORT).show();
                validateLogin(userName, password);
                break;
            case R.id.tvRegister:
                startActivity(new Intent(LoginActivty.this, RegisterActivty.class));
                break;
            case R.id.tvForgot:
                validateForgot();
                break;
        }
    }

    private void validateForgot(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        userModel = saveShared.getUser();
        String showPasword = userModel.getPasword();
        alert.setTitle("pasword kamu adalah :"+showPasword);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    private void validateLogin(String email, String password){
        userModel = saveShared.getUser();
        String saveEmeail = userModel.getEmail();
        String savePassword = userModel.getPasword();
        if (email.equals(saveEmeail)&& password.equals(savePassword)){
            startActivity(new Intent(LoginActivty.this, MainActivity.class));
            userModel.setStatusLogin(true);
            saveShared.setUser(userModel);
            finish();
        }else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(getString(R.string.acountNotRegist));
            alert.setTitle(getString(R.string.pleaseRegist));
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(LoginActivty.this, RegisterActivty.class));
                    finish();
                }
            });
            alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.show();
        }
    }

    private void checkLogin(){
        userModel = saveShared.getUser();
        boolean statusLogin = userModel.isStatusLogin();
        if (statusLogin){
            startActivity(new Intent(LoginActivty.this, MainActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    private boolean isValidEmail(CharSequence email){
        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
