package com.example.myproject.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myproject.PositionSelectedListener
import com.example.myproject.R
import com.example.myproject.model.User
import kotlinx.android.synthetic.main.activity_pick.*


class PickActivity : AppCompatActivity()  ,PositionSelectedListener{

    private lateinit var layouts: IntArray
    private lateinit var al: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pick)

        var user = User("witsawa","https://i.pinimg.com/originals/62/f4/bf/62f4bf498655e01b734ab02e718a9b7f.jpg","witsawa","somkane");

        Glide.with(baseContext)
            .load(user.url)
            .circleCrop()
            .into(imvProfile)

        initEvent()

        var mainFragment = PickFragment()
        mainFragment.setListener(this)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, mainFragment)
            .commit()

    }

    private fun initEvent() {
        imvProfile.setOnClickListener {
            Toast.makeText(applicationContext, "Profile",Toast.LENGTH_SHORT).show()
        }

        imvChat.setOnClickListener {
            Toast.makeText(applicationContext, "Chat",Toast.LENGTH_SHORT).show()
        }

        fabClose.setOnClickListener {
            Toast.makeText(applicationContext, "Dislike",Toast.LENGTH_SHORT).show()
        }

        fabFavorite.setOnClickListener {
            Toast.makeText(applicationContext, "Star",Toast.LENGTH_SHORT).show()
        }

        fabLove.setOnClickListener {
            Toast.makeText(applicationContext, "like",Toast.LENGTH_SHORT).show()
        }

        fabBack.visibility = View.GONE

        fabBack.setOnClickListener {
            fabBack.visibility = View.GONE
            frameLayout.visibility = View.GONE
            Toast.makeText(applicationContext, "back",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPositionSelected(position: Int) {
        frameLayout.visibility = View.VISIBLE
        var mainFragment = PinkFragment()
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, mainFragment)
            .addToBackStack(null)
            .commit()
    }
}
