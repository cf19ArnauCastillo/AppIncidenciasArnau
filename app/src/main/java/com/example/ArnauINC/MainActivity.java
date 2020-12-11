package com.example.ArnauINC;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText user_name;
    EditText password;

    public void registrar(View v){
        SharedPreferences mis=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=mis.edit();
        editor.putString("user",user_name.getText().toString());
        editor.commit();
        String nombre_usuario=user_name.getText().toString();
        String contrasenia=password.getText().toString();
        if(nombre_usuario.contains("@") && contrasenia.length()>=1 || contrasenia.length()<=5){
            Toast.makeText(this,"Ingresando",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this, Menu.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"Usuario o contraseña invalido!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name=findViewById(R.id.txtUsername);
        password=findViewById(R.id.txtPassword);

        SharedPreferences mispreferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        user_name.setText(mispreferencias.getString("user","admin"));
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();

        if(id==R.id.ingles){
            Toast.makeText(this,"Has seleccionado Inglés",Toast.LENGTH_SHORT).show();
            String languageToLoad  = "en"; // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config,
                    getResources().getDisplayMetrics());
            recreate();
        }else if(id==R.id.espanol){
            Toast.makeText(this,"Has seleccionado Español",Toast.LENGTH_SHORT).show();
            String languageToLoad  = "es"; // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config,
                    getResources().getDisplayMetrics());
            recreate();
        }else if(id==R.id.cat){
            Toast.makeText(this,"Has seleccionado Catálan",Toast.LENGTH_SHORT).show();
            String languageToLoad  = "ca"; // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config,
                    getResources().getDisplayMetrics());
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.layout.idiomas,menu);
        return true;
    }
}