package com.example.variastablasdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.variastablasdb.entidades.Usuario;
import com.example.variastablasdb.utilidades.Utilidades;

import java.util.ArrayList;

public class RegistrarMascota extends AppCompatActivity {

    // Instanciamos los elementos vacio
    EditText nombreMascota;
    EditText razaMascota;
    Spinner spinDuenio;

    ArrayList<String> listaPersona;
    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);

        con = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        nombreMascota = (EditText) findViewById(R.id.txtNombreMascota);
        razaMascota = (EditText) findViewById(R.id.txtRaza);

    }

    private void registrarMascota(){

        SQLiteDatabase db = con.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_MASCOTA,nombreMascota.getText().toString());
        values.put(Utilidades.CAMPO_RAZA_MASCOTA,razaMascota.getText().toString());

        int idSpinner = (int) spinDuenio.getSelectedItemId();
        /**
         * Valida la seleccion del spinner de dueños, si el usuario elige "seleccione" entonces
         * se retorna el id 0 ya que la palabra "seleccione" se encuentra en la posicion0 del spinner
         * sino retorna la posicion del spinner para consulta el usuario almacenado en la lista
         */
        if(idSpinner!=0){
            Log.i("TAMAÑO", personasList.size() + "");
            Log.i("id spinner", idSpinner + "");
            Log.i("id spinner -1", idSpinner-1 + ""); // se resta 1 ya que se quiere obtener la posicion de la lista, no del spinner
            int idDuenio = personasList.get(idSpinner-1).getId();
            Log.i("id DUEÑO", idDuenio + "");

            values.put(Utilidades.CAMPO_ID_DUENIO,idDuenio);

            Long idResultante = db.insert(Utilidades.TABLA_MASCOTA,Utilidades.CAMPO_ID_MASCOTA,values);

            Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
            db.close();
        }else{
            Toast.makeText(getApplicationContext(), "Debe seleccionar un Dueño", Toast.LENGTH_LONG).show();
        }

    }

}