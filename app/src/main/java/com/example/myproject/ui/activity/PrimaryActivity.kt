package com.example.myproject.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myproject.R
import com.example.myproject.ui.fragment.HomeFragment
import com.fxn.BubbleTabBar
import com.fxn.OnBubbleClickListener

class PrimaryActivity : AppCompatActivity(){
    private var bubbleTabBar : BubbleTabBar? = null

    var callback = object :Callback{
        override fun someEvent(fragment: Fragment?) {
            fragment?.let {
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.contentContainer, it)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_primary)

        bubbleTabBar = findViewById(R.id.bubbleTabBar)

        bubbleTabBar?.addBubbleListener(object : OnBubbleClickListener{
            override fun onBubbleClick(id: Int) {
                when (id) {
                    R.id.home -> replaceFragment(HomeFragment())
                    R.id.car -> replaceFragment(HomeFragment())
                    R.id.location -> replaceFragment(HomeFragment())
                    R.id.more -> replaceFragment(HomeFragment())
                }
            }
        })

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment?){
        fragment?.let {
            supportFragmentManager.beginTransaction()
                .add(R.id.contentContainer, it)
                .commit()
        }
    }

    interface Callback {
        fun someEvent(fragment: Fragment?)
    }

}