package com.example.appluyenthi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.databinding.ActivityInsertCauHoiBinding
import com.example.appluyenthi.model.CauHoi

lateinit var bindingAdd:ActivityInsertCauHoiBinding
class Activity_InsertCauHoi : AppCompatActivity() {
    lateinit var monHoc:String
    lateinit var db:CopyData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAdd = ActivityInsertCauHoiBinding.inflate(layoutInflater)
        setContentView(bindingAdd.root)

        monHoc = intent.getStringExtra("monhoc")!!
        bindingAdd.txtAddMonHoc.setText(monHoc)

        bindingAdd.btnThem.setOnClickListener {
            val maDe = bindingAdd.edtAddMaDe.text.toString()
            val cauH = bindingAdd.edtAddCauHoi.text.toString()
            val ketQua = bindingAdd.edtAddKetQua.text.toString()
            val answer1 = bindingAdd.edtAddDapAn1.text.toString()
            val answer2 = bindingAdd.edtAddDapAn2.text.toString()
            val answer3 = bindingAdd.edtAddDapAn3.text.toString()
            val answer4 = bindingAdd.edtAddDapAn4.text.toString()
            val anh = bindingAdd.edtAnhAdd.text.toString()

            val cauHoi = CauHoi(monHoc,maDe,cauH, ketQua, answer1, answer2, answer3, answer4, anh)

            db = CopyData(this)
            db.insertCauHoi(cauHoi)
            Toast.makeText(this, "Thêm câu hỏi thành công!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Activity_ListCauHoi::class.java)
            intent.putExtra("monhoc",monHoc)
            finish()
            startActivity(intent)
        }

        bindingAdd.btnHuy.setOnClickListener {
            val intent = Intent(this, Activity_ListCauHoi::class.java)
            intent.putExtra("monhoc",monHoc)
            finish()
            startActivity(intent)
        }
    }
}