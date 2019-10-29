package com.example.belajarsharedprefence;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingAcount extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText etNama, etEmail, etPass, etConfrimPass;
    Button btConfirm;
    UserModel userModel;
    SaveShared saveShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_acount);

        etNama = findViewById(R.id.tiEditNAma);
        etEmail = findViewById(R.id.tiEditEmail);
        etPass = findViewById(R.id.tiPaswordEdit);
        etConfrimPass = findViewById(R.id.tiPaswordEditConfirm);
        btConfirm = findViewById(R.id.btConfirm);

        saveShared = new SaveShared(this);
        setData();
        btConfirm.setOnClickListener(this);
        etEmail.setFocusable(false);
    }

    private void setData(){
        userModel = saveShared.getUser();
        String nama = userModel.getNama();
        String email = userModel.getEmail();
        String pasword = userModel.getPasword();

        etNama.setText(nama);
        etEmail.setText(email);
        etPass.setText(pasword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btConfirm:
                confirmSetting();
                break;
        }
    }

    private void confirmSetting(){
        userModel = saveShared.getUser();
        String confirmPasword = etConfrimPass.getText().toString();
        String oldPasword = userModel.getPasword();
        if (confirmPasword.equals(oldPasword)){
            userModel.setNama(etNama.getText().toString());
            userModel.setPasword(etPass.getText().toString());
            saveShared.setUser(userModel);
            Toast.makeText(this, "data berhasil diganti", Toast.LENGTH_SHORT).show();
        }
    }
}
