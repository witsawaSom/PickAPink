package com.example.myproject.ui

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myproject.R
import com.example.myproject.model.PinkProfile
import com.example.myproject.model.User
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_pick.*


class PickActivity : AppCompatActivity(), CardStackListener  {

    private lateinit var layouts: IntArray
    private lateinit var al: ArrayList<String>
    private lateinit var newCardAdapter: NewCardAdapter
    private lateinit var card: ArrayList<PinkProfile>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pick)

        var user = User("witsawa","https://i.pinimg.com/originals/62/f4/bf/62f4bf498655e01b734ab02e718a9b7f.jpg","witsawa","somkane");

        Glide.with(baseContext)
            .load(user.url)
            .circleCrop()
            .into(imvProfile)

        // new card
        var pinkProfile = PinkProfile("Leejiun","https://i.pinimg.com/originals/62/f4/bf/62f4bf498655e01b734ab02e718a9b7f.jpg","Korean")
        var pinkProfile2 = PinkProfile("Iu","https://cache.gmo2.sistacafe.com/images/uploads/summary/image/23465/ca8571991acc5ae96f5539f005d58b7d.jpg","Korean")
        card = ArrayList()
        card.add(pinkProfile)
        card.add(pinkProfile2)
        card.add(pinkProfile)
        card.add(pinkProfile2)

        var cardStackLayoutManager = CardStackLayoutManager(baseContext,this)
        newCardAdapter = NewCardAdapter(baseContext,card)
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        cardStackLayoutManager.setSwipeAnimationSetting(setting)
        cardStackLayoutManager.setStackFrom(StackFrom.Top)
        cardStackLayoutManager.setVisibleCount(2)
        cardStackLayoutManager.setMaxDegree(60.0f)
        cardStackLayoutManager.setTranslationInterval(2.0f)
        cardStackLayoutManager.setDirections(Direction.HORIZONTAL)
        cardStackView.layoutManager = cardStackLayoutManager
        cardStackView.adapter = newCardAdapter

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
        fabLove.setOnClickListener {
            Toast.makeText(applicationContext, "like",Toast.LENGTH_SHORT).show()
        }


    }


    override fun onCardDisappeared(view: View?, position: Int) {
//        Toast.makeText(applicationContext, "Disappear",Toast.LENGTH_SHORT).show()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        if (direction != null) {
//            Toast.makeText(applicationContext,"Drag"+ direction?.name,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCardSwiped(direction: Direction?) {
        if (direction != null) {
            when(direction?.ordinal){
                 0 -> {
                     fabClose.requestFocusFromTouch()
                     fabClose.callOnClick()
                     Handler().postDelayed({
                         fabClose.clearFocus()
                     }, 1000)
                 }
                 1 -> {
                     fabLove.requestFocusFromTouch()
                     fabLove.callOnClick()
                     Handler().postDelayed({
                         fabLove.clearFocus()
                     }, 1000)
                 }
            }
        }
    }

    override fun onCardCanceled() {
//        Toast.makeText(applicationContext, "Cancel",Toast.LENGTH_SHORT).show()
    }

    override fun onCardAppeared(view: View?, position: Int) {
//        Toast.makeText(applicationContext, "Appeared",Toast.LENGTH_SHORT).show()

    }

    override fun onCardRewound() {
//        Toast.makeText(applicationContext, "Rewound",Toast.LENGTH_SHORT).show()

    }


}
