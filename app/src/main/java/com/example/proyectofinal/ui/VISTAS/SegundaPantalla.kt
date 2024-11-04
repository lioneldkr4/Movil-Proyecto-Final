package com.example.proyectofinal.ui.VISTAS

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinal.Datos.ENTIDADES.Nota
import com.example.proyectofinal.R
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarTareaScreen(navController: NavController? = null,viewModel: LoginViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Opción 1")
                    Text("Opción 2")
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            FormularioAgregarTarea(Modifier.padding(innerPadding),viewModel,navController)
        }
    }
}

@Composable
fun FormularioAgregarTarea(modifier: Modifier = Modifier, viewModel: LoginViewModel,navController: NavController?) {

    val titulo: String by viewModel.titulo.observeAsState(initial = "")
    val descripcion: String by viewModel.descripcion.observeAsState(initial = "")
    val fechaSeleccionada: String by viewModel.fecha.observeAsState(initial = "")
    val archivoSeleccionado: String by viewModel.ruta.observeAsState(initial = "")

    // Configuración del calendario para la selección de fecha
    val calendario = Calendar.getInstance()
    val anio = calendario.get(Calendar.YEAR)
    val mes = calendario.get(Calendar.MONTH)
    val dia = calendario.get(Calendar.DAY_OF_MONTH)

    // State para mostrar el DatePicker
    var showDatePicker by remember { mutableStateOf(false) }

    // Mostrar el DatePickerDialog
    if (showDatePicker) {
        DatePickerDialog(
            LocalContext.current,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                viewModel.setFecha("$selectedDay/${selectedMonth + 1}/$selectedYear")
                showDatePicker = false // Cierra el DatePicker después de seleccionar
            },
            anio, mes, dia
        ).show()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Agregar Tarea", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = titulo,
            onValueChange = { viewModel.setTitulo(it) }, // Actualiza el valor en el ViewModel
            label = { Text(text = "Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { viewModel.setDescripcion(it) }, // Actualiza el valor en el ViewModel
            label = { Text(text = "Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para seleccionar fecha
        OutlinedButton(onClick = {
            showDatePicker = true // Abre el DatePicker
        }) {
            Text(text = if (fechaSeleccionada.isEmpty()) "Seleccionar fecha estimada" else fechaSeleccionada)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para simular selección de archivo
        OutlinedButton(onClick = {
            viewModel.setRuta("archivo_simulado.pdf") // Actualiza el valor en el ViewModel
        }) {
            Text(text = if (archivoSeleccionado.isEmpty()) "Seleccionar archivo" else archivoSeleccionado)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar la tarea (no incluye lógica de almacenamiento)
        Button(onClick = {
           if(!descripcion.isEmpty()){
            val nota= Nota(descripcion=descripcion, titulo = titulo,fecha=fechaSeleccionada,ruta=archivoSeleccionado)
               viewModel.insertar(nota)
               navController?.navigate("primeraPantalla")
           }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Agregar Tarea")
        }
    }
}



