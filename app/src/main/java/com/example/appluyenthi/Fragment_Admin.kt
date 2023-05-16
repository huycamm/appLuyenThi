package com.example.appluyenthi

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.appluyenthi.adapter.AdapterMonHocAdMin
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.model.MonHoc

class Fragment_Admin : Fragment() {
    lateinit var db: CopyData
    lateinit var rs: Cursor
    lateinit var dsMonHoc: MutableList<MonHoc>

    lateinit var lvMonHoc: ListView

    lateinit var adapter: AdapterMonHocAdMin

    lateinit var btnThemMonHoc:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = CopyData(requireContext())
        rs = db.getMonHoc()
        dsMonHoc = mutableListOf()
        while (rs.moveToNext()) {
            val monHoc = MonHoc(
                rs.getString(1),
                rs.getString(2)
            )
            dsMonHoc.add(monHoc)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__admin, container, false)

        lvMonHoc = view.findViewById(R.id.lvDsMonHoc)
        btnThemMonHoc = view.findViewById(R.id.btnThemMonHoc)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterMonHocAdMin(requireActivity(), dsMonHoc)

        lvMonHoc.adapter = adapter

        btnThemMonHoc.setOnClickListener {
            val intent = Intent(requireActivity(), Activity_Insert_Subject::class.java)
            startActivity(intent)
        }

        lvMonHoc.setOnItemClickListener { adapterView, view, i, l ->
            val monHoc = dsMonHoc[i].getTenMonHoc()
            val intent = Intent(requireActivity(),Activity_ListCauHoi::class.java)
            intent.putExtra("monhoc",monHoc)
            startActivity(intent)
        }

        registerForContextMenu(lvMonHoc)
    }

    private fun updateMonHoc() {
        dsMonHoc.clear()
        rs = db.getMonHoc()
        while (rs.moveToNext()) {
            val monHoc = MonHoc(
                rs.getString(1),
                rs.getString(2)
            )
            dsMonHoc.add(monHoc)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        updateMonHoc()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(1,1,1,"Xóa môn học")
        menu.setHeaderTitle("Delete")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val pos = info.position
        if(item.itemId == 1){
            db.deleteSubject(dsMonHoc[pos])
            updateMonHoc()
        }
        return super.onContextItemSelected(item)
    }

}