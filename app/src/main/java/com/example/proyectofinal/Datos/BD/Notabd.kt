package com.example.proyectofinal.Datos.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectofinal.Datos.DAO.NotaDao
import com.example.proyectofinal.Datos.ENTIDADES.Nota

@Database(entities = [Nota::class], version = 1)
abstract class Notadb : RoomDatabase() {
    abstract fun NotaDao(): NotaDao

    companion object {
        @Volatile
        private var INSTANCE: Notadb? = null

        fun getDatabase(context: Context): Notadb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Notadb::class.java,
                    "mi_bd"
                ).build() // Eliminado el Callback
                INSTANCE = instance
                instance
            }
        }
    }
}
