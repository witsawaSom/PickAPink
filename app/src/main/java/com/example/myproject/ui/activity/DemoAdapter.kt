package com.example.myproject.ui.activity

import android.widget.ListAdapter
import com.example.myproject.model.DemoItem


interface DemoAdapter : ListAdapter {
    fun appendItems(newItems: List<DemoItem?>?)
    fun setItems(moreItems: List<DemoItem?>?)
}