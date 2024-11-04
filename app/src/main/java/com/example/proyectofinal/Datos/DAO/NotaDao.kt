package com.example.proyectofinal.Datos.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectofinal.Datos.ENTIDADES.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {
    @Insert
    suspend fun insertar(nota: Nota)
    @Query("SELECT * FROM Notas")
    fun getAllNotas(): Flow<List<Nota>>

}