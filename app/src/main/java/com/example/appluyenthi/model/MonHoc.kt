package com.example.appluyenthi.model

class MonHoc {
    private var tenMonHoc:String = ""
    private var anhMonHoc:String = ""

    constructor(ten:String, anh:String){
        this.tenMonHoc = ten
        this.anhMonHoc = anh
    }

    fun getTenMonHoc():String{
        return this.tenMonHoc
    }

    fun getAnh():String{
        return this.anhMonHoc
    }
}