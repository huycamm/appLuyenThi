package com.example.appluyenthi.adapter

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.appluyenthi.R
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.model.Diem

class Activity_ListDiem : AppCompatActivity() {
    lateinit var db:CopyData
    lateinit var rs:Cursor
    lateinit var dsDiem:MutableList<Diem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_diem)

        val lvDiem = findViewById<ListView>(R.id.lvDiem)

        val monHoc = intent.getStringExtra("monhoc")
        dsDiem = mutableListOf()
        db = CopyData(this)
        rs = db.getDiem(monHoc!!)
        while (rs.moveToNext()){
            val diem = Diem(
                rs.getString(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getString(4),
            )
            dsDiem.add(diem)
        }

        lvDiem.adapter = AdapterDiem(this, dsDiem)

    }
}