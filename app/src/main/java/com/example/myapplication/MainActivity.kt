package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

/*Creamos dos arreglos uno para el nombre y país y el otro para las imagenes
En dado caso de que el maestro pidiera los paises por aparte habría que hacer un tercer arreglo
*/
    val comidas = arrayOf(
        "Tacos - México",
        "Sushi - Japón",
        "Pizza - Italia",
        "Paella - España",
        "Curry - India",
        "Croissant - Francia",
        "Borscht - Rusia",
        "Pad Thai - Tailandia",
        "Shawarma - Líbano",
        "Feijoada - Brasil"
    )

    val imagenesComidas = arrayOf(
        R.drawable.tacos,
        R.drawable.sushi,
        R.drawable.pizza,
        R.drawable.paella,
        R.drawable.curri,
        R.drawable.croissant,
        R.drawable.borscht,
        R.drawable.pad,
        R.drawable.shawarma,
        R.drawable.feijoada
    )

    //En esta parte declaramos las var como se vió en la clase
    private lateinit var listView: ListView
    private lateinit var platilloDetalle: LinearLayout
    private lateinit var imageViewPlatillo: ImageView
    private lateinit var tvNombrePlatillo: TextView
    private lateinit var btnRegresar: Button
    private lateinit var btnCompartir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Enlazando los logico con lo físico
        listView = findViewById(R.id.listView)
        platilloDetalle = findViewById(R.id.platilloDetalle)
        imageViewPlatillo = findViewById(R.id.imageViewPlatillo)
        tvNombrePlatillo = findViewById(R.id.tvNombrePlatillo)
        btnRegresar = findViewById(R.id.btnRegresar)
        btnCompartir = findViewById(R.id.btnCompartir)


        val comidaAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, comidas)
        listView.adapter = comidaAdapter

        // Configurar el listener para el ListView
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // Ocultar
            listView.visibility = ListView.GONE
            // Mostrar
            platilloDetalle.visibility = LinearLayout.VISIBLE

            // Mostrar los detalles del platillo seleccionado
            val comida = comidas[position]  // Nombre del platillo
            val imagen = imagenesComidas[position]// Imagen del platillo
            imageViewPlatillo.setImageResource(imagen)
            tvNombrePlatillo.text = comida
        }


        btnRegresar.setOnClickListener {
            //Oculta lo que antes mostramos
            platilloDetalle.visibility = LinearLayout.GONE
            //Muestra lo que antes ocultamos
            listView.visibility = ListView.VISIBLE
        }
        btnCompartir.setOnClickListener {
             val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Estoy compartiendo este platillo: ${tvNombrePlatillo.text}")
                            type = "text/plain"
                        }

                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(Intent.createChooser(intent, "Compartir con"))
                        }

        }
    }
}









