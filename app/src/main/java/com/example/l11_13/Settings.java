package com.example.l11_13;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;


public class Settings extends Fragment {
    EditText fontti;
    EditText rivivali;
    EditText leveys;
    EditText korkeus;
    Button muuta;
    Switch kytkin;
    EditText nimi;
    Spinner kielet;
    List<String> kieli_lista;
    ArrayAdapter<String> ap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        muuta = (Button) v.findViewById(R.id.button);
        fontti = (EditText) v.findViewById(R.id.fontti);
        rivivali = (EditText) v.findViewById(R.id.rivi);
        leveys = (EditText) v.findViewById(R.id.leveys);
        korkeus = (EditText) v.findViewById(R.id.korkeus);
        kytkin = (Switch) v.findViewById(R.id.kytkin);
        kielet = (Spinner) v.findViewById(R.id.spinner);
        nimi = (EditText) v.findViewById(R.id.nimi);
        kieli_lista = new ArrayList();
        kieli_lista.add("Suomi");
        kieli_lista.add("Englanti");
        kieli_lista.add("Ruotsi");
        ap = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, kieli_lista);
        kielet.setAdapter(ap);
        muuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String font = fontti.getText().toString();
                String line = rivivali.getText().toString();
                String width = leveys.getText().toString();
                String height = korkeus.getText().toString();
                Boolean muutos = kytkin.isChecked();
                String name =  nimi.getText().toString();
                System.out.println(font);
                Bundle bundle = new Bundle();
                bundle.putString("fontti", font);
                bundle.putString("rivi", line);
                bundle.putString("leveys", width);
                bundle.putString("korkeus", height);
                bundle.putBoolean("kytkin",muutos);
                bundle.putString("nimi", name);
                fontti.setText("");
                rivivali.setText("");
                leveys.setText("");
                korkeus.setText("");
                nimi.setText("");
                Home hf = new Home();
                hf.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.main, hf).commit();
            }
        });
        return v;
    }

}
