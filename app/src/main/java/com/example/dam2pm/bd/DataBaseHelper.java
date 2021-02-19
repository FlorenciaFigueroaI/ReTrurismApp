package com.example.dam2pm.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dam2pm.modelos.Fotografia;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String FOTOGRAFIA_TABLA = "FOTOGRAFIA_TABLA";
    public static final String COLUMN_TITULO = "TITULO";
    public static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    public static final String COLUMN_CIUDAD = "CIUDAD";
    public static final String COLUMN_ANYO = "ANYO";
    public static final String COLUMN_RUTA = "COLUMN_RUTA";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "galeria.db", null, 1);
    }

    // Cuando se accede por primera vez a la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + FOTOGRAFIA_TABLA + " (FOTOGRAFIA_ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITULO + " TEXT, " + COLUMN_DESCRIPCION + " TEXT, " + COLUMN_CIUDAD + " TEXT, " + COLUMN_ANYO + " INT, " + COLUMN_RUTA + " TEXT)";
        db.execSQL(createTableStatement);
        

    }
// Cuando la version de la base de datos cambia.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Método para añadir una foto
    public boolean insertarFoto (Fotografia fotografia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITULO, fotografia.getTitulo());
        cv.put(COLUMN_DESCRIPCION, fotografia.getDescripcion());
        cv.put(COLUMN_CIUDAD, fotografia.getCiudad());
        cv.put(COLUMN_ANYO, fotografia.getAnyo());
        cv.put(COLUMN_RUTA, fotografia.getRuta());


        long insert = db.insert(FOTOGRAFIA_TABLA, null, cv);

        if (insert == -1){  // si devuelve -1 la inserción ha fallado
            return false;
        } else { // inserción exitosa
            return true;
        }

    }
}
