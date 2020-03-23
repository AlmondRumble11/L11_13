package com.example.l11_13;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.concurrent.atomic.AtomicInteger;


public class Home extends Fragment  {
    TextView tv;
    EditText tekstikentta;
    int check = 0;
    private Context c;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        tv = (TextView) v.findViewById(R.id.teksti);
        tekstikentta = (EditText) v.findViewById(R.id.kirjoitusalusta);
        if (check == 0) {
            System.out.println("dsovnjefignerwihjg");
            tv.setText("Tämä on testi tekstipohja eli siinä ei  ole mitään järkevää\nTämä vaihtuu toiseen keäntään jos et kirjoita mitään siihe\nHmm");
            check = 1;
        }
        return v;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onStart(){
        super.onStart();

        Bundle bundle = getArguments();

        if(bundle != null) {

            String fontti = bundle.getString("fontti");
            String leveys = bundle.getString("leveys");
            String korkeus = bundle.getString("korkeus");
            String rivi = bundle.getString("rivi");
            Boolean kytkin = bundle.getBoolean("kytkin");
            String nimi = bundle.getString("nimi");
            System.out.println(bundle);
            if (fontti.isEmpty() == false){
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
                if (kytkin.equals(false)){
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) tekstikentta.getLayoutParams();
                    layoutParams.height = Integer.parseInt(korkeus);
                    tekstikentta.setLayoutParams(layoutParams);
                }
            }
            if (leveys.isEmpty() == false){
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) tv.getLayoutParams();
                params.width = Integer.parseInt(leveys);
                tv.setLayoutParams(params);
                if (kytkin.equals(false)){
                    tekstikentta.setLayoutParams(params);
                }
            }
            if (kytkin.equals(true)){
                tekstikentta.setText(tv.getText().toString());
                tekstikentta.setEnabled(false);
            }
            if (nimi.isEmpty() == false){
                tv.setText(nimi);
            }


        }
    }
}
