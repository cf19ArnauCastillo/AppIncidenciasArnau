package com.example.ArnauINC.Fragments;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ArnauINC.Menu;
import com.example.ArnauINC.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import com.example.ArnauINC.db.IncidenciaBDHelper;

public class DetallesIncidencias extends Fragment {
    IncidenciaBDHelper dbhelper;
    SQLiteDatabase db;
    String titulo,prioridad,descripcion,fecha,estado;
    int id_usuario;

    public String validarEstado(String estado){
        int numero_estado=Integer.parseInt(estado);
        String cambio;
        if(numero_estado==0){
            numero_estado++;
            cambio=String.valueOf(numero_estado);
            return cambio;
        }else if(numero_estado==1){
            numero_estado++;
            cambio=String.valueOf(numero_estado);
            return cambio;
        }else{
            numero_estado++;
            cambio=String.valueOf(numero_estado);
            return cambio;
        }
    }

    public void mostrardialogo(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Warning!");
        builder.setMessage("Erase event?")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IncidenciaBDHelper.Eliminar_Incidencia_ID(db,id_usuario);
                        Toast.makeText(getContext(),"Event successfully erased",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbhelper = new IncidenciaBDHelper(getContext());
        db = dbhelper.getWritableDatabase();
        View view=inflater.inflate(R.layout.fragment_detalles_incidencia, container, false);
        dbhelper=((Menu)getActivity()).dbhelper;
        db=((Menu)getActivity()).db;
        id_usuario=getArguments().getInt("ID");
        titulo=getArguments().getString("TITLE");
        prioridad=getArguments().getString("PRIORITY");
        descripcion=getArguments().getString("DESCRIPCION");
        fecha=getArguments().getString("DATE");
        estado=getArguments().getString("STATE");

        TextView titulo_1=view.findViewById(R.id.txtUrgencia);
        TextView prioridad_1=view.findViewById(R.id.txtTitulo);
        TextView descripcion_1=view.findViewById(R.id.txtDescripcion);
        TextView fecha_1=view.findViewById(R.id.txtFecha);
        final Button estado_1=view.findViewById(R.id.estado);
        final Button del_Incidencia=view.findViewById(R.id.eliminardi);

        titulo_1.setText(titulo);
        prioridad_1.setText(prioridad);
        descripcion_1.setText(descripcion);
        fecha_1.setText(fecha);
        estado_1.setText("Sin Asignar Estado");

        estado_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cambio=validarEstado(estado);
                if(cambio.equals("1")){
                    estado_1.setText("Asignada");
                    estado_1.setBackgroundColor(Color.YELLOW);
                    estado="1";
                    Toast.makeText(getContext(),"el estado es:"+estado+" id"+id_usuario,Toast.LENGTH_SHORT).show();
                    dbhelper.modificaStatus(db,dbhelper,id_usuario,estado);
                }else if(cambio.equals("2")){
                    estado_1.setText("Completada");
                    estado_1.setBackgroundColor(Color.GREEN);
                    estado="2";
                    Toast.makeText(getContext(),"el estado es:"+estado+" id "+id_usuario,Toast.LENGTH_SHORT).show();
                    dbhelper.modificaStatus(db,dbhelper,id_usuario,estado);
                }else if(!cambio.equals("2") || !cambio.equals("1") ){
                    estado_1.setText("Pendiente");
                    estado_1.setBackgroundColor(Color.RED);
                    estado="0";
                    Toast.makeText(getContext(),"el estado es:"+estado+" id"+id_usuario,Toast.LENGTH_SHORT).show();
                    dbhelper.modificaStatus(db,dbhelper,id_usuario,estado);

                }
            }
        });

        del_Incidencia.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mostrardialogo();
                    }
                });
        return view;
    }


}