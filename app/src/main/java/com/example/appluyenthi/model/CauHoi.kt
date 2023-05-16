package com.example.appluyenthi.model

class CauHoi {
    private var id:Int=0
    private var cauHoi:String=""
    private var ketQua:String=""
    private var monHoc:String=""
    private var maDe:String =""
    private var answer1:String=""
    private var answer2:String=""
    private var answer3:String=""
    private var answer4:String=""
    private var anh:String = ""
    private var traLoi:String = ""

    constructor(monHoc:String,maDe:String,cauHoi:String,ketQua:String,answer1:String,answer2:String,answer3:String,answer4:String,anh:String){
        this.cauHoi = cauHoi
        this.ketQua = ketQua
        this.monHoc = monHoc
        this.maDe = maDe
        this.answer1 =answer1
        this.answer2 =answer2
        this.answer3 =answer3
        this.answer4 =answer4
        this.anh = anh
    }
    constructor(id:Int,monHoc:String,maDe:String,cauHoi:String,ketQua:String,answer1:String,answer2:String,answer3:String,answer4:String,anh:String){
        this.cauHoi = cauHoi
        this.ketQua = ketQua
        this.monHoc = monHoc
        this.maDe = maDe
        this.answer1 =answer1
        this.answer2 =answer2
        this.answer3 =answer3
        this.answer4 =answer4
        this.anh = anh
        this.id = id
    }

    fun getId():Int{
        return this.id
    }
    fun getCauHoi():String{
        return this.cauHoi
    }
    fun getketQua():String{
        return this.ketQua
    }
    fun getMonHoc():String{
        return this.monHoc
    }
    fun getAnswer1():String{
        return this.answer1
    }
    fun getAnswer2():String{
        return this.answer2
    }
    fun getAnswer3():String{
        return this.answer3
    }
    fun getAnswer4():String{
        return this.answer4
    }
    fun getMaDe():String{
        return this.maDe
    }
    fun getAnh():String{
        return this.anh
    }

    fun getTraLoi():String{
        return this.traLoi
    }

    fun setTraLoi(traLoi:String){
        this.traLoi = traLoi
    }
}