package com.example.coleccionpeliculas

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_agregar_peli.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.NumberFormatException
import java.sql.SQLException
import java.sql.SQLIntegrityConstraintViolationException


class agregarPeli : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_peli)
        btAgregar.setOnClickListener(){
            anadir()
        }

//        val xid : EditText = findViewById(R.id.etId)
//        val xnombre: EditText = findViewById(R.id.etNombre)
//        val xgenero: EditText = findViewById(R.id.etGenero)
//        val xduracion : EditText = findViewById(R.id.etDuracion)
//        val xdescripcion : EditText = findViewById(R.id.etDescripcion)


    }

    fun anadir() {
// http://iesayala.ddns.net/JosemiMartinez/json/insertar_pelicula.php?id=4&nomPelicula=Nosferatu&genero=hola&duracionMinutos=110&descripcion=descripcion23
        try {

            val xid: EditText = findViewById(R.id.etId)
            val xnombre: EditText = findViewById(R.id.etNombre)
            val xgenero: EditText = findViewById(R.id.etGenero)
            val xduracion: EditText = findViewById(R.id.etDuracion)
            val xdescripcion: EditText = findViewById(R.id.etDescripcion)


            val id = xid.text.toString().toInt()
            val nomPelicula = xnombre.text
            val genero = xgenero.text
            Log.d("contenido",nomPelicula.toString())
            val duracion = xduracion.text.toString().toInt()
            val descripcion = xdescripcion.text
            if(nomPelicula.toString().trim().equals("") || genero.toString().trim().equals("") || descripcion.toString().trim().equals("")) {
                Toast.makeText(applicationContext,"Rellene todos los campos", Toast.LENGTH_LONG).show()
            }else{
                val url =
                    "http://iesayala.ddns.net/JosemiMartinez/json/insertar_pelicula.php?id=" + id + "&nomPelicula=" +
                            nomPelicula + "&genero=" + genero + "&duracionMinutos=" + duracion + "&descripcion=" + descripcion
                val request = Request.Builder().url(url).build()
                val cliente = OkHttpClient()
                cliente.newCall(request).enqueue(object : Callback {
                    override fun onResponse(call: okhttp3.Call, response: Response) {
                        //                Toast.makeText(applicationContext,"Insertado Correctamente", Toast.LENGTH_LONG).show()
                        finish()
                    }

                    override fun onFailure(call: okhttp3.Call, e: IOException) {
                        //println("Error")
                        //                Toast.makeText(applicationContext,"Error al añadir", Toast.LENGTH_LONG).show()
                        //finish() https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Film_reel.svg/220px-Film_reel.svg.png
                    }


                })
            }
        }catch (e: NumberFormatException){
            Toast.makeText(applicationContext,"Introduzca correctamente los datos numericos", Toast.LENGTH_LONG).show()
        }catch (e:SQLException){
            Toast.makeText(applicationContext,"Ya hay una pelicula con ese ID", Toast.LENGTH_LONG).show()
        }


    }




}
