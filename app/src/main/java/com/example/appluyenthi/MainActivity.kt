package com.example.appluyenthi

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.appluyenthi.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity(){
    var FRAGMENT_MONHOC =1
    var FRAGMENT_DIEM = 2
    var FRAGMENT_TAIKHOAN=3
    var FRAGMENT_PHANQUYEN = 4

    var CURRENT_FRAGMENT = FRAGMENT_MONHOC

    lateinit var userName:String
    lateinit var email:String
    lateinit var password:String
    var phanQuyen:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.getStringExtra("username")!!
        email = intent.getStringExtra("email")!!
        password = intent.getStringExtra("password")!!
        phanQuyen = intent.getIntExtra("phanquyen",1)

        replaceFragment(Fragment_Monhoc())

        binding.navMain.menu.findItem(R.id.menuMonHoc).setChecked(true)

        actionToolBar()
        actionNav()

    }

    private fun actionToolBar() {
        setSupportActionBar(binding.toolBarMain)
        binding.toolBarMain.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size)
        binding.toolBarMain.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun replaceFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().apply {
            val bundle = Bundle()
            bundle.putString("username", userName)
            bundle.putString("email", email)
            bundle.putString("password", password)
            bundle.putInt("phanquyen",phanQuyen)
            fragment.arguments = bundle
            replace(R.id.frameLayout, fragment)
            commit()
        }

    }

    private fun actionNav() {
        //header nav

        val view = binding.navMain.getHeaderView(0)
        val user = view.findViewById<TextView>(R.id.txtUser)
        val em = view.findViewById<TextView>(R.id.txtEmail2)
        user.setText(userName)
        em.setText(email)

        //menu
        binding.navMain.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menuMonHoc ->{
                    if(CURRENT_FRAGMENT!=FRAGMENT_MONHOC){
                        replaceFragment(Fragment_Monhoc())
                        CURRENT_FRAGMENT=FRAGMENT_MONHOC
                        binding.navMain.menu.findItem(R.id.menuMonHoc).setChecked(true)
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.menuDiem ->{
                    if(CURRENT_FRAGMENT != FRAGMENT_DIEM){
                        replaceFragment(Fragment_Diem())
                        CURRENT_FRAGMENT=FRAGMENT_DIEM
                        binding.navMain.menu.findItem(R.id.menuDiem).setChecked(true)
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.menuTaiKhoan -> {
                    if(CURRENT_FRAGMENT!=FRAGMENT_TAIKHOAN){
                        replaceFragment(MyAccount())
                        CURRENT_FRAGMENT=FRAGMENT_TAIKHOAN
                        binding.navMain.menu.findItem(R.id.menuTaiKhoan).setChecked(true)
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.menuDangXuat ->{
                    val dialog = AlertDialog.Builder(this)
                    dialog.apply {
                        setTitle("Confirm window")
                        setMessage("Bạn có muốn đăng xuất?")
                        setNegativeButton("No"){diologInterface:DialogInterface,i: Int ->
                            diologInterface.dismiss()
                        }
                        setPositiveButton("Yes"){diologInterface:DialogInterface,i: Int ->
                            val intent = Intent(this@MainActivity, DangNhap::class.java)
                            finish()
                            startActivity(intent)
                        }
                        setCancelable(false)
                    }
                    dialog.show()
                    true
                }
                R.id.menuAdmin -> {
                    if(CURRENT_FRAGMENT != FRAGMENT_PHANQUYEN){
                        if(phanQuyen == 0){
                            replaceFragment(Fragment_Admin())
                            CURRENT_FRAGMENT = FRAGMENT_PHANQUYEN
                            binding.navMain.menu.findItem(R.id.menuAdmin).setChecked(true)
                            binding.drawerLayout.closeDrawer(GravityCompat.START)
                        }else{
                            Toast.makeText(this, "Bạn không phải là admin!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    true
                }
                else -> false
            }
        }
    }

}