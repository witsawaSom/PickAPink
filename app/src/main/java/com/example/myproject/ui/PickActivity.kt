package com.example.myproject.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.transition.Explode
import android.transition.Visibility
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.myproject.HolderSelectedListener
import com.example.myproject.PositionSelectedListener
import com.example.myproject.R
import com.example.myproject.model.PinkProfile
import com.example.myproject.model.User
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.activity_pick.*
import kotlinx.android.synthetic.main.layout_profile_pink.view.*
import java.io.ByteArrayOutputStream


class PickActivity : AppCompatActivity(), HolderSelectedListener, CardStackListener {

    private lateinit var layouts: IntArray
    private lateinit var al: ArrayList<String>
    private var mainFragment: Fragment? = null
    private lateinit var storage: FirebaseStorage
    private var storageReference: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pick)

        storage = FirebaseStorage.getInstance("gs://pick-pink.appspot.com")

        var user = User(
            "witsawa",
            "https://i.pinimg.com/originals/62/f4/bf/62f4bf498655e01b734ab02e718a9b7f.jpg",
            "witsawa",
            "somkane"
        );

        Glide.with(baseContext)
            .load(user.url)
            .circleCrop()
            .into(imvProfile)

        initEvent()

        mainFragment = PickFragment()
        (mainFragment as PickFragment).setListener(this)
        (mainFragment as PickFragment).setCarStackListener(this)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, mainFragment as PickFragment)
            .commit()

        supportFragmentManager.removeOnBackStackChangedListener {
            visibleBottom()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            visibleBottom()
        }

    }

    private fun createStorageRef() {
        if (this.storageReference == null) {
            storageReference = storage.reference
        }
    }

    private fun uploadImage(){
        createStorageRef()
        val uploadRef = storageReference!!.child("test.jpg")
        imvProfile.isDrawingCacheEnabled = true
        imvProfile.buildDrawingCache()
        val bitmap = (imvProfile.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = uploadRef.putBytes(data)
        uploadTask.addOnFailureListener {
            Log.d("Upload","onFail")
        }.addOnSuccessListener {
            Log.d("Upload","onSuccess")
        }
    }

    private fun visibleBottom() {
        var backcount = supportFragmentManager.backStackEntryCount
        if (backcount != 0) {
            for (fragment in supportFragmentManager.fragments) {
                if (fragment.isVisible) {
                    if (fragment is MyProfileFragment) {
                        hideBottom()
                    } else {
                        showBottom()
                    }
                }
            }
        } else {
            showBottom()
        }
    }

    private fun showBottom() {
        layoutBottom.visibility = View.VISIBLE
    }

    private fun hideBottom() {
        layoutBottom.visibility = View.GONE
    }


    private fun initEvent() {
        imvProfile.setOnClickListener {
            mainFragment = MyProfileFragment()
            supportFragmentManager.beginTransaction().add(R.id.frameLayout, mainFragment as MyProfileFragment)
                .setReorderingAllowed(true)
                .addToBackStack("my_profile")
                .commit()
        }

        imvChat.setOnClickListener {
            uploadImage()
            Toast.makeText(applicationContext, "Chat", Toast.LENGTH_SHORT).show()
        }

        fabClose.setOnClickListener {
            for (fragment in supportFragmentManager.fragments) {
                if (fragment.isVisible && fragment is PinkFragment) {
                    supportFragmentManager.popBackStack()
                    break
                }

                if (fragment.isVisible && fragment is PickFragment) {
                    (fragment as PickFragment).onSwipeLeft()
                }
            }
        }

        fabFavorite.setOnClickListener {
            for (fragment in supportFragmentManager.fragments) {
                if (fragment.isVisible && fragment is PinkFragment) {
                    supportFragmentManager.popBackStack()
                    break
                }

                if (fragment.isVisible && fragment is PickFragment) {
                    fragment.onRewind()
                }
            }
        }

        fabLove.setOnClickListener {
            for (fragment in supportFragmentManager.fragments) {
                if (fragment.isVisible && fragment is PinkFragment) {
                    supportFragmentManager.popBackStack()
                    break
                }

                if (fragment.isVisible && fragment is PickFragment) {
                    fragment.onSwipeRight()
                }
            }
        }


        fabBack.setOnClickListener {
            fabBack.visibility = View.GONE
            Toast.makeText(applicationContext, "back", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onHolderPositionSelected(pinkProfile: PinkProfile, viewHolder: NewCardAdapter.ViewHolder) {
        mainFragment = PinkFragment()
        (mainFragment as PinkFragment).setUrl(pinkProfile.url)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, mainFragment as PinkFragment)
            .setReorderingAllowed(true)
            .addSharedElement(
                viewHolder.itemView.imageView,
                ViewCompat.getTransitionName(viewHolder.itemView.imageView)!!
            )
            .addToBackStack("profile")
            .commit()
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        if (direction != null) {
            when (direction.ordinal) {
                0 -> {
                    fabClose.requestFocusFromTouch()
                    Handler().postDelayed({
                        fabClose.clearFocus()
                    }, 800)
                }
                1 -> {
                    fabLove.requestFocusFromTouch()
                    Handler().postDelayed({
                        fabLove.clearFocus()
                    }, 800)
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
