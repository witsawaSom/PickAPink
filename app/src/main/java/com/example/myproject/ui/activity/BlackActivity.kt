package com.example.myproject.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.model.Item
import com.example.myproject.ui.fragment.BlackFragment
import com.example.myproject.ui.fragment.HomeFragment.Companion.newInstance
import com.example.myproject.ui.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_black.*


class BlackActivity : AppCompatActivity() {

    private var items: ArrayList<Item>? = null
    private var bottomNavigate: BottomNavigationView? = null

    var navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.home -> {
                        homePress()
                        return true
                    }
                    R.id.add -> {
                        profilePress()
                        return true
                    }
                    R.id.profile -> {
                        profilePress()
                        return true
                    }
                }
                return false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_black)
        bottomNavigate = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigate!!.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        bottomNavigate!!.selectedItemId = R.id.home

    }

    private fun profilePress(){
        loadFragment(ProfileFragment())
    }

    private fun homePress(){

        items = ArrayList()
        items!!.add(Item("Untitle1",""))
        items!!.add(Item("Untitle2",""))
        items!!.add(Item("Untitle3",""))
        items!!.add(Item("Untitle4",""))
        items!!.add(Item("Untitle5",""))
        items!!.add(Item("Untitle6",""))
        items!!.add(Item("Untitle7",""))
        items!!.add(Item("Untitle9",""))
        items!!.add(Item("Untitle10",""))
        items!!.add(Item("Untitle11",""))
        items!!.add(Item("Untitle12",""))
        items!!.add(Item("Untitle13",""))
        items!!.add(Item("Untitle14",""))
        items!!.add(Item("Untitle15",""))


        var fragment = BlackFragment()
        fragment.setData(items!!)
        fragment.setScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    bottom_navigation.visibility = View.GONE
                } else if (dy < 0) {

                    bottom_navigation.visibility = View.VISIBLE
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        loadFragment(fragment)

        fragment.initView()
    }

    private fun loadFragment(fragment :Fragment){
        val fm: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.layout, fragment)
        fragmentTransaction.commit()
    }
}