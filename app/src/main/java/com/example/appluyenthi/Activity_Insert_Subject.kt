package com.example.appluyenthi

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.model.MonHoc

class Activity_Insert_Subject : AppCompatActivity() {
    lateinit var db:CopyData
    lateinit var ds:MutableList<MonHoc>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_subject)

        db = CopyData(this)

        val btnThem = findViewById<Button>(R.id.btnThem)
        val edtTen = findViewById<EditText>(R.id.edtTenMonHoc)
        val edtAnh = findViewById<EditText>(R.id.edtAnhMonHoc)

        btnThem.setOnClickListener {
            if (edtTen.equals("") || edtAnh.equals("")){
                Toast.makeText(this, "Nhập đầy đủ thông tin môn học!", Toast.LENGTH_SHORT).show()
            }else{
                val monHoc = MonHoc(edtTen.text.toString(),edtAnh.text.toString())
                db.insertSubject(monHoc)
                Toast.makeText(this, "Thêm môn học thành công!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

}