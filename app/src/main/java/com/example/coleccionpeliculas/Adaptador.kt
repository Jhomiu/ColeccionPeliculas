package com.example.coleccionpeliculas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adaptador(var lista: ArrayList<Peliculas>): RecyclerView.Adapter<Adaptador.MiViewHolder>() {
    class MiViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun enlazaItems(datos:Peliculas){
            val titulo: TextView =itemView.findViewById(R.id.titulo)
            var imagen: ImageView =itemView.findViewById(R.id.imagen)
            val genero: TextView = itemView.findViewById(R.id.genero)
            val duracion: TextView = itemView.findViewById(R.id.duracion)
            val descripcion: TextView = itemView.findViewById(R.id.descripcion)

            titulo.text=datos.nomPelicula
            genero.text=datos.genero
            duracion.text= "" + datos.duracionMinutos.toString()
            descripcion.text=datos.descripcion
            Glide.with(itemView.context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Film_reel.svg/220px-Film_reel.svg.png").into(imagen)



            itemView.setOnClickListener {
                Toast.makeText(itemView.context,"Pelicula ${datos.nomPelicula}", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return MiViewHolder(v)
    }
    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        holder.enlazaItems(lista[position])
    }
}