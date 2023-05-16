package com.example.appluyenthi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.appluyenthi.database.CopyData


class MyAccount : Fragment() {
    lateinit var userName:String
    lateinit var password:String
    lateinit var email:String

    lateinit var txtUserName:TextView
    lateinit var txtPassword:TextView
    lateinit var txtEmail:TextView

    lateinit var btnUpdate:Button
    lateinit var edtNewPass:EditText
    lateinit var edtConfirm:EditText
    lateinit var db:CopyData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userName = arguments?.getString("username")!!
        password = arguments?.getString("password")!!
        email = arguments?.getString("email")!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_account, container, false)
        txtUserName = view.findViewById(R.id.txtUserName)
        txtPassword = view.findViewById(R.id.txtPassword)
        txtEmail = view.findViewById(R.id.txtEmail)
        btnUpdate = view.findViewById(R.id.btnUpdate)
        edtNewPass = view.findViewById(R.id.edtPassNew)
        edtConfirm = view.findViewById(R.id.edtCfPassword)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtPassword.setText(password)
        txtUserName.setText(userName)
        txtEmail.setText(email)

        btnUpdate.setOnClickListener {
            val newPass = edtNewPass.text.toString()
            val confirm = edtConfirm.text.toString()
            if(newPass.equals("") || confirm.equals("")){
                Toast.makeText(requireContext(), "Nhập đầy đủ mật khẩu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!newPass.equals(confirm)){
                Toast.makeText(requireContext(), "Mật khẩu không khớp nhau", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(newPass.equals(txtPassword.text.toString())){
                Toast.makeText(requireContext(), "Mật khẩu mới phải khác mật khẩu cũ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            db = CopyData(requireContext())
            db.changePassword(txtUserName.text.toString(), newPass)
            Toast.makeText(requireContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show()
            txtPassword.setText(newPass)
            edtNewPass.setText("")
            edtConfirm.setText("")
        }
    }
}