package com.example.mibocata

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Perfil : AppCompatActivity() {

    private lateinit var botonRegistroPedidos : ImageButton
    private lateinit var botonListadoBocadillos: ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imagenPedir: ImageView = findViewById(R.id.imageBocata)
        imagenPedir.setOnClickListener{
            val intent = Intent(this,Pedir::class.java)
            startActivity(intent)
        }


        botonRegistroPedidos = findViewById(R.id.historicoPedidos)
        botonRegistroPedidos.setOnClickListener {
            val intent = Intent(this,RegistroPedidos::class.java)
            startActivity(intent)
        }
        botonListadoBocadillos = findViewById(R.id.listaBocatas)
        botonListadoBocadillos.setOnClickListener {
            val intent = Intent(this,ListadoBocadillos::class.java)
            startActivity(intent)
        }

    }
}