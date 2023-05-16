package com.example.appluyenthi.model

import java.sql.Date

class Diem {
    private var id:Int = 0
    private var monHoc:String=""
    private var maDe:Int=0
    private var diem:Int=0
    private var date:String

    constructor(monHoc:String, maDe:Int, diem:Int, date: String){
        this.diem = diem
        this.maDe = maDe
        this.monHoc = monHoc
        this.date = date
    }

    fun getMonHoc():String{
        return this.monHoc
    }
    fun getDiem():Int{
        return this.diem
    }
    fun getMaDe():Int{
        return this.maDe
    }
    fun getDate():String{
        return this.date
    }
}