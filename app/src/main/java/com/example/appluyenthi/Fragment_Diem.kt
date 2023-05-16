package com.example.appluyenthi

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.example.appluyenthi.adapter.AdapterMonHocAdMin
import com.example.appluyenthi.database.CopyData
import com.example.appluyenthi.model.MonHoc


class Fragment_Diem : Fragment() {
    lateinit var db: CopyData
    lateinit var rs: Cursor
    lateinit var dsMonHoc: MutableList<MonHoc>

    lateinit var lvMonHoc: ListView

    lateinit var adapter: AdapterMonHocAdMin
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
        val view = inflater.inflate(R.layout.fragment__diem, container, false)
        lvMonHoc = view.findViewById(R.id.lvDSMonHoc)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterMonHocAdMin(requireActivity(), dsMonHoc)

        lvMonHoc.adapter = adapter

        lvMonHoc.setOnItemClickListener { adapterView, view, i, l ->
            val monHoc = dsMonHoc[i].getTenMonHoc()
            val intent = Intent(requireActivity(), Activity_ListDiem::class.java)
            intent.putExtra("monhoc",monHoc)
            startActivity(intent)
        }
    }

}