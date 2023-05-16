package com.example.appluyenthi

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.appluyenthi.adapter.AdapterDsCauHoi
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.model.CauHoi

class Activity_ListCauHoi : AppCompatActivity() {
    lateinit var db:CopyData
    lateinit var rs:Cursor
    lateinit var dsCauHoi:MutableList<CauHoi>
    lateinit var adapter: AdapterDsCauHoi

    lateinit var monHoc:String

    lateinit var btnThem:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cau_hoi)

        monHoc = intent.getStringExtra("monhoc")!!

        btnThem = findViewById(R.id.btnThemCauHoi)

        dsCauHoi = mutableListOf()

        val lvCauHoi = findViewById<ListView>(R.id.lvDsCauHoi)
        db = CopyData(this)
        rs = db.getCauHoi(monHoc)
        while (rs.moveToNext()){
            val cauHoi = CauHoi(
                rs.getInt(0),
                rs.getString(1),
                rs.getInt(2).toString(),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9)
            )
            dsCauHoi.add(cauHoi)
        }
        adapter = AdapterDsCauHoi(this, dsCauHoi)
        lvCauHoi.adapter = adapter

        lvCauHoi.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this,Activity_InfoCauHoi::class.java)
            intent.putExtra("id",dsCauHoi[i].getId())
            intent.putExtra("monhoc",dsCauHoi[i].getMonHoc())
            intent.putExtra("made",dsCauHoi[i].getMaDe())
            intent.putExtra("cauhoi",dsCauHoi[i].getCauHoi())
            intent.putExtra("ketqua",dsCauHoi[i].getketQua())
            intent.putExtra("dapan1",dsCauHoi[i].getAnswer1())
            intent.putExtra("dapan2",dsCauHoi[i].getAnswer2())
            intent.putExtra("dapan3",dsCauHoi[i].getAnswer3())
            intent.putExtra("dapan4",dsCauHoi[i].getAnswer4())
            intent.putExtra("anh",dsCauHoi[i].getAnh())

            finish()
            startActivity(intent)
        }

        //code button thêm
        btnThem.setOnClickListener {
            val intent = Intent(this, Activity_InsertCauHoi::class.java)
            intent.putExtra("monhoc",monHoc)
            finish()
            startActivity(intent)
        }

        registerForContextMenu(lvCauHoi)
    }



    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(2,2,1,"Xóa câu hỏi")
        menu?.setHeaderTitle("Delete")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val pos = info.position
        if(item.itemId==2){
            db.deleteCauHoi(dsCauHoi[pos])
            Toast.makeText(this, "Xóa câu hỏi thành công", Toast.LENGTH_SHORT).show()
            updateLvCauHoi()
        }
        return super.onContextItemSelected(item)
    }

    fun updateLvCauHoi(){
        dsCauHoi.clear()
        rs = db.getCauHoi(monHoc)
        while (rs.moveToNext()){
            val cauHoi = CauHoi(
                rs.getInt(0),
                rs.getString(1),
                rs.getInt(2).toString(),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9)
            )
            dsCauHoi.add(cauHoi)
        }
        adapter.notifyDataSetChanged()
    }
}