package com.example.almbumesjetpackcompose.ui.theme

sealed class Screen(val dir:String) {
    object start: Screen("Inicio")
    object inf: Screen("Info/{nombre}")
    {
        fun funScreen(nombre:String) = "info/$nombre"
    }
}