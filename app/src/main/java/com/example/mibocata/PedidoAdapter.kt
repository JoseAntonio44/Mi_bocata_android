package com.example.mibocata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PedidoAdapter (context: Context, pedido: List<Pedido>) : ArrayAdapter<Pedido>(context,0,pedido){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_registro, parent, false)

        val pedido = getItem(position)
        val nombreBocadillo = view.findViewById<TextView>(R.id.bocadillo_nombre)
        val diaBocadillo = view.findViewById<TextView>(R.id.bocadillo_Dia)


        nombreBocadillo.text= pedido?.nombreBocadillo
        diaBocadillo.text= pedido?.fecha

        return view

    }

}