package com.example.proyectofinal.ui.VISTAS

import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal.Datos.DAO.NotaDao
import androidx.lifecycle.ViewModel



class LoginViewModelFactory(private val notaDao: NotaDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(notaDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
