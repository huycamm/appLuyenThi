package com.example.appluyenthi.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.appluyenthi.R
import com.example.appluyenthi.model.MonHoc
import com.squareup.picasso.Picasso

class AdapterMonHocAdMin(var activity: Activity, var ds:List<MonHoc>):ArrayAdapter<MonHoc>(activity, R.layout.monhoc_layout_admin) {
    override fun getCount(): Int {
        return ds.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = activity.layoutInflater.inflate(R.layout.monhoc_layout_admin, parent, false)

        val image = view.findViewById<ImageView>(R.id.imgMonHocAdmin)
        val tenMonHoc = view.findViewById<TextView>(R.id.txtMonHocAdmin)

        Picasso.get().load(ds[position].getAnh()).into(image)
        tenMonHoc.setText(ds[position].getTenMonHoc())

        return view
    }
}