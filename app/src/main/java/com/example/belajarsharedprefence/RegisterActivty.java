package com.example.belajarsharedprefence;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivty extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout tInputName, tInputPass, tInputEmail, tIConfirmPass;
    AutoCompleteTextView etName, etEmail, etPass, etConfirmPass;
    RadioButton rbMale, rbFemale;
    RadioGroup rgGender;
    Button btRegister;
    CheckBox cbAggre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activty);

        etName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etConfirmPass = findViewById(R.id.etPassConfirm);
        rgGender = findViewById(R.id.rgGender);
        rbFemale = findViewById(R.id.rbFemale);
        rbMale = findViewById(R.id.rbMale);
        cbAggre = findViewById(R.id.cbAgre);
        btRegister = findViewById(R.id.btRegister);

        btRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btRegister){
            String nama = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPass.getText().toString();
            String kelamin = String.valueOf(rgGender.getCheckedRadioButtonId());
            String agree = cbAggre.getText().toString();
            String confirm = etConfirmPass.getText().toString();

            if (TextUtils.isEmpty(nama)){
                etName.setError("nama tidak boleh kosong");
                return;
            }

            if (TextUtils.isEmpty(email)){
                etEmail.setError("email tidak boleh kosong");
                return;
            }

            if (TextUtils.isEmpty(password)){
                etPass.setError("password tidak boleh kosong");
                return;
            }

            if (TextUtils.isEmpty(confirm)){
                etConfirmPass.setError("confirm password tidak boleh kosong");
                return;
            }

            if (!password.equals(confirm)){
                Toast.makeText(this, "password tidak sama", Toast.LENGTH_SHORT).show();
                return;
            }

            invalidUser(nama, email, password, kelamin, agree);

        }
    }
    private void invalidUser(String nama, String email, String password, String kelamin, String agree){
        SaveShared userShare = new SaveShared(this);
        UserModel userModel = userShare.getUser();

        String saveEmail = userModel.getEmail();
        if (email.equals(saveEmail)){
            Toast.makeText(this, "email sudah terdaftar, silahkan gunakan email yang lain", Toast.LENGTH_SHORT).show();
        }else {
            saveUser(nama, email, password, kelamin, agree);
        }
    }

    private void saveUser(String nama, String email, String password, String kelamin, String agree){
        SaveShared userShared = new SaveShared(this);
        UserModel userModel = new UserModel();
        userModel.setNama(nama);
        userModel.setEmail(email);
        userModel.setPasword(password);
        userModel.setKelamin(kelamin);
        userModel.setAgrew(agree);

        userShared.setUser(userModel);
        //Toast.makeText(this, getString(R.string.succesRegister), Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivty.this);
        alert.setTitle(getString(R.string.succesRegister));
        alert.setTitle(getString(R.string.cautionSucces));
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(RegisterActivty.this, LoginActivty.class));
                finish();
            }
        });
        alert.show();

    }
}
