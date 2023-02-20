package com.example.variastablasdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.variastablasdb.entidades.Mascota;
import com.example.variastablasdb.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaListaMascotas extends AppCompatActivity {

    ListView listViewMascota;
    ArrayList<String>listaInformacion;
    ArrayList<Mascota> listaMascotas;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_lista_mascotas);

        con = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        listViewMascota = (ListView) findViewById(R.id.listViewMascotas);

        consultarListaPersonas();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listaMascotas.setAdapter(adaptador);

        listViewMascota.setOnItemClickListener((adapterView, view, pos, 1){

            Mascota mascota = listaMascotas.get(pos);

            Intent intent = new Intent(.this,DetalleMascotaActivity.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("mascota",mascota);

            intent.putExtras(bundle);
            startActivity(intent);

        });

    }

    private void consultarListaPersonas(){
        SQLiteDatabase db = con.getReadableDatabase();

        Mascota mascota = null;
        listaMascotas=new ArrayList<Mascota>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_MASCOTA,null);

        while (cursor.moveToNext()){
            mascota = new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota((cursor.getString(1)));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDuenio(cursor.getInt(3));

            listaMascotas.add(mascota);
        }
        obtenerLista();
    }

    private void obtenerLista(){
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaMascotas.size(); i++){
            listaInformacion.add(listaMascotas.get(i).getIdMascota() + " - "
            + listaMascotas.get(i).getNombreMascota());
        }

    }



}