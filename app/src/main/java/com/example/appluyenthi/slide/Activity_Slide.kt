package com.example.appluyenthi.slide

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.appluyenthi.MainActivity
import com.example.appluyenthi.R
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.model.CauHoi
import com.example.appluyenthi.model.Diem
import java.sql.Date

class Activity_Slide : FragmentActivity() {
    lateinit var viewPage: ViewPager2

    lateinit var db: CopyData
    lateinit var rs: Cursor
    lateinit var dsCauHoi: MutableList<CauHoi>

    lateinit var monHoc: String
    var maDe: Int = 0

    lateinit var userName: String
    lateinit var mail: String
    lateinit var pass: String
    var phanQuyen: Int = 0

    lateinit var txtNopBai: TextView
    lateinit var txtXemDiem:TextView

    var checkAns = 0

    lateinit var btnHome: ImageButton

    var soCauDung = 0
    var soCauSai = 0
    var soCauChuaTraLoi = 0

    //lateinit var diolog:AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)

        btnHome = findViewById(R.id.imageButton)
        viewPage = findViewById(R.id.viewPager)

        monHoc = intent.getStringExtra("monhoc")!!
        maDe = intent.getIntExtra("made", 0)

        db = CopyData(this)
        rs = db.getCauHoi(monHoc, maDe)

        dsCauHoi = mutableListOf()

        while (rs.moveToNext()) {
            val itemCauHoi = CauHoi(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9)
            )
            dsCauHoi.add(itemCauHoi)
        }

        val adapter = AdapterSlide(this)
        viewPage.adapter = adapter

        actionHome()

        txtNopBai = findViewById(R.id.txtNopBai)
        txtNopBai.setOnClickListener {
            showDiologNopBai()
        }

        txtXemDiem = findViewById(R.id.txtXemDiem)
        txtXemDiem.setOnClickListener {
            showDiologXemDiem()
        }
    }

    private fun showDiologXemDiem() {
        var diolog:AlertDialog
        val build = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.custom_diolog_diem,null, false)

        for (i in dsCauHoi.indices){
            if(dsCauHoi[i].getTraLoi().equals("")){
                soCauChuaTraLoi++
            }else if(dsCauHoi[i].getketQua().equals(dsCauHoi[i].getTraLoi())){
                soCauDung++
            }else{
                soCauSai++
            }
        }
        val txtSoCauDung = view.findViewById<TextView>(R.id.txtSoCauDung)
        val txtSoCauSai = view.findViewById<TextView>(R.id.txtSoCauSai)
        val txtSoCauChuaTraLoi = view.findViewById<TextView>(R.id.txtSoChuaTL)
        val txtDiem = view.findViewById<TextView>(R.id.txtDiem)

        val btnThoat = view.findViewById<Button>(R.id.btnThoat)
        val btnLuu = view.findViewById<Button>(R.id.btnLuuDiem)

        txtSoCauDung.setText(soCauDung.toString())
        txtSoCauSai.setText(soCauSai.toString())
        txtSoCauChuaTraLoi.setText(soCauChuaTraLoi.toString())
        txtDiem.setText((soCauDung*10).toString())

        btnThoat.setOnClickListener {
            finish()
        }
        btnLuu.setOnClickListener {
            val date = Date(System.currentTimeMillis())
            val diem = Diem(monHoc,maDe,soCauDung*10,date.toString())
            db.insertScore(diem)
            Toast.makeText(this, "Lưu điểm thành công!", Toast.LENGTH_SHORT).show()
            finish()
        }
        build.setCancelable(false)

        build.setView(view)
        diolog = build.create()
        diolog.show()
    }

    private fun showDiologNopBai() {
        val diolog = AlertDialog.Builder(this)
        diolog.setTitle("Confirm")
        diolog.setMessage("Bạn có muốn nộp bài?")
        diolog.setNegativeButton("No") { listener, i ->
            listener.dismiss()
        }
        diolog.setPositiveButton("Yes") { listener, i ->
            checkAns = 1
            listener.dismiss()
            txtNopBai.visibility = View.GONE
            txtXemDiem.visibility = View.VISIBLE
            if (viewPage.currentItem >=5){
                viewPage.currentItem -= 4
            }else{
                viewPage.currentItem += 4
            }
        }
        diolog.show()
    }


    private inner class AdapterSlide(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return dsCauHoi.size
        }

        override fun createFragment(position: Int): Fragment {
            return Fragment_Slide().create(position, checkAns)
        }

    }

    fun getCauHoi(): MutableList<CauHoi> {
        return dsCauHoi
    }

    fun actionHome() {
        userName = intent.getStringExtra("username")!!
        mail = intent.getStringExtra("email")!!
        pass = intent.getStringExtra("password")!!
        phanQuyen = intent.getIntExtra("phanquyen", 1)!!

        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("username", userName)
            intent.putExtra("password", pass)
            intent.putExtra("email", mail)
            intent.putExtra("phanquyen", phanQuyen)
            finish()
            startActivity(intent)
        }
    }
}
