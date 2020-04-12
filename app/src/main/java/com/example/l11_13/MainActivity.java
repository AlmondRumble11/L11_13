package com.example.l11_13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class MainActivity<val> extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , Home.HomeListener, Settings.SettingsListener {
    TextView tv;
    String font;
    String line;
    String width;
    String height;
    Boolean muutos;
    Boolean bold;
    String name;
    String lang;
    Home hm = new Home();
    Settings st = new Settings();
    int check =0;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Object savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loadLocale();
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer

        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.main, hm);
            ft.commit();
            check = 1;
        }
        }

    public void setLocale(String lang) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        Locale locale = new Locale(pref.getString("lang_code",lang));
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale= locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
    public void loadLocale(int n){
        String language;
        if (n==1) {
            SharedPreferences preferences = getSharedPreferences("MyPref", Activity.MODE_PRIVATE);
            language = preferences.getString("lang_code", "en");
        }
        else if (n==2){
            SharedPreferences preferences = getSharedPreferences("MyPref", Activity.MODE_PRIVATE);
            language = preferences.getString("lang_code", "sv");
        }else{
            SharedPreferences preferences = getSharedPreferences("MyPref", Activity.MODE_PRIVATE);
            language = preferences.getString("lang_code","fi");
        }
        setLocale(language);
    }

    @Override
    public void onInputHomeSent(String n, String m) {

    }

    @Override
    public void onInputSettingsSent(Bundle input) {
        //jos yrittää suoraan vaihtaa kieltä uudestaan niin silloin langia ei ole saatu niin
            try {
                lang = st.kielet.getSelectedItem().toString();
            }catch (NullPointerException e){
                lang = "en_EN";
                Toast.makeText(this,"Change the page before changing language again",Toast.LENGTH_SHORT).show();

            }

            if (lang.equals("Suomi") || lang.equals("Finnish") || (lang.equals("Finska"))) {
                lang = "fi_FI";
                setLocale(lang);
                loadLocale(0);
                recreate();
            } else if (lang.equals("Ruotsi") || (lang.equals("Swedish")) || (lang.equals("Svenska"))) {
                lang = "sv_FI";
                setLocale(lang);
                loadLocale(2);
                recreate();
            } else if (lang.equals("Englanti") || (lang.equals("English")) || (lang.equals("Engelska"))) {
                lang = "en_EN";
                setLocale(lang);
                loadLocale(1);
                recreate();
            }
    }
    public void muutaKieli(View v){

        onInputSettingsSent(st.bundle);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.main, hm);
                    ft.commit();

                break;
            case  R.id.nav_settings:
                if (savedInstanceState != null) {
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction().add(R.id.main, st);
                    ft2.commit();
                }else{
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction().replace(R.id.main, st);
                    ft2.commit();
                }
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void sendValueToFragment1(View Viev){
        Bundle bundle = new Bundle();
        String teksti = hm.tekstikentta.getText().toString();
        System.out.println("*******************"+teksti);
        bundle.putString("key1",teksti);
        bundle.putString("key2",hm.alupera);
        System.out.println(bundle);
        st.setArguments(bundle);
        //FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction().replace(R.id.main, st);
        //ft2.commit();
    }
    public void sendValueToFragment2(View view){
        try {
            //tässä on varmaan turhaa käytää settingsien bundlea kun voisi vain suoraan tehdä mainactivityyn oman bundlen
            //samoin key1 ja key2 voisi vain suoraan saada mainactivityyn saaduista arvoista eikä tavitsisi settingien kautta ottaa niitä
            lang = st.kielet.getSelectedItem().toString();
            font = st.fontti.getText().toString();
            width = st.leveys.getText().toString();
            height = st.korkeus.getText().toString();
            muutos = st.kytkin.isChecked();
            bold = st.bold.isChecked();
            name = st.nimi.getText().toString();
            line = st.rivit.getSelectedItem().toString();
            if (muutos.equals(true)){
                //st.eka = st.toka;
                st.toka = st.eka;
            }
            st.bundle.putString("key1", st.eka);
            st.bundle.putString("key2", st.toka);
            st.bundle.putString("fontti", font);
            st.bundle.putString("rivi", line);
            st.bundle.putString("leveys", width);
            st.bundle.putString("korkeus", height);
            st.bundle.putBoolean("kytkin", muutos);
            st.bundle.putString("nimi", name);
            st.bundle.putString("kieli", lang);
            st.bundle.putBoolean("bold",bold);
            System.out.println("terve");
            hm.setArguments(st.bundle);
            //hm.tekstikentta.setText("jfnvijdfvnjdfkovndfklvndfmlvn dfkjv");
        }catch (NullPointerException e){
            System.out.println(e);
        }
        //FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.main, hm);
        //ft.commit();

    }
}
