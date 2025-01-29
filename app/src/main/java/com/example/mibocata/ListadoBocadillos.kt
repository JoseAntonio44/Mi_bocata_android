package com.example.mibocata

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class ListadoBocadillos : AppCompatActivity() {
    private lateinit var botonRegistroPedidos: ImageButton
    private lateinit var botonPerfil: ImageButton

    @SuppressLint("MissingSuperCall", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_bocadillos)

        // Manejo de insets para barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar botones
        botonRegistroPedidos = findViewById(R.id.historicoPedidos)
        botonPerfil = findViewById(R.id.perfil)

        // Configurar listeners para los botones
        botonRegistroPedidos.setOnClickListener {
            val intent = Intent(this, RegistroPedidos::class.java)
            startActivity(intent)
        }
        botonPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }
        val imagenPedir: ImageView = findViewById(R.id.imageBocata)
        imagenPedir.setOnClickListener{
            val intent = Intent(this,Pedir::class.java)
            startActivity(intent)
        }

        val bocadillosfrios =loadBocadillosFrios(this)
        val listviewFrios =findViewById<ListView>(R.id.listViewFrios)
        val adapterFrios = BocadilloAdapter(this,bocadillosfrios)
        listviewFrios.adapter=adapterFrios


        val bocadillosCalientes =loadBocadillosCalientes(this)
        val listviewCalientes =findViewById<ListView>(R.id.listViewCalientes)
        val adapterCalientes = BocadilloAdapter(this,bocadillosCalientes)
        listviewCalientes.adapter=adapterCalientes

        val calienteMannana =findViewById<TextView>(R.id.calientesMañana)
        val frioMannana = findViewById<TextView>(R.id.friosMañana)


        bocadilloMannana(calienteMannana,frioMannana)

    }

    //Conexion con el json de bocadillos frios
    private fun loadBocadillosFrios(context: Context): List<Bocadillo>{
        val inputStream = resources.openRawResource(R.raw.bocadillos_frios) //Abre el json
        val reader = InputStreamReader(inputStream) //Creo el canal para leer el json
        val type = object : TypeToken<List<Bocadillo>>() {}.type //Para poner el tipo de dato con el que se va a trabajar

        val bocadillos: List<Bocadillo> = Gson().fromJson(reader, type) //Convierte el json en un objeto o coleccion de objetos
        return bocadillos
    }
    //Conexion con el json de bocadillos calientes
    private fun loadBocadillosCalientes(context: Context): List<Bocadillo>{
        val inputStream = resources.openRawResource(R.raw.bocadillos_calientes) //Abre el json
        val reader = InputStreamReader(inputStream) //Creo el canal para leer el json
        val type = object : TypeToken<List<Bocadillo>>() {}.type //Para poner el tipo de dato con el que se va a trabajar

        val bocadillos: List<Bocadillo> = Gson().fromJson(reader, type) //Convierte el json en un objeto o coleccion de objetos
        return bocadillos
    }

    private fun bocadilloMannana(calienteMannana: TextView, frioMannana: TextView) {
        // Cargar los JSON
        val bocatasFrios = loadBocadillos(this, R.raw.bocadillos_frios)
        val bocatasCalientes = loadBocadillos(this, R.raw.bocadillos_calientes)

        val manana = LocalDate.now().dayOfWeek.plus(1)

        val nombreManana = manana.getDisplayName(TextStyle.FULL, Locale("es", "ES"))

        // Encontrar el bocadillo frío del día
        val bocadilloFrioHoy = bocatasFrios.find { it.diaSemana?.lowercase() == nombreManana }
        val bocadilloCalienteHoy = bocatasCalientes.find { it.diaSemana?.lowercase() == nombreManana }

        // Actualizar los TextViews
        frioMannana.text = bocadilloFrioHoy?.nombre ?: "No disponible"
        calienteMannana.text = bocadilloCalienteHoy?.nombre ?: "No disponible"
    }


    private fun loadBocadillos(context: Context, resourceId: Int): List<Bocadillo> {
        return try {
            val inputStream = resources.openRawResource(resourceId)
            val reader = InputStreamReader(inputStream)
            val type = object : TypeToken<List<Bocadillo>>() {}.type
            Gson().fromJson(reader, type)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }


}
