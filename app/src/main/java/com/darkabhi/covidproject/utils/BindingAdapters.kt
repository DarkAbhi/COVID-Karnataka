package com.darkabhi.covidproject.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.models.State
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.util.*

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

@BindingAdapter("dashboardImage")
fun setDashboardImage(imageView: ImageView, value: Int) {
    imageView.setImageResource(value)
}

@BindingAdapter("verifiedColor")
fun setVerificationChipColor(chip: Chip, value: Long) {
    if (value == 0L) chip.setChipBackgroundColorResource(R.color.verified_chip_bg) else if (value == 1L) chip.setChipBackgroundColorResource(R.color.not_verified_chip_bg) else chip.setChipBackgroundColorResource(R.color.to_be_verified_chip_bg)
}

@BindingAdapter("verifiedChipText")
fun setVerificationChipText(chip: Chip, value: Long) {
    if (value == 0L) chip.setText(R.string.verified) else if (value == 1L) chip.setText(R.string.not_verified) else chip.setText(R.string.to_be_verified)
}

@BindingAdapter("availableColor")
fun setAvailableChipColor(chip: Chip, value: Boolean) {
    if (value) chip.setChipBackgroundColorResource(R.color.available_chip_bg) else chip.setChipBackgroundColorResource(android.R.color.darker_gray)
}

@BindingAdapter("availableChipText")
fun setAvailableChipText(chip: Chip, value: Boolean) {
    if (value) chip.setText(R.string.available) else chip.setText(R.string.not_available)
}

@BindingAdapter("setLastUpdatedTime")
fun setLastUpdatedTimeText(tv: TextView, value: Date) {
    tv.text = "Last updated: ${SimpleDateFormat("dd MMMM hh:mm aa", Locale.US).format(value)}"
}
