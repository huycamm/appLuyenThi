package com.example.appluyenthi.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.appluyenthi.model.CauHoi
import com.example.appluyenthi.model.Diem
import com.example.appluyenthi.model.MonHoc
import com.example.appluyenthi.model.TaiKhoan
import java.io.File
import java.io.FileOutputStream


class CopyData (val context: Context):SQLiteOpenHelper(context, "dbtracnghiem.db", null, 1){
    lateinit var db:SQLiteDatabase
    lateinit var rs:Cursor

    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun copyData():SQLiteDatabase{
        val dbFile = context.getDatabasePath("dbtracnghiem.db")
        val file = File(dbFile.toString())
        if(file.exists()){
            Log.e("data","File đã tồn tại")
        }else{
            openData(dbFile)
        }
        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    private fun openData(dbFile: File?) {
        val input = context.assets.open("dbtracnghiem.db")
        val outputStream = FileOutputStream(dbFile)
        val buffer = ByteArray(1024)
        while(input.read(buffer)>0){
            outputStream.write(buffer)
            Log.e("database","Writing")
        }
        outputStream.flush()
        outputStream.close()
        input.close()
    }

    fun getCauHoi(monHoc:String, maDe:Int):Cursor{
        db = this.readableDatabase
        rs = db.rawQuery("Select * from question where monhoc = '$monHoc' and made = '$maDe'", null)
        return rs
    }


    fun insertUser(taiKhoan:TaiKhoan){
        db = this.writableDatabase
        var cv = ContentValues()
        cv.put("username", taiKhoan.getTenTaiKhoan())
        cv.put("password", taiKhoan.getMatKhau())
        cv.put("email", taiKhoan.getEmail())
        cv.put("phanquyen", taiKhoan.getPhanQuyen1())

        db.insert("user", null, cv)
        db.close()
    }

    fun getTaiKhoan():Cursor{
        db = this.readableDatabase
        rs = db.rawQuery("Select * from user", null)
        return rs
    }

    fun changePassword(user:String,newPass:String){
        db = this.writableDatabase
        val cv = ContentValues()
        cv.put("password", newPass)

        db.update("user",cv, "username=?", arrayOf(user))
        db.close()
    }

    fun getMonHoc():Cursor{
        db = this.readableDatabase
        rs = db.rawQuery("select * from subject",null)
        return rs
    }

    fun insertSubject(monHoc:MonHoc){
        db = this.writableDatabase
        val cv = ContentValues()
        cv.put("name_subject", monHoc.getTenMonHoc())
        cv.put("anh",monHoc.getAnh())

        db.insert("subject",null, cv)
        db.close()
    }

    fun deleteSubject(monHoc:MonHoc){
        db = this.writableDatabase
        db.delete("subject","name_subject=?", arrayOf(monHoc.getTenMonHoc()))
        db.close()
    }

    fun getCauHoi(monHoc:String):Cursor{
        db = this.readableDatabase
        rs = db.rawQuery("select * from question where monhoc = '${monHoc}' order by made",null)
        return rs
    }

    fun deleteCauHoi(cauHoi: CauHoi){
        db = this.writableDatabase
        db.delete("question", "_id=?", arrayOf(cauHoi.getId().toString()))
        db.close()
    }

    fun updateCauHoi(cauHoi: CauHoi){
        db = this.writableDatabase
        val cv = ContentValues()
        cv.put("monhoc",cauHoi.getMonHoc())
        cv.put("made", cauHoi.getMaDe().toInt())
        cv.put("cauhoi", cauHoi.getCauHoi())
        cv.put("ketqua",cauHoi.getketQua())
        cv.put("answer1",cauHoi.getAnswer1())
        cv.put("answer2",cauHoi.getAnswer2())
        cv.put("answer3",cauHoi.getAnswer3())
        cv.put("answer4",cauHoi.getAnswer4())
        cv.put("anh", cauHoi.getAnh())

        db.update("question",cv, "_id=?", arrayOf(cauHoi.getId().toString()))
        db.close()
    }

    fun insertCauHoi(cauHoi: CauHoi){
        db = this.writableDatabase
        val cv = ContentValues()
        cv.put("monhoc",cauHoi.getMonHoc())
        cv.put("made", cauHoi.getMaDe().toInt())
        cv.put("cauhoi", cauHoi.getCauHoi())
        cv.put("ketqua",cauHoi.getketQua())
        cv.put("answer1",cauHoi.getAnswer1())
        cv.put("answer2",cauHoi.getAnswer2())
        cv.put("answer3",cauHoi.getAnswer3())
        cv.put("answer4",cauHoi.getAnswer4())
        cv.put("anh", cauHoi.getAnh())

        db.insert("question",null,cv)
        db.close()
    }

    fun insertScore(score:Diem){
        db = this.writableDatabase
        val cv = ContentValues()
        cv.put("monhoc",score.getMonHoc())
        cv.put("made",score.getMaDe())
        cv.put("diem",score.getDiem())
        cv.put("date",score.getDate())

        db.insert("score", null, cv)
        db.close()
    }

    fun getDiem(monHoc:String):Cursor{
        db = this.readableDatabase
        rs = db.rawQuery("select * from score where monhoc = '${monHoc}' order by id desc",null)
        return rs
    }

}