package com.example.myproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.model.Item
import com.example.myproject.ui.adapter.ItemAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlackFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var recyclerView: RecyclerView? = null
    private var items: ArrayList<Item>? = null
    private var listener: RecyclerView.OnScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView

        items = ArrayList()
        items!!.add(Item("Untitle1",""))
        items!!.add(Item("Untitle2",""))
        items!!.add(Item("Untitle3",""))
        items!!.add(Item("Untitle4",""))
        items!!.add(Item("Untitle5",""))
        items!!.add(Item("Untitle6","data:image/jpeg;base64,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"))
        items!!.add(Item("Untitle7",""))
        items!!.add(Item("Untitle9",""))
        items!!.add(Item("Untitle10",""))
        items!!.add(Item("Untitle11",""))
        items!!.add(Item("Untitle12",""))
        items!!.add(Item("Untitle13",""))
        items!!.add(Item("Untitle14",""))
        items!!.add(Item("Untitle15",""))
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = context?.let { ctx -> ItemAdapter(ctx, items!!) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_black, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlackFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        @JvmStatic
        fun newInstance() =
            BlackFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun setScrollListener(listener: RecyclerView.OnScrollListener){
        this.listener = listener
    }

    fun setData(items: ArrayList<Item>){
        this.items = items
    }

    fun  initView(){
//        recyclerView?.let {
//            it.layoutManager = LinearLayoutManager(context)
//            it.adapter = context?.let { ctx -> ItemAdapter(ctx, items!!) }
//        }
    }

}