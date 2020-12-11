package com.example.ArnauINC.Fragments;

import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Toast;
import com.example.ArnauINC.ListarInformacion;
import com.example.ArnauINC.R;
import com.example.ArnauINC.db.IncidenciaBDHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ListarIncidencias extends Fragment {
    Spinner spinner;
    Button buscar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_listar_incidencias, container, false);

        buscar=(Button)view.findViewById(R.id.buscar);

        String nivel [] ={"Todas","Pendientes","Asignadas","Completadas"};
        spinner=(Spinner)view.findViewById(R.id.prioridades);
        ArrayAdapter<String> adapte=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,nivel);
        spinner.setAdapter(adapte);

        IncidenciaBDHelper dbhelper=new IncidenciaBDHelper(getContext());
        SQLiteDatabase db=dbhelper.getWritableDatabase();

        final RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncidenciaBDHelper dbhelper=new IncidenciaBDHelper(getContext());
                SQLiteDatabase db=dbhelper.getReadableDatabase();
                String get_spinner=spinner.getSelectedItem().toString();

                if(get_spinner.equals("Todas")){
                    ListarInformacion adapter=new ListarInformacion(getContext(),IncidenciaBDHelper.getAllIncidencies(db,get_spinner));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    Toast.makeText(getContext(), "Todas", Toast.LENGTH_SHORT).show();
                }else if(get_spinner.equals("Completadas")){
                    String filter="Alta";
                    ListarInformacion adapter=new ListarInformacion(getContext(),IncidenciaBDHelper.getAllIncidencies(db,filter));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }else if(get_spinner.equals("Asignadas")){
                    String filter="Media";
                    ListarInformacion adapter=new ListarInformacion(getContext(),IncidenciaBDHelper.getAllIncidencies(db,filter));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }else if(get_spinner.equals("Pendientes")){
                    String filter="Baja";
                    ListarInformacion adapter=new ListarInformacion(getContext(),IncidenciaBDHelper.getAllIncidencies(db,filter));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }
        });

        return view;
    }
    public ListarIncidencias() {

    }
}