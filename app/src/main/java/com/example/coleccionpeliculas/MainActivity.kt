package com.example.coleccionpeliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var tarjetaAdapter : Adaptador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btAñadir.setOnClickListener{
            val intent = Intent(this,agregarPeli::class.java)
            startActivity(intent)
        }


        jsonAdaptador()
    }

    override fun onStart() {
        super.onStart()
        jsonAdaptador()
    }

    fun jsonAdaptador() {

        val url = "http://iesayala.ddns.net/JosemiMartinez/json/generarJSON.php"
        val request = Request.Builder().url(url).build()
        val cliente = OkHttpClient()
        cliente.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: okhttp3.Call, response: Response) {
                val json = response.body()?.string()
                println(json)

                val gson = GsonBuilder().create()

                var lista = gson.fromJson(json, PeliculasArray::class.java)
                println(lista.Peliculas)

                runOnUiThread {
                    recyclerIt.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        tarjetaAdapter = Adaptador(lista.Peliculas!!)
                        adapter = tarjetaAdapter
                    }
                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Error")
            }


        })
    }
}
