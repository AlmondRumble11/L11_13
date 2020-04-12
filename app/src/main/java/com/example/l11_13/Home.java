package com.example.l11_13;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class Home extends Fragment {
    public static final String TAG = "home";
    private TextView tv;
    EditText tekstikentta;
    private int check = 0;
    private String fontti;
    private String leveys;
    private String korkeus;
    private String rivi;
    private Boolean kytkin;
    private String nimi;
    private Boolean test = false;
    private  Boolean bold;
    String tekstikentan_teksti;
    private String tekstipohja;
    String alupera="Tämä on kokeilu tekstikenttä ja  vaihtuu toiseen kenttään lukitsen sen\n" +
            "Detta är ett experimentfält och kommer att ändras till ett annat fält om du låser det\n" +
            "This is an experiment text field and will change to another field if you lock it";
    HomeListener fal;
    private Button button;
    private Context c;
    private Bundle bl = new Bundle();
    public Home() {
    }

    public interface HomeListener {
        void onInputHomeSent(String eka, String toka);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        button = (Button) v.findViewById(R.id.button2);
        tekstikentta = (EditText) v.findViewById(R.id.kirjoitusalusta);
        tv = (EditText) v.findViewById(R.id.teksti);
        //tv = (TextView) v.findViewById(R.id.teksti);
        if (savedInstanceState == null) {
           tv.setText(alupera);
        }
            Bundle bundle = getArguments();
            System.out.println(bundle);
            if (bundle != null) {
                tekstikentta.setText("toimiii", TextView.BufferType.EDITABLE);
                fontti = bundle.getString("fontti");
                leveys = bundle.getString("leveys");
                korkeus = bundle.getString("korkeus");
                rivi = bundle.getString("rivi");
                kytkin = bundle.getBoolean("kytkin");
                nimi = bundle.getString("nimi");
                tekstipohja = bundle.getString("key1");
                tekstikentan_teksti = bundle.getString("key2");
                bold = bundle.getBoolean("bold");
                System.out.println(tekstipohja);
                if (bold.equals(true)){
                    tekstikentta.setTypeface(null,Typeface.BOLD);
                    tv.setTypeface(null,Typeface.BOLD);
                }
                tv.setText(tekstipohja);
                if (kytkin.equals(true)) {

                    tekstikentta.post(new Runnable() {
                        @Override
                        public void run() {
                            //tekstikentta.setText(alupera);
                        }
                    });
                    tv.post(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(tekstikentan_teksti);
                        }
                    });
                    tv.setEnabled(false);
                    //tekstikentta.setEnabled(false);
                } else if (kytkin.equals(false)) {
                    tekstikentta.post(new Runnable() {
                        @Override
                        public void run() {
                            tekstikentta.setText(tekstipohja);
                        }
                    });
                }
                if (fontti.isEmpty() == false) {
                    tv.setTextSize(Float.parseFloat(fontti));
                    if (kytkin.equals(false)) {
                        tekstikentta.setTextSize(Float.parseFloat(fontti));
                    }
                }
                if (rivi.isEmpty() == false) {
                    tv.setLineSpacing(tv.getLineHeight(), Float.parseFloat(rivi));
                    if (kytkin.equals(false)) {
                        tekstikentta.setLineSpacing(tv.getLineHeight(), Float.parseFloat(rivi));
                    }
                }
                if (korkeus.isEmpty() == false) {

                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) tv.getLayoutParams();
                    params.height = Integer.parseInt(korkeus);
                    tv.setLayoutParams(params);
                    if (kytkin.equals(false)) {
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) tekstikentta.getLayoutParams();
                        layoutParams.height = Integer.parseInt(korkeus);
                        tekstikentta.setLayoutParams(layoutParams);
                    }
                }
                if (leveys.isEmpty() == false) {
                    tv.setTextScaleX(Float.parseFloat(leveys));

                    if (kytkin.equals(false)) {
                        tekstikentta.setTextScaleX(Float.parseFloat(leveys));

                    }
                }


                if (nimi.isEmpty() == false) {
                    tv.post(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(nimi);
                        }
                    });

                } else if (nimi.isEmpty() == true) {
                    //tv.setText(alupera);
                }
            }

            return v;
        }

}
