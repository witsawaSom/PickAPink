package com.example.myproject.ui

import android.content.Intent.getIntent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myproject.R
import kotlinx.android.synthetic.main.activity_pick.*

private const val ARG_PINK_PROFILE_URL = "url"

class PinkFragment : Fragment() {

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
        arguments?.let {
            url = it.getString(ARG_PINK_PROFILE_URL)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            PickFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PINK_PROFILE_URL, url)
                }
            }
    }

    fun setUrl(url: String){
        this.url = url
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_pink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageView = view.findViewById<ImageView>(R.id.imvProfile)

        context?.let {
            Glide.with(it)
                .load(url)
                .listener(object :RequestListener<Drawable>{
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .into(imageView)
        }
    }
}
