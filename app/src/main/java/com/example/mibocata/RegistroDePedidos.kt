package com.example.mibocata

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroDePedidos : AppCompatActivity() {
    private lateinit var botonPedir : ImageButton
    private lateinit var botonHistorico : ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_pedidos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        botonPedir = findViewById(R.id.pedirBocadillo)
        botonHistorico = findViewById(R.id.historicoPedidos)

        botonPedir.setOnClickListener {
            val intent = Intent(this,Pedir::class.java)
            startActivity(intent)
        }
        botonHistorico.setOnClickListener {
            val intent = Intent(this,ListadoBocadillos::class.java)
            startActivity(intent)
        }

    }

}