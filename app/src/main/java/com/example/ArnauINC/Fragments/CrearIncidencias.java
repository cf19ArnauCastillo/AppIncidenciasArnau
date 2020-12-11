package com.example.ArnauINC.Fragments;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.ArnauINC.Incidencia;
import com.example.ArnauINC.Menu;
import com.example.ArnauINC.R;
import com.example.ArnauINC.db.IncidenciaBDHelper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class CrearIncidencias extends Fragment {

    View view;
    EditText info_incidencia;
    EditText Desc_incidencia;
    Button Boton_incidencia;
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_crear_incidencias, container, false);

        info_incidencia=(EditText) view.findViewById(R.id.nombre);
        Boton_incidencia=(Button) view.findViewById(R.id.insertar);
        Desc_incidencia=(EditText) view.findViewById(R.id.desc);
        String nivel [] ={"Baja","Media","Alta"};

        spinner=(Spinner) view.findViewById(R.id.urgencia);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,nivel);
        spinner.setAdapter(adapter);

        Boton_incidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String get_info_incidencia=info_incidencia.getText().toString();
                String get_spinner=spinner.getSelectedItem().toString();
                String get_Desc_incidencia=Desc_incidencia.getText().toString();
                Incidencia miincidencia=new Incidencia(get_info_incidencia,get_spinner,get_Desc_incidencia);
                miincidencia.setFecha(System.currentTimeMillis() / 1000);
                miincidencia.setEstado(0);

                IncidenciaBDHelper dbhelper=((Menu)getActivity()).dbhelper;

                SQLiteDatabase db=((Menu)getActivity()).db;

                dbhelper.insertIncidencia(db,miincidencia);
                Toast.makeText(getContext(),"Incidencia Añadida Correctamente!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    } public CrearIncidencias() {

    }
}