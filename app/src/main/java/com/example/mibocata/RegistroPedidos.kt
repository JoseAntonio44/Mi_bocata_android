package com.example.mibocata

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class RegistroPedidos : AppCompatActivity() {
    private lateinit var botonPerfil: ImageButton
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

        // Configuración de botones y listeners
        botonPerfil = findViewById(R.id.perfil)
        botonListadoBocadillos = findViewById(R.id.listaBocatas)

        val imagenPedir: ImageView = findViewById(R.id.imageBocata)
        imagenPedir.setOnClickListener {
            val intent = Intent(this, Pedir::class.java)
            startActivity(intent)
        }

        botonPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        botonListadoBocadillos.setOnClickListener {
            val intent = Intent(this, ListadoBocadillos::class.java)
            startActivity(intent)
        }

        // Cargar y mostrar pedidos
        val pedidos = loadPedidos(this)
        val listViewPedidos = findViewById<ListView>(R.id.listViewPedidos)
        val adapter = PedidoAdapter(this, pedidos)
        listViewPedidos.adapter = adapter
    }

    private fun loadPedidos(context: Context): List<Pedido> {
        val inputStream = resources.openRawResource(R.raw.pedidos) // Abre el JSON
        val reader = InputStreamReader(inputStream) // Canal para leer el JSON
        return try {
            val type = object : TypeToken<List<Pedido>>() {}.type // Define el tipo de datos
            val pedidos: List<Pedido> = Gson().fromJson(reader, type) // Convierte el JSON en objetos
            pedidos
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Retorna una lista vacía si ocurre algún error
        } finally {
            reader.close() // Cierra el lector para liberar recursos
        }
    }
}
