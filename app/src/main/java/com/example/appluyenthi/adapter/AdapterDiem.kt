package com.example.appluyenthi.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.appluyenthi.R
import com.example.appluyenthi.model.Diem

class AdapterDiem(val activity: Activity, val dsDiem:MutableList<Diem>):ArrayAdapter<Diem>(activity, R.layout.diem_layout) {
    override fun getCount(): Int {
        return dsDiem.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = activity.layoutInflater.inflate(R.layout.diem_layout,parent,false)

        val txtDiem = view.findViewById<TextView>(R.id.txtDiemSo)
        val txtMaDe = view.findViewById<TextView>(R.id.txtMaDeD)
        val ngay = view.findViewById<TextView>(R.id.txtNgay)

        txtDiem.setText(dsDiem[position].getDiem().toString())
        txtMaDe.setText(dsDiem[position].getMaDe().toString())
        ngay.setText(dsDiem[position].getDate())

        return view
    }
}