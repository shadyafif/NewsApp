package com.example.newsapp.Utlies

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.newsapp.R
import com.squareup.picasso.Picasso

object Helper {
    fun Replace(fragment: Fragment?, id: Int, fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.replace(id, fragment!!)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun Add(fragment: Fragment?, id: Int, fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.replace(id, fragment!!)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun ImageView.loadImage(url: String) {
        Picasso.get()
            .load(url)
            .resize(400, 400)
            .error(R.drawable.nophoto)
            .placeholder(R.drawable.square)
            .into(this)
    }

}