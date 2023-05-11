package com.example.appluyenthi.model

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

class TaiKhoan {
    private var mid:Int = 0
    private var mTenTaiKhoan:String = ""
    private var mMatKhau:String = ""
    private var mEmail:String = ""
    var phanQuyen:Int = 0

    constructor(mTenTaiKhoan:String, mMatKhau:String, mEmail:String, phanQuyen:Int){
        this.mTenTaiKhoan = mTenTaiKhoan
        this.mMatKhau = mMatKhau
        this.mEmail = mEmail
        this.phanQuyen = phanQuyen
    }

    constructor(mTenTaiKhoan:String, mEmail:String){
        this.mTenTaiKhoan = mTenTaiKhoan
        this.mEmail = mEmail
    }

    fun getmId():Int{
        return this.mid
    }

    fun getTenTaiKhoan():String{
        return this.mTenTaiKhoan
    }

    fun getMatKhau():String{
        return this.mMatKhau
    }
    fun getEmail():String{
        return this.mEmail
    }
    fun getPhanQuyen1():Int{
        return this.phanQuyen
    }

    fun checkPass():Boolean{
        return !TextUtils.isEmpty(mMatKhau) && mMatKhau.length>=6
    }
    fun checkEmail():Boolean{
        return !TextUtils.isEmpty(mEmail) && Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()
    }
}