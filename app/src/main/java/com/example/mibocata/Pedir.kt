package com.example.mibocata

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class Pedir : AppCompatActivity() {
    private lateinit var botonRegistroPedidos : ImageButton
    private lateinit var botonPerfil : ImageButton
    private lateinit var botonListadoBocadillos : ImageButton

    private lateinit var botonPedirFrios : Button
    private lateinit var botonPedirCalientes : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedir)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        botonRegistroPedidos = findViewById(R.id.historicoPedidos)
        botonPerfil = findViewById(R.id.perfil)
        botonListadoBocadillos = findViewById(R.id.listaBocatas)


        botonRegistroPedidos.setOnClickListener {
            val intent = Intent(this,RegistroPedidos::class.java)
            startActivity(intent)
        }
        botonPerfil.setOnClickListener {
            val intent = Intent(this,Perfil::class.java)
            startActivity(intent)
        }
        botonListadoBocadillos.setOnClickListener {
            val intent = Intent(this,ListadoBocadillos::class.java)
            startActivity(intent)
        }


        botonPedirFrios = findViewById(R.id.botonFrios)
        botonPedirCalientes = findViewById(R.id.botonCalientes)


        //Llama a la funcion de mostrar los bocadillos del dia en los botones
        bocadilloHoy(botonPedirCalientes,botonPedirFrios)


    }
    private fun bocadilloHoy(botonCalientes: Button, botonFrios: Button) {
        // Cargar los JSON
        val bocatasFrios = loadBocadillos(this, R.raw.bocadillos_frios) // Usar función generalizada
        val bocatasCalientes = loadBocadillos(this, R.raw.bocadillos_calientes)

        // Obtener el día de la semana actual en español
        val hoy = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale("es", "ES")).lowercase()

        // Encontrar el bocadillo frío del día
        val bocadilloFrioHoy = bocatasFrios.find { it.diaSemana?.lowercase() == hoy }
        val bocadilloCalienteHoy = bocatasCalientes.find { it.diaSemana?.lowercase() == hoy }

        // Actualizar los TextViews
        botonFrios.text = bocadilloFrioHoy?.nombre ?: "No disponible"
        botonCalientes.text = bocadilloCalienteHoy?.nombre ?: "No disponible"

        botonPedirFrios.setOnClickListener {
            if (bocadilloFrioHoy != null) {
                hacerPedido(bocadilloFrioHoy.nombre)
            }else{
                Toast.makeText(this, "No hay bocadillo frío disponible hoy",Toast.LENGTH_SHORT).show()
            }
        }
        botonPedirCalientes.setOnClickListener {
            if (bocadilloCalienteHoy != null) {
                hacerPedido(bocadilloCalienteHoy.nombre)
            }else{
                Toast.makeText(this, "No hay bocadillo caliente disponible hoy",Toast.LENGTH_SHORT).show()
            }
        }

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

    private fun hacerPedido(nombreBocadillo:String){
        Toast.makeText(this, "Pedido hecho: $nombreBocadillo", Toast.LENGTH_SHORT).show()
    }

}