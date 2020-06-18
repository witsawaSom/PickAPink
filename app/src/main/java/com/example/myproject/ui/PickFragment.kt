package com.example.myproject.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import com.example.myproject.HolderSelectedListener
import com.example.myproject.PositionSelectedListener

import com.example.myproject.R
import com.example.myproject.model.PinkProfile
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_pick.*
import kotlinx.android.synthetic.main.fragment_pick.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PickFragment : Fragment(){

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var newCardAdapter: NewCardAdapter
    private lateinit var card: ArrayList<PinkProfile>
    private var selectedListener: HolderSelectedListener? = null
    private var carStackListener: CardStackListener? = null
    private lateinit var swipeAnimationSetting :SwipeAnimationSetting
    private var cardStackView :CardStackView? = null
    private lateinit var cardStackLayoutManager : CardStackLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun setListener( selectedListener: HolderSelectedListener){
        this.selectedListener = selectedListener
    }

    fun setCarStackListener( carStackListener: CardStackListener){
        this.carStackListener = carStackListener
    }


    fun onSwipe(){
        cardStackView?.swipe()
    }

    fun onSwipeLeft(){
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Left)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        cardStackLayoutManager.setSwipeAnimationSetting(setting)
        cardStackView?.swipe()
    }
    fun onSwipeRight(){
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        cardStackLayoutManager.setSwipeAnimationSetting(setting)
        cardStackView?.swipe()
    }

    fun onRewind(){
        cardStackView?.rewind()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_pick, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardStackView = view.cardStackView
        var pinkProfile = PinkProfile("Leejiun","https://i.pinimg.com/originals/62/f4/bf/62f4bf498655e01b734ab02e718a9b7f.jpg","Korean")
        var pinkProfile2 = PinkProfile("Iu","https://cache.gmo2.sistacafe.com/images/uploads/summary/image/23465/ca8571991acc5ae96f5539f005d58b7d.jpg","Korean")

        card = ArrayList()
        card.add(pinkProfile)
        card.add(pinkProfile2)
        card.add(pinkProfile)
        card.add(pinkProfile2)

        cardStackLayoutManager = CardStackLayoutManager(context,carStackListener)

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
            view.cardStackView.layoutManager = cardStackLayoutManager
            view.cardStackView.adapter = newCardAdapter
        }

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

    fun getAdapter() = newCardAdapter





}
