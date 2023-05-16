package com.example.appluyenthi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.databinding.ActivityDangKyBinding
import com.example.appluyenthi.model.TaiKhoan

lateinit var binding3: ActivityDangKyBinding
class DangKy : AppCompatActivity() {
    lateinit var db:CopyData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding3 = ActivityDangKyBinding.inflate(layoutInflater)
        setContentView(binding3.root)

        binding3.btndkDangNhap.setOnClickListener {
            val intent = Intent(this, DangNhap::class.java)
            finish()
            startActivity(intent)
        }

        binding3.btndkDangKy.setOnClickListener {
            val userName = binding3.edtUserName.text.toString()
            val password = binding3.edtPassword.text.toString()
            val confirm = binding3.edtPasswordConfirm.text.toString()
            val email = binding3.edtEmail.text.toString()
            val taiKhoan = TaiKhoan(userName,password,email,1)

            if (!password.equals(confirm)){
                Toast.makeText(this, "Mật khẩu không khớp nhau!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(taiKhoan.checkEmail() && taiKhoan.checkPass()){
                db = CopyData(this)
                db.insertUser(taiKhoan)
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                binding3.edtUserName.setText("")
                binding3.edtPassword.setText("")
                binding3.edtEmail.setText("")
                binding3.edtPasswordConfirm.setText("")
                binding3.edtUserName.requestFocus()
            }else{
                Toast.makeText(this, "Kiểm tra lại thông tin đăng ký!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}