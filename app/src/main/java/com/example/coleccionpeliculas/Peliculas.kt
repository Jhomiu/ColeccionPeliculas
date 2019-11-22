package com.example.coleccionpeliculas


data class Peliculas (
    var id: Int,
    var nomPelicula: String,
    var genero: String,
    var duracionMinutos: Int,
    var descripcion: String,
    val imagen: String

)