package com.example.appluyenthi.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.appluyenthi.R

class AdapterMaDe(val activity: Activity, val ds:List<String>):ArrayAdapter<String>(activity, R.layout.item_made) {
    override fun getCount(): Int {
        return ds.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = activity.layoutInflater.inflate(R.layout.item_made, parent, false)

        val maDe = view.findViewById<TextView>(R.id.txtMaDe)
        maDe.setText(ds[position])

        return view
    }
}