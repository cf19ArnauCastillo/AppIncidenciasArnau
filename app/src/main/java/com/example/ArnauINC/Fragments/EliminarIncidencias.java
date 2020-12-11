package com.example.ArnauINC.Fragments;

import com.example.ArnauINC.Menu;
import com.example.ArnauINC.R;
import com.example.ArnauINC.db.IncidenciaBDHelper;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class EliminarIncidencias extends Fragment {
    Button borrar_todos_registros;
    IncidenciaBDHelper dbhelper;
    SQLiteDatabase db;

    public void mostrardialogo(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Cuidado!");
        builder.setMessage("Quieres eliminar todas las incidencias?")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbhelper.eliminarIncidencias(db,dbhelper);

                        Toast.makeText(getContext(),"Incidencias eliminadas!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"Cancelado",Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_eliminar__incidencias, container, false);
        dbhelper=((Menu)getActivity()).dbhelper;
        db=((Menu)getActivity()).db;
        mostrardialogo();

        return view;
    }

    public EliminarIncidencias() {

    }
}