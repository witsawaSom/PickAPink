package com.example.myproject.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myproject.R


class StatTextView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.StatTextView_Styleable, 0, 0
        )
        val text = a.getString(R.styleable.StatTextView_Styleable_text)
        val title = a.getString(R.styleable.StatTextView_Styleable_title)
        a.recycle()

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_stat_textview,this)
        val tvValue = view.findViewById<TextView>(R.id.tvValue)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)

        tvValue.text = text
        tvTitle.text = title
    }

}