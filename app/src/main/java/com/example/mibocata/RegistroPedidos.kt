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

class RegistroPedidos : AppCompatActivity() {
    private lateinit var botonPerfil : ImageButton
    private lateinit var botonListadoBocadillos: ImageButton


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

        botonPerfil = findViewById(R.id.perfil)
        botonListadoBocadillos = findViewById(R.id.listaBocatas)

        val imagenPedir: ImageView = findViewById(R.id.imageBocata)
        imagenPedir.setOnClickListener{
            val intent = Intent(this,Pedir::class.java)
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



    }

}