package com.example.myproject.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myproject.R
import com.example.myproject.model.SliderItem
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderView


class MyProfileFragment : Fragment() {

    var sliderView: SliderView? = null
    private var adapter: SliderAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sliderView = view.findViewById(R.id.imageSlider)

        adapter = context?.let { SliderAdapter(it) }

        renewItems()

        sliderView?.run {
            adapter?.let { setSliderAdapter(it) }
            setOnIndicatorClickListener {
                Log.i(
                    "GGG",
                    "onIndicatorClicked: " + sliderView!!.currentPagePosition
                )
            }
        }


//        addNewItem()
    }

    fun renewItems() {
        val sliderItemList: MutableList<SliderItem> = ArrayList()
        //dummy data
        for (i in 0..4) {
            val sliderItem = SliderItem()
            sliderItem.setDescription("Slider Item $i")
            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
            } else {
                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
            }
            sliderItemList.add(sliderItem)
        }
        adapter!!.renewItems(sliderItemList)
    }

    fun removeLastItem() {
        if (adapter!!.count - 1 >= 0) adapter!!.deleteItem(adapter!!.count - 1)
    }

    fun addNewItem() {
        val sliderItem = SliderItem()
        sliderItem.setDescription("Slider Item Added Manually")
        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        adapter!!.addItem(sliderItem)
    }

}
