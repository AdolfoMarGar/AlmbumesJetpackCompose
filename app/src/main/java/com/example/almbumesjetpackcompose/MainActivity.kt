package com.example.almbumesjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.almbumesjetpackcompose.ui.theme.AlmbumesJetpackComposeTheme
import com.example.almbumesjetpackcompose.ui.theme.Screen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlmbumesJetpackComposeTheme {
                Surface(color = Color.Black) {
                    NavigationHost()
                }
            }
        }
    }
}

@Composable
fun NavigationHost(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.start.dir) {
        composable(Screen.start.dir){
            inicio(navController = navController)
        }

        composable(Screen.inf.dir){
                navBackStackEntry ->
            var nombre = navBackStackEntry.arguments?.getString("nombre")
            data(nombre!!, navController = navController)
        }
    }
}

@Composable
fun data(Nombre: String, navController: NavController){
    val discos = crearLista()
    var alb = Album("", "", 0, "", "")

    for (i in discos){
        if (i.nombre == Nombre) {
            alb = i
        }
    }

    Column(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Magenta,
                        Color.Red,
                    )
                )
            )
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Row(
            modifier = Modifier
                .background(Color.Magenta)
        ){
            Text(
                text = alb.nombre,
                color = Color.DarkGray,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                textAlign = TextAlign.Right
            )
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .width(130.dp)
                .height(130.dp)
        ){
            imagen(url = alb.imagen)
        }

        Row {
            Text(
                text = alb.autores,
                color = Color.DarkGray,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }

        Row {
            Text(
                text = alb.anio.toString(),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        Row {
            Text(
                text = alb.desc,
                color = Color.White,
                lineHeight = 2.em,
                textAlign = TextAlign.Left
            )
        }

        Row {
            IconButton(
                onClick = { navController.navigate(Screen.start.dir) },
                modifier = Modifier.padding(top = 35.dp).background(Color.White)
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Volver al inicio"
                )
            }
        }
    }
}

@Composable
fun imagen(url: String) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = "imagePadding",
        modifier = Modifier.padding(1.dp).height(120.dp),
        alignment = Alignment.Center ,
        contentScale = ContentScale.Inside)
}

@Composable
fun imagenLista(url: String) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = "imagePadding",
        modifier = Modifier
            .height(250.dp)
            .width(50.dp),
        alignment = Alignment.Center ,
        contentScale = ContentScale.FillHeight
    )
}

@Composable
fun inicio(navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(80.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Red,
                        Color.Black,
                    )
                )
            )
    ){
        items(crearLista()) {
                alb ->
            Card(
                modifier = Modifier
                    .clickable(
                        onClick = {
                            navController.navigate(
                                Screen.inf.funScreen(
                                    alb.nombre
                                )
                            )
                        }
                    )
                    .background(Color.LightGray)
                    .padding(horizontal = 1.dp, vertical = 2.dp)
                    .fillMaxSize(),
                elevation = 2.dp,
                shape = CutCornerShape(10.dp),
                )
            {
                imagenLista(url = alb.imagen)
            }
        }
    }
}

fun crearLista(): List<Album> {
    val alb1 = Album(
        "A Night at the Opera",
        "Queen",
        1975,
        "https://m.media-amazon.com/images/I/71nxRqKGA8L._SL1400_.jpg",
        "1º «Death on Two Legs»\n" +
                "2º «Lazing On A Sunday Afternoon»\n" +
                "3º «I'm In Love With My Car»\n" +
                "4º «You're My Best Friend»\n" +
                "5º «'39»\n" +
                "6º «Sweet Lady»\n" +
                "7º «Seaside Rendezvous»\n" +
                "8º «The Prophet's Song»\n" +
                "9º «Love of My Life»\n" +
                "10º «Good Company»\n" +
                "11º «Bohemian Rhapsody»\n" +
                "12º «God Save the Queen (instrumental)»\n",
    )
    val alb2 = Album(
        "Led Zeppelin IV",
        "Led Zeppelin",
        1971,
        "https://m.media-amazon.com/images/I/51h-cJeHf0L.jpg",
        "1º «Black Dog»»\n" +
                "2º «Rock and Roll»»\n" +
                "3º «The Battle of Evermore»\n" +
                "4º «Stairway to Heaven»\n" +
                "5º «Misty Mountain Hop»\n" +
                "6º «Four Sticks»\n" +
                "7º «Going to California»\n" +
                "8º «When the Levee Breaks»\n"
    )
    val alb3 = Album(
        "Appetite for Destruction",
        "Guns N' Roses",
        1987,
        "https://m.media-amazon.com/images/I/91ksS-ioRpL._SL1500_.jpg",
        "1º «Welcome to the Jungle»\n" +
                "2º «It's So Easy»\n" +
                "3º «Nightrain»\n" +
                "4º «Out Ta Get Me»\n" +
                "5º «Mr. Brownstone»\n" +
                "6º «Paradise City»\n" +
                "7º «My Michelle»\n" +
                "8º «Think About You»\n" +
                "9º «Sweet Child o' Mine»\n" +
                "10º «You're Crazy»\n" +
                "11º «Anything Goes»\n" +
                "12º «Rocket Queen»\n",
    )

    return listOf(alb1, alb2, alb3,alb1, alb2, alb3,alb1, alb2, alb3)
}