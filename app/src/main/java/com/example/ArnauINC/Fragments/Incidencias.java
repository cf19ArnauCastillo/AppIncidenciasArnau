package com.example.ArnauINC.Fragments;

import android.view.ViewGroup;
import android.widget.Button;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import com.example.ArnauINC.R;

public class Incidencias extends Fragment {

    View view;
    Button boton1;
    Button boton2;
    Button boton3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_incidencias, container, false);

        boton1=(Button) view.findViewById(R.id.afegir);
        boton2=(Button) view.findViewById(R.id.llistar);
        boton3=(Button) view.findViewById(R.id.eliminar);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment vista= new CrearIncidencias();
                FragmentManager mimanejador=getFragmentManager();
                FragmentTransaction mitransaccion=mimanejador.beginTransaction();
                mitransaccion.replace(R.id.contenedor,vista);
                mitransaccion.commit();
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment vista= new ListarIncidencias();
                FragmentManager mimanejador=getFragmentManager();
                FragmentTransaction mitransaccion=mimanejador.beginTransaction();
                mitransaccion.replace(R.id.contenedor,vista);
                mitransaccion.commit();
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment vista= new EliminarIncidencias();
                FragmentManager mimanejador=getFragmentManager();
                FragmentTransaction mitransaccion=mimanejador.beginTransaction();
                mitransaccion.replace(R.id.contenedor,vista);
                mitransaccion.commit();
            }
        });

        return view;
    }

    public Incidencias() {
    }
}