package com.example.belajarsharedprefence;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.belajarsharedprefence.model.Location;
import com.example.belajarsharedprefence.servie.ApiClient;
import com.example.belajarsharedprefence.servie.BaseApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    UserModel userModel;
    SaveShared saveShared;
    BaseApiService apiService;
    TextView tvFajr, tvSyuruk, tvDuhr, tvAsar, tvMagrib, tvIsya, tvTanggal, tvLocation;
    Context mContex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContex = this;

        saveShared = new SaveShared(this);
        tvFajr = findViewById(R.id.tvFajr);
        tvTanggal = findViewById(R.id.tvCalendar);
        tvLocation = findViewById(R.id.tvLocation);
        tvSyuruk = findViewById(R.id.tvSunrise);
        tvDuhr = findViewById(R.id.tvDhuhr);
        tvAsar = findViewById(R.id.tvAsr);
        tvMagrib = findViewById(R.id.tvMaghrib);
        tvIsya = findViewById(R.id.tvIsha);
        Button btOk = findViewById(R.id.btOk);
        btOk.setOnClickListener(this);

        apiService = ApiClient.getJadwal();
        getMethodJadwal("jonggol");
        getCurrentDate();

    }

    private void getMethodJadwal(String cityName){
        Call<ResponseBody> callResponse = apiService.getJadwalShalat(cityName);
        callResponse.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("status").equals("OK")){
                            String pagi =jsonRESULTS.getJSONObject("data").getString("Fajr");
                            String syuruk =jsonRESULTS.getJSONObject("data").getString("Sunrise");
                            String duhur =jsonRESULTS.getJSONObject("data").getString("Dhuhr");
                            String asar =jsonRESULTS.getJSONObject("data").getString("Asr");
                            String magrib =jsonRESULTS.getJSONObject("data").getString("Maghrib");
                            String isya =jsonRESULTS.getJSONObject("data").getString("Isha");
                            String location =jsonRESULTS.getJSONObject("location").getString("address");
                            //Toast.makeText(MainActivity.this, pagi, Toast.LENGTH_SHORT).show();
                            tvFajr.setText(pagi+" AM");
                            tvSyuruk.setText(syuruk+" AM");
                            tvDuhr.setText(duhur+" AM");
                            tvAsar.setText(asar+" PM");
                            tvMagrib.setText(magrib+" PM");
                            tvIsya.setText(isya+" PM");
                            tvLocation.setText(location);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "gagal mendapat response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failur", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyy");
        tvTanggal.setText(simpleDateFormat.format(new Date()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout:
                userModel = saveShared.getUser();
                userModel.setStatusLogin(false);
                saveShared.setUser(userModel);
                startActivity(new Intent(MainActivity.this, LoginActivty.class));
                finish();
                break;
            case R.id.accountSetting:
                startActivity(new Intent(MainActivity.this, SettingAcount.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btOk:
                final EditText etCity = new EditText(mContex);
                AlertDialog.Builder aler = new AlertDialog.Builder(this);
                aler.setTitle("Cit Name").setMessage("masukan nama kota tanpa spasi").setView(etCity);
                aler.setPositiveButton("Change City", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String city = etCity.getText().toString();
                        getMethodJadwal(city);
                    }
                });
                aler.show();
        }

    }
}
