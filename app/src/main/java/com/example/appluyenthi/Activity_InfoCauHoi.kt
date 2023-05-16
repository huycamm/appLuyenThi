package com.example.appluyenthi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.databinding.ActivityInfoCauHoiBinding
import com.example.appluyenthi.model.CauHoi

lateinit var bindingIf:ActivityInfoCauHoiBinding
class Activity_InfoCauHoi : AppCompatActivity() {
    lateinit var db:CopyData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingIf = ActivityInfoCauHoiBinding.inflate(layoutInflater)
        setContentView(bindingIf.root)

        db = CopyData(this)

        val id = intent.getIntExtra("id",0)
        val monHoc = intent.getStringExtra("monhoc")
        val maDe = intent.getStringExtra("made")
        val cauHoi = intent.getStringExtra("cauhoi")
        val ketQua = intent.getStringExtra("ketqua")
        val dapAn1 = intent.getStringExtra("dapan1")
        val dapAn2 = intent.getStringExtra("dapan2")
        val dapAn3 = intent.getStringExtra("dapan3")
        val dapAn4 = intent.getStringExtra("dapan4")
        val anh = intent.getStringExtra("anh")

        bindingIf.txtIfMonHoc.setText(monHoc)
        bindingIf.edtIfCauHoi.setText(cauHoi)
        bindingIf.edtIfMaDe.setText(maDe)
        bindingIf.edtIfDapAn1.setText(dapAn1)
        bindingIf.edtIfDapAn2.setText(dapAn2)
        bindingIf.edtIfDapAn3.setText(dapAn3)
        bindingIf.edtIfDapAn4.setText(dapAn4)
        bindingIf.edtIfKetQua.setText(ketQua)
        bindingIf.edtAnhIf.setText(anh)

        bindingIf.btnLuu.setOnClickListener {
            val cauhoi = CauHoi(
                id,
                bindingIf.txtIfMonHoc.text.toString(),
                bindingIf.edtIfMaDe.text.toString(),
                bindingIf.edtIfCauHoi.text.toString(),
                bindingIf.edtIfDapAn1.text.toString(),
                bindingIf.edtIfDapAn2.text.toString(),
                bindingIf.edtIfDapAn3.text.toString(),
                bindingIf.edtIfDapAn4.text.toString(),
                bindingIf.edtIfKetQua.text.toString(),
                bindingIf.edtAnhIf.text.toString()
            )
            db.updateCauHoi(cauhoi)
            val intent = Intent(this, Activity_ListCauHoi::class.java)
            intent.putExtra("monhoc",monHoc)
            Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(intent)
        }

        bindingIf.btnHuy.setOnClickListener {
            val intent = Intent(this, Activity_ListCauHoi::class.java)
            intent.putExtra("monhoc",monHoc)
            finish()
            startActivity(intent)
        }
    }
}