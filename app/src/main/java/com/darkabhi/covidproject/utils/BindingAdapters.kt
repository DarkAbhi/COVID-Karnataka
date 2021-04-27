package com.darkabhi.covidproject.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.darkabhi.covidproject.models.State

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/27/2021.
 */

@BindingAdapter("imageUrl")
fun setPostImage(postImageView: ImageView?, url: String?) {
    postImageView?.load(url) {
        crossfade(true)
    }
}

@BindingAdapter("hideOnLoading")
fun ViewGroup.hideOnLoading(responseState: State<*>) {
    visibility = if (responseState is State.Loading)
        View.GONE
    else
        View.VISIBLE
}

@BindingAdapter("showOnLoading")
fun ProgressBar.showOnLoading(responseState: State<*>) {
    visibility = if (responseState is State.Loading)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("showOnError")
fun TextView.showError(responseState: State<*>) {
    visibility = if (responseState is State.Failed)
        View.VISIBLE
    else
        View.GONE
    text = (responseState as State.Failed).message
}
