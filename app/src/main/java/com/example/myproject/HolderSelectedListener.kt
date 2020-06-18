package com.example.myproject

import com.example.myproject.model.PinkProfile
import com.example.myproject.ui.NewCardAdapter

interface HolderSelectedListener {
    fun onHolderPositionSelected(pinkProfile : PinkProfile, viewHolder: NewCardAdapter.ViewHolder)
}