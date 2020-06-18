package com.example.myproject.ui

import android.os.Bundle
import android.os.Handler
import android.transition.Explode
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.myproject.HolderSelectedListener
import com.example.myproject.PositionSelectedListener
import com.example.myproject.R
import com.example.myproject.model.PinkProfile
import com.example.myproject.model.User
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.activity_pick.*
import kotlinx.android.synthetic.main.layout_profile_pink.view.*


class PickActivity : AppCompatActivity()  ,HolderSelectedListener, CardStackListener {

    private lateinit var layouts: IntArray
    private lateinit var al: ArrayList<String>
    private var mainFragment : Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pick)

        var user = User("witsawa","https://i.pinimg.com/originals/62/f4/bf/62f4bf498655e01b734ab02e718a9b7f.jpg","witsawa","somkane");

        Glide.with(baseContext)
            .load(user.url)
            .circleCrop()
            .into(imvProfile)

        initEvent()

        mainFragment = PickFragment()
        (mainFragment as PickFragment).setListener(this)
        (mainFragment as PickFragment).setCarStackListener(this)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, mainFragment as PickFragment)
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
//            if(mainFragment is PinkFragment ){
//                supportFragmentManager.popBackStack ("profile", FragmentManager.POP_BACK_STACK_INCLUSIVE)
//            }
            if(mainFragment is PickFragment ){
                (mainFragment as PickFragment).onSwipeLeft()
            }


        }

        fabFavorite.setOnClickListener {
//            if(mainFragment is PinkFragment ){
//                supportFragmentManager.popBackStack ("profile", FragmentManager.POP_BACK_STACK_INCLUSIVE)
//            }
            if(mainFragment is PickFragment ){
                (mainFragment as PickFragment).onRewind()
            }


        }

        fabLove.setOnClickListener {

//            if(mainFragment is PinkFragment ){
//                supportFragmentManager.popBackStack ("profile", FragmentManager.POP_BACK_STACK_INCLUSIVE)
//            }

            if(mainFragment is PickFragment ){
                (mainFragment as PickFragment).onSwipeRight()
            }


        }


        fabBack.setOnClickListener {
            fabBack.visibility = View.GONE
            Toast.makeText(applicationContext, "back",Toast.LENGTH_SHORT).show()
        }
    }



    override fun onHolderPositionSelected(pinkProfile: PinkProfile, viewHolder: NewCardAdapter.ViewHolder) {
        mainFragment = PinkFragment()
        (mainFragment as PinkFragment).setUrl(pinkProfile.url)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, mainFragment as PinkFragment)
            .addSharedElement(viewHolder.itemView.imageView, ViewCompat.getTransitionName(viewHolder.itemView.imageView)!!)
            .addToBackStack("profile")
            .commit()
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        if (direction != null) {
            when(direction.ordinal){
                0 -> {
                    fabClose.requestFocusFromTouch()
                    Handler().postDelayed({
                        fabClose.clearFocus()
                    }, 1000)
                }
                1 -> {
                    fabLove.requestFocusFromTouch()
                    Handler().postDelayed({
                        fabLove.clearFocus()
                    }, 1000)
                }
            }
        }
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {
    }
}
