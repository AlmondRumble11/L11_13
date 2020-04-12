package com.example.l11_13;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


public class Settings extends Fragment {
    EditText fontti;
    EditText leveys;
    EditText korkeus;
    Button muuta;
    Switch kytkin;
    Switch bold;
    EditText nimi;
    Spinner kielet;
    Home hf = new Home();
    Spinner rivit;
    List<String> kieli_lista;
    List<Float> rivi_vali;
    ArrayAdapter<String> ap;
    ArrayAdapter<Float> ap2;
    String font;
    String line;
    String width;
    String height;
    Boolean muutos;
    String name;
    String lang;
    String eka;
    String toka;
    SettingsListener fal;
    Bundle bundle = new Bundle();
    public Settings (){}



    public interface  SettingsListener{
        void onInputSettingsSent(Bundle bundle);
    }
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        if(getArguments() != null){
            eka = getArguments().getString("key1");
            toka = getArguments().getString("key2");
            System.out.println(eka);
            System.out.println(toka);
        }
        muuta = (Button) v.findViewById(R.id.button);
        fontti = (EditText) v.findViewById(R.id.fontti);

        leveys = (EditText) v.findViewById(R.id.leveys);
        korkeus = (EditText) v.findViewById(R.id.korkeus);
        kytkin = (Switch) v.findViewById(R.id.kytkin);
        kielet = (Spinner) v.findViewById(R.id.spinner);
        rivit = (Spinner) v.findViewById(R.id.spinner2);
        nimi = (EditText) v.findViewById(R.id.nimi);
        bold = (Switch) v.findViewById(R.id.bold);
        kieli_lista = new ArrayList();
        kieli_lista.add(getResources().getString(R.string.suomi));
        kieli_lista.add(getResources().getString(R.string.englanti));
        kieli_lista.add(getResources().getString(R.string.ruotsi));
        rivi_vali = new ArrayList();
        rivi_vali.add(new Float(0.5));
        rivi_vali.add(new Float(1));
        rivi_vali.add(new Float(1.25));
        rivi_vali.add(new Float(1.5));
        rivi_vali.add(new Float(2));
        ap2 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1,rivi_vali);
        ap = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, kieli_lista);
        rivit.setAdapter(ap2);
        kielet.setAdapter(ap);
        //näitä ei tarvittu enää kun mainactivityssä otetaan tiedot haltuun
       /* muuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                font = fontti.getText().toString();

                width = leveys.getText().toString();
                height = korkeus.getText().toString();
                muutos  = kytkin.isChecked();
                name =  nimi.getText().toString();
                line = rivit.getSelectedItem().toString();
                lang = kielet.getSelectedItem().toString();
                System.out.println(font);

                bundle.putString("fontti", font);
                bundle.putString("rivi", line);
                bundle.putString("leveys", width);
                bundle.putString("korkeus", height);
                bundle.putBoolean("kytkin",muutos);
                bundle.putString("nimi", name);
                bundle.putString("kieli",lang);
                fontti.setText("");

                leveys.setText("");
                korkeus.setText("");
                nimi.setText("");
                fal.onInputSettingsSent(bundle);
                hf.setArguments(bundle);
                System.out.println("tämä on bundle"+bundle);
                System.out.println(eka+toka);
                //getFragmentManager().beginTransaction().replace(R.id.main, hf).commit();
            }
        });*/
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

    }



    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof  SettingsListener){
            fal =(SettingsListener) context;
        }
    }
    @Override
    public  void onDetach(){
        super.onDetach();
        fal = null;
    }
}
