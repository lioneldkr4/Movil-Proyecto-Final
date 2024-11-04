package com.example.proyectofinal.ui.VISTAS

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.Datos.DAO.NotaDao
import com.example.proyectofinal.Datos.ENTIDADES.Nota
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val notaDao: NotaDao) : ViewModel() {
    // Ya no necesitamos lateinit
    val notas: Flow<List<Nota>> = notaDao.getAllNotas()
    private val _titulo = MutableLiveData<String>()
    val titulo: LiveData<String> = _titulo

    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion

    private val _ruta = MutableLiveData<String>()
    val ruta: LiveData<String> = _ruta

    private val _fecha = MutableLiveData<String>()
    val fecha: LiveData<String> = _fecha

    fun setTitulo(titulo: String) {
        _titulo.value = titulo
    }

    fun setDescripcion(descripcion: String) {
        _descripcion.value = descripcion
    }

    fun setRuta(ruta: String) {
        _ruta.value = ruta
    }

    fun setFecha(fecha: String) {
        _fecha.value = fecha
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isValidPassword(password: String): Boolean = password.length >= 6

    fun insertar(nota: Nota) = viewModelScope.launch {
        notaDao.insertar(nota)
    }
}
