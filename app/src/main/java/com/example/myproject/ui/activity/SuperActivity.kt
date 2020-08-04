package com.example.myproject.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.myproject.R
import com.example.myproject.model.PinkProfile
import com.example.myproject.ui.adapter.CardPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class SuperActivity : AppCompatActivity() {

    private var viewPager : ViewPager? = null
    private var dotsIndicator : DotsIndicator? = null
    private var btnAccept : Button? = null
    private lateinit var newCardAdapter: CardPagerAdapter
    private lateinit var card: ArrayList<PinkProfile>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_super)

        viewPager = findViewById(R.id.viewPager)
        dotsIndicator = findViewById(R.id.dotsIndicator)
        btnAccept = findViewById(R.id.btnAccept)

        var pinkProfile = PinkProfile("Leejiun","","Korean")
        var pinkProfile2 = PinkProfile("Iu","","Korean")

        card = ArrayList()
        card.add(pinkProfile)
        card.add(pinkProfile2)
        card.add(pinkProfile)
        card.add(pinkProfile2)

        newCardAdapter = CardPagerAdapter(baseContext, card)
        viewPager?.adapter = newCardAdapter
        dotsIndicator?.setViewPager(viewPager!!)

        btnAccept?.setOnClickListener {

            val intent = Intent(this, PrimaryActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}