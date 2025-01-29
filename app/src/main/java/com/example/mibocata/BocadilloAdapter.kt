package com.example.mibocata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class BocadilloAdapter (context: Context, bocadillo: List<Bocadillo>) : ArrayAdapter<Bocadillo>(context, 0, bocadillo) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_listado, parent, false)

        val bocadillo =getItem(position)

        val nombreBocadillo = view.findViewById<TextView>(R.id.bocadillo_nombre)
        val diaBocadillo = view.findViewById<TextView>(R.id.bocadillo_Dia)


        nombreBocadillo.text= bocadillo?.nombre
        diaBocadillo.text= bocadillo?.diaSemana

        return view
    }
}