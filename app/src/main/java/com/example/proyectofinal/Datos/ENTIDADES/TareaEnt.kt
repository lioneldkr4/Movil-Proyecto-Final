package com.example.proyectofinal.Datos.ENTIDADES

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Tarea")
data class Tarea(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id") val id: Int = 0, // Cambié para que tenga un valor por defecto
    @ColumnInfo(name = "Titulo") val titulo: String,
    @ColumnInfo(name = "Descripcion") val descripcion: String,
    @ColumnInfo(name = "Fecha") val fecha: String,
    @ColumnInfo(name = "Ruta") val ruta :String,
    @ColumnInfo(name ="Bandera") val bandera : Boolean,

    )