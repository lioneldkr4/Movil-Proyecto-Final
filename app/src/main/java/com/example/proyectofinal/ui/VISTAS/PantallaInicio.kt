package com.example.proyectofinal.ui.VISTAS



import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.Datos.ENTIDADES.Nota
import com.example.proyectofinal.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimeraPantalla(navController: NavController,viewModel: LoginViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // Cambiamos scaffoldState por drawerState
    val scope = rememberCoroutineScope() // Alcance para abrir el drawer

    // Usamos ModalNavigationDrawer en lugar de drawerContent dentro del Scaffold
    val notas by viewModel.notas.collectAsState(initial = emptyList())

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text("Opción 1")
                    Text("Opción 2")
                    Text("Opción 3")
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(notas.size) { index ->
                        NotaItem(notas[index])
                    }
                }
                IconInBottomLeft(navController)
            }
        }
    }
}


@Composable
fun NotaItem(nota: Nota) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = nota.titulo, style = MaterialTheme.typography.headlineSmall)
        Text(text = nota.descripcion, style = MaterialTheme.typography.bodyMedium)
        Text(text = nota.fecha, style = MaterialTheme.typography.bodySmall)
    }
}


@Composable
fun Content(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        IconRow()
    }
}

@Composable
fun IconRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(5) {
            IconItem()
        }
    }
}

@Composable
fun IconItem(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.agregar),
        contentDescription = stringResource(id = R.string.app_name),
        modifier = modifier
            .size(40.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(0.dp)
            )
    )
}

@Composable
fun IconInBottomLeft(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.agregar),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier
                .size(60.dp)
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                )
                .align(Alignment.BottomStart)
                .clickable {
                    // Navegamos a la segunda pantalla al hacer clic en el ícono
                    navController.navigate("segundaPantalla")
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPrimeraPantalla() {
    val navController = rememberNavController() // Crear un controlador de navegación de muestra
    PrimeraPantalla(navController = navController, viewModel = viewModel()) // Pasar el controlador a la función
}
