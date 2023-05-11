package com.example.appluyenthi

import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.appluyenthi.adapter.AdapterMaDe
import com.example.appluyenthi.adapter.AdapterMonHoc
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.model.MonHoc
import com.example.appluyenthi.slide.Activity_Slide


class Fragment_Monhoc : Fragment() {
    lateinit var db:CopyData
    lateinit var rs:Cursor
    lateinit var dsMonHoc:MutableList<MonHoc>

    lateinit var monHoc:String
    var maDe:Int = 0

    lateinit var diolog:AlertDialog

    lateinit var userName:String
    lateinit var email:String
    lateinit var pass:String
    var phanQuyen:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userName = arguments?.getString("username")!!
        email = arguments?.getString("email")!!
        pass = arguments?.getString("password")!!
        phanQuyen = arguments?.getInt("phanquyen",1)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment__monhoc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gvMonHoc = view.findViewById<GridView>(R.id.gvMonHoc)
        dsMonHoc = mutableListOf()

        db = CopyData(requireContext())
        rs = db.getMonHoc()
        while (rs.moveToNext()){
            val monHoc = MonHoc(
                rs.getString(1),
                rs.getString(2))
            dsMonHoc.add(monHoc)
        }

        gvMonHoc.adapter = AdapterMonHoc(requireActivity(), dsMonHoc)

        gvMonHoc.setOnItemClickListener { adapterView, view, i, l ->
            monHoc = dsMonHoc[i].getTenMonHoc()
            showDiolog()
        }
    }

    private fun showDiolog() {
        val dsMaDe = mutableListOf<String>()
        dsMaDe.add("Đề 1")
        dsMaDe.add("Đề 2")
        dsMaDe.add("Đề 3")
        dsMaDe.add("Đề 4")

        val build = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.customdiolog, null, false)

        val gvMaDe = view.findViewById<GridView>(R.id.gvMaDe)
        gvMaDe.adapter = AdapterMaDe(requireActivity(), dsMaDe)
        gvMaDe.setOnItemClickListener { adapterView, view, i, l ->
            maDe = i+1
            val intent = Intent(requireActivity(), Activity_Slide::class.java)
            intent.putExtra("monhoc", monHoc)
            intent.putExtra("made",maDe)
            intent.putExtra("username", userName)
            intent.putExtra("password",pass)
            intent.putExtra("email", email)
            intent.putExtra("phanquyen",phanQuyen)

            startActivity(intent)
        }

        build.setView(view)
        diolog = build.create()
        diolog.show()
    }

}