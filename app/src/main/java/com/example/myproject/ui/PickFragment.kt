package com.example.myproject.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import com.example.myproject.PositionSelectedListener

import com.example.myproject.R
import com.example.myproject.model.PinkProfile
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_pick.*
import kotlinx.android.synthetic.main.fragment_pick.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PickFragment : Fragment(), CardStackListener{

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var newCardAdapter: NewCardAdapter
    private lateinit var card: ArrayList<PinkProfile>
    private var selectedListener: PositionSelectedListener? = null
    private lateinit var swipeAnimationSetting :SwipeAnimationSetting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun setListener( selectedListener: PositionSelectedListener){
        this.selectedListener = selectedListener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v = inflater.inflate(R.layout.fragment_pick, container, false)

        var pinkProfile = PinkProfile("Leejiun","https://i.pinimg.com/originals/62/f4/bf/62f4bf498655e01b734ab02e718a9b7f.jpg","Korean")
        var pinkProfile2 = PinkProfile("Iu","https://cache.gmo2.sistacafe.com/images/uploads/summary/image/23465/ca8571991acc5ae96f5539f005d58b7d.jpg","Korean")

        card = ArrayList()
        card.add(pinkProfile)
        card.add(pinkProfile2)
        card.add(pinkProfile)
        card.add(pinkProfile2)

        var cardStackLayoutManager = CardStackLayoutManager(context,this)

        swipeAnimationSetting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()

        cardStackLayoutManager.setSwipeAnimationSetting(swipeAnimationSetting)
        cardStackLayoutManager.setStackFrom(StackFrom.Top)
        cardStackLayoutManager.setVisibleCount(2)
        cardStackLayoutManager.setMaxDegree(60.0f)
        cardStackLayoutManager.setTranslationInterval(2.0f)
        cardStackLayoutManager.setDirections(Direction.HORIZONTAL)

        selectedListener?.let {
            newCardAdapter = NewCardAdapter(context!!,card, it)
            v.cardStackView.layoutManager = cardStackLayoutManager
            v.cardStackView.adapter = newCardAdapter
        }


        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PickFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    override fun onCardDisappeared(view: View?, position: Int) {
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
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
    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {
    }

}
