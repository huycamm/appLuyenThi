package com.example.appluyenthi

import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.databinding.ActivityDangNhapBinding

lateinit var binding2: ActivityDangNhapBinding
class DangNhap : AppCompatActivity() {
    lateinit var db:CopyData
    lateinit var rs:Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityDangNhapBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        //Copy database
        val copyData = CopyData(this)
        copyData.copyData()

        binding2.btndnDangKy.setOnClickListener {
            val intent = Intent(this, DangKy::class.java)
            finish()
            startActivity(intent)
        }


        binding2.btndnDangNhap.setOnClickListener {
            val tenTk = binding2.edtUserNameDN.text.toString()
            val mk = binding2.edtPasswordDN.text.toString()
            if(tenTk.equals("") || mk.equals("")){
                Toast.makeText(this, "Nhập đầy đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show()
            }else{
                db = CopyData(this)
                rs = db.getTaiKhoan()
                var check = false
                while (rs.moveToNext()){
                    val userName = rs.getString(1)
                    val password = rs.getString(2)
                    val email = rs.getString(3)
                    val phanQuyen = rs.getInt(4)
                    if(userName.equals(tenTk) && password.equals(mk)){
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("username", userName)
                        intent.putExtra("password", password)
                        intent.putExtra("email", email)
                        intent.putExtra("phanquyen",phanQuyen)
                        finish()
                        startActivity(intent)
                        check = true
                    }
                }
                rs.close()
                if(!check){
                    val ad = AlertDialog.Builder(this)
                    ad.setTitle("Đăng nhập")
                    ad.setMessage("Sai tên đăng nhập hoặc mật khẩu")
                    ad.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
                        binding2.edtUserNameDN.setText("")
                        binding2.edtPasswordDN.setText("")
                        binding2.edtUserNameDN.requestFocus()
                    })
                    ad.show()
                }
            }
        }
    }
}