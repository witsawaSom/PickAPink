package com.example.myproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myproject.R
import com.example.myproject.model.DemoItem
import com.example.myproject.ui.activity.DemoAdapter
import com.example.myproject.ui.adapter.DefaultListAdapter
import com.example.myproject.util.DemoUtils
import com.felipecsl.asymmetricgridview.library.Utils
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listView: AsymmetricGridView? = null
    private var adapter: DemoAdapter? = null
    private var demoUtils = DemoUtils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.gridView) as AsymmetricGridView

        listView!!.setRequestedColumnWidth(Utils.dpToPx(context, 120f))

        adapter = DefaultListAdapter(context, demoUtils.moarItems(50)).also { adapter = it }
        listView!!.setRequestedColumnCount(3)
        listView!!.requestedHorizontalSpacing = Utils.dpToPx(context, 3f)
        getNewAdapter()?.let { listView!!.adapter = it }
        listView!!.isDebugging = true
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    private fun getNewAdapter(): AsymmetricGridViewAdapter<*>? {
        return AsymmetricGridViewAdapter<DemoItem>(context, listView, adapter)
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}