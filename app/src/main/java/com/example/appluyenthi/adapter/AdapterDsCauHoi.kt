package com.example.appluyenthi.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.appluyenthi.R
import com.example.appluyenthi.model.CauHoi

class AdapterDsCauHoi(val activity: Activity, val ds:List<CauHoi>): ArrayAdapter<String>(activity, R.layout.cauhoi_layout) {
    override fun getCount(): Int {
        return ds.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = activity.layoutInflater.inflate(R.layout.cauhoi_layout, parent, false)

        val cauHoi = view.findViewById<TextView>(R.id.txtTenCauHoi)
        cauHoi.setText(ds[position].getCauHoi())

        return view
    }
}