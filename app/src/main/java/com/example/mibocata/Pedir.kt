package com.example.mibocata

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
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
    private lateinit var botonPerfil : ImageButton
    private lateinit var botonHistorico : ImageButton

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

        botonPerfil = findViewById(R.id.perfil)
        botonHistorico = findViewById(R.id.historicoPedidos)

        botonPerfil.setOnClickListener {
            val intent = Intent(this,Perfil::class.java)
            startActivity(intent)
        }
        botonHistorico.setOnClickListener {
            val intent = Intent(this,ListadoBocadillos::class.java)
            startActivity(intent)
        }

        val botonFrios = findViewById<Button>(R.id.botonFrios)
        val botonCalientes = findViewById<Button>(R.id.botonCalientes)



        bocadilloHoy(botonCalientes,botonFrios)
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