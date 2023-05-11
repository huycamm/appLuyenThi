package com.example.appluyenthi.slide

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.appluyenthi.R
import com.example.appluyenthi.model.CauHoi
import com.squareup.picasso.Picasso

class Fragment_Slide : Fragment() {
    var numberFragment:Int = 0
    var checkAns = 0

    lateinit var dsCauHoi:MutableList<CauHoi>

    lateinit var numberQ:TextView
    lateinit var cauHoi:TextView
    lateinit var imageCauHoi:ImageView
    lateinit var answer1:RadioButton
    lateinit var answer2:RadioButton
    lateinit var answer3:RadioButton
    lateinit var answer4:RadioButton
    lateinit var btnGroup: RadioGroup

    lateinit var txtThuTu:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        numberFragment = arguments?.getInt("position")!!
        checkAns = arguments?.getInt("checkans")!!

        val activitySlide = activity as Activity_Slide
        dsCauHoi = activitySlide.getCauHoi()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__slide, container, false)
        numberQ = view.findViewById(R.id.txtNumberQ)
        cauHoi = view.findViewById(R.id.txtCauHoi)
        imageCauHoi = view.findViewById(R.id.imgCauHoi)
        answer1 = view.findViewById(R.id.rbt1)
        answer2 = view.findViewById(R.id.rbt2)
        answer3 = view.findViewById(R.id.rbt3)
        answer4 = view.findViewById(R.id.rbt4)
        btnGroup = view.findViewById(R.id.radioGroup)

        txtThuTu = requireActivity().findViewById(R.id.txtThuTu)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberQ.setText("Câu "+(numberFragment+1))
        cauHoi.setText(dsCauHoi[numberFragment].getCauHoi())
        Picasso.get().load(dsCauHoi[numberFragment].getAnh()).into(imageCauHoi)
        answer1.setText(dsCauHoi[numberFragment].getAnswer1())
        answer2.setText(dsCauHoi[numberFragment].getAnswer2())
        answer3.setText(dsCauHoi[numberFragment].getAnswer3())
        answer4.setText(dsCauHoi[numberFragment].getAnswer4())

        btnGroup.setOnCheckedChangeListener { radioGroup, id ->
            dsCauHoi[numberFragment].setTraLoi(getCauTraLoi(id))
        }

        if(checkAns!=0){
            answer1.isClickable = false
            answer2.isClickable = false
            answer3.isClickable = false
            answer4.isClickable = false
            checkAns(dsCauHoi[numberFragment].getketQua())
        }
    }

    fun checkAns(ketQua:String){
        if(ketQua=="A"){
            answer1.setBackgroundColor(Color.GREEN)
        }
        if(ketQua=="B"){
            answer2.setBackgroundColor(Color.GREEN)
        }
        if(ketQua=="C"){
            answer3.setBackgroundColor(Color.GREEN)
        }
        if(ketQua=="D"){
            answer4.setBackgroundColor(Color.GREEN)
        }
    }

    fun getCauTraLoi(id:Int):String{
        if(id == R.id.rbt1){
            return "A"
        }
        if(id == R.id.rbt2){
            return "B"
        }
        if(id == R.id.rbt3){
            return "C"
        }
        if(id == R.id.rbt4){
            return "D"
        }
        else return ""
    }

    fun create(position:Int, checkAns:Int):Fragment_Slide{
        val fragment = Fragment_Slide()
        val bundle = Bundle()
        bundle.putInt("position", position)
        bundle.putInt("checkans",checkAns)
        fragment.arguments = bundle
        return fragment
    }

    override fun onResume() {
        super.onResume()

        txtThuTu.setText("${numberFragment+1}/${dsCauHoi.size}")
    }
}