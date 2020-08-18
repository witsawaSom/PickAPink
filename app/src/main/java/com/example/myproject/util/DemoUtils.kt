package com.example.myproject.util

import com.example.myproject.model.DemoItem


internal class DemoUtils {
    var currentOffset = 0
    fun moarItems(qty: Int): List<DemoItem> {
        val items: MutableList<DemoItem> = ArrayList()
        for (i in 0 until qty) {
            val colSpan = if (Math.random() < 0.2f) 2 else 1
            // Swap the next 2 lines to have items with variable
            // column/row span.
            // int rowSpan = Math.random() < 0.2f ? 2 : 1;
            val item = DemoItem(colSpan, colSpan, currentOffset + i)
            items.add(item)
        }
        currentOffset += qty
        return items
    }
}