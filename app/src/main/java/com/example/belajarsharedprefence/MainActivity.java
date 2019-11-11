package com.example.belajarsharedprefence;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.belajarsharedprefence.servie.ApiClient;
import com.example.belajarsharedprefence.servie.BaseApiService;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UserModel userModel;
    SaveShared saveShared;
    TextView tvFajr, tvSyuruk, tvDhur, tvAsr, tvMagrib, tvIsya, tvTanggal, tvLocation,
            tvTime;
    TextView txtfajr, txtsunrise, txtduhr, txtAsr, txtMagrib, txtIsya;
    ImageView ivBackgound;
    BaseApiService apiService;
    ProgressBar progressBar;

    SimpleDateFormat inputParser;
    Date dateFajar, dateSunrise, dateDuhur, dateAsar, dateMagrib, dateIsya;
    String fajr, syuruk, duhur, asar, magrib, isya;

    ScrollView svMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //membuat local time berdasarkan lokasi/country
        Locale indonesia = new Locale("in", "ID");
        inputParser = new SimpleDateFormat("HH:mm");

        tvFajr = findViewById(R.id.tvFajr);
        tvTanggal = findViewById(R.id.tvCalendar);
        tvLocation = findViewById(R.id.tvLocation);
        tvSyuruk = findViewById(R.id.tvSunrise);
        tvDhur = findViewById(R.id.tvDhuhr);
        tvAsr = findViewById(R.id.tvAsr);
        tvMagrib = findViewById(R.id.tvMaghrib);
        tvIsya = findViewById(R.id.tvIsha);
        tvTime = findViewById(R.id.tvTime);
        ivBackgound = findViewById(R.id.ivBg);
        svMain = findViewById(R.id.svMain);


        //deklarasi untuk view texnya
        txtfajr = findViewById(R.id.fajr);
        txtsunrise = findViewById(R.id.sunrise);
        txtduhr = findViewById(R.id.dhuhr);
        txtAsr = findViewById(R.id.asr);
        txtMagrib = findViewById(R.id.maghrib);
        txtIsya = findViewById(R.id.isha);

        progressBar = findViewById(R.id.spin_kit);
        Sprite thre = new ThreeBounce();
        progressBar.setIndeterminateDrawable(thre);

        apiService = ApiClient.getJadwal();
        getJadwalSholatMethiod("Bekasi");
        ImageView btChange = findViewById(R.id.btOk);
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etCityName = new EditText(MainActivity.this);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("City Name").setMessage("masukan nama kota yang di inginkan").setView(etCityName);
                alert.setPositiveButton("Change City", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String city = etCityName.getText().toString();
                        getJadwalSholatMethiod(city);
                    }
                });
                alert.show();
            }
        });

        showDynamisTime();
    }

    private void showDynamisTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMM yyy");
        tvTanggal.setText(simpleDateFormat.format(new Date()));

        //setting dynamisnya, mengikuti waktu
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY);
        if (time >= 4 && time <= 5) {
            tvTime.setText(getString(R.string.Fajr));
            ivBackgound.setImageResource(R.drawable.pagi_bg);
            txtfajr.setTextColor(getResources().getColor(R.color.hijouTua));
            tvFajr.setTextColor(getResources().getColor(R.color.hijouTua));
        } else if (time >= 5 && time <= 6) {
            tvTime.setText(getString(R.string.Sunrise));
            txtsunrise.setTextColor(getResources().getColor(R.color.hijouTua));
            tvSyuruk.setTextColor(getResources().getColor(R.color.hijouTua));
        } else if (time >= 19 && time <= 24) {
            tvTime.setText(getString(R.string.isyaa));
            ivBackgound.setImageResource(R.drawable.malam_bg);
            tvIsya.setTextColor(getResources().getColor(R.color.hijouTua));
            txtIsya.setTextColor(getResources().getColor(R.color.hijouTua));
        }
    }

    private void getJadwalSholatMethiod(String namaKota) {
        Call<ResponseBody> requestApi = apiService.getJadwalShalat(namaKota);
        requestApi.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("status").equals("OK")) {
                            progressBar.setVisibility(View.GONE);
                            svMain.setVisibility(View.VISIBLE);
                            fajr = jsonObject.getJSONObject("data").getString("Fajr");
                            syuruk = jsonObject.getJSONObject("data").getString("Sunrise");
                            duhur = jsonObject.getJSONObject("data").getString("Dhuhr");
                            asar = jsonObject.getJSONObject("data").getString("Asr");
                            magrib = jsonObject.getJSONObject("data").getString("Maghrib");
                            isya = jsonObject.getJSONObject("data").getString("Isha");
                            String addres = jsonObject.getJSONObject("location").getString("address");

                            //set data to textview
                            tvFajr.setText(fajr + " AM");
                            tvSyuruk.setText(syuruk + " AM");
                            tvDhur.setText(duhur + " AM");
                            tvAsr.setText(asar + " PM");
                            tvMagrib.setText(magrib + " PM");
                            tvIsya.setText(isya + " PM");
                            tvLocation.setText(addres);
                            compareTime();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "response gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Bad Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void compareTime() {
        Calendar calendarNow = Calendar.getInstance();
        int hour = calendarNow.get(Calendar.HOUR);
        int minute = calendarNow.get(Calendar.MINUTE);


        SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm");
        String newDateStr = postFormater.format(new Date());
        Date date = parseDate(hour + ":" + minute);
        dateFajar = parseDate(fajr);
        dateSunrise = parseDate(syuruk);
        dateDuhur = parseDate(duhur);
        dateAsar = parseDate(asar);
        if (dateAsar.before(date) && dateMagrib.after(date)) {
            tvTime.setText("menungu waktu mahrib");
            tvDhur.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            txtduhr.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            txtduhr.setTypeface(null, Typeface.BOLD);
            tvDhur.setTypeface(null, Typeface.BOLD);
        } else {
            String currentDateTimeString = String.valueOf(dateAsar);
            tvTime.setText(newDateStr);
        }
    }

    private Date parseDate(String date) {
        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
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

}
