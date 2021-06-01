package com.darkabhi.covidproject.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.darkabhi.covidproject.R
import com.google.android.material.chip.Chip
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/27/2021.
 */

@BindingAdapter("imageUrl")
fun setPostImage(postImageView: ImageView?, url: String?) {
    postImageView?.load(url) {
        crossfade(true)
    }
}

@BindingAdapter("dashboardImage")
fun setDashboardImage(imageView: ImageView, value: Int) {
    imageView.setImageResource(value)
}

@BindingAdapter("verifiedColor")
fun setVerificationChipColor(chip: Chip, value: String) {
    when (value) {
        "0" -> chip.setChipBackgroundColorResource(R.color.verified_chip_bg)
        "1" -> chip.setChipBackgroundColorResource(
            R.color.not_verified_chip_bg
        )
        else -> chip.setChipBackgroundColorResource(R.color.to_be_verified_chip_bg)
    }
}

@BindingAdapter("verifiedChipText")
fun setVerificationChipText(chip: Chip, value: String) {
    when (value) {
        "0" -> chip.setText(R.string.verified)
        "1" -> chip.setText(R.string.not_verified)
        else -> chip.setText(
            R.string.to_be_verified
        )
    }
}

@BindingAdapter("availableColor")
fun setAvailableChipColor(chip: Chip, value: Boolean) {
    if (value) chip.setChipBackgroundColorResource(R.color.available_chip_bg) else chip.setChipBackgroundColorResource(
        android.R.color.darker_gray
    )
}

@BindingAdapter("availableChipText")
fun setAvailableChipText(chip: Chip, value: Boolean) {
    if (value) chip.setText(R.string.available) else chip.setText(R.string.not_available)
}

@BindingAdapter("setLastUpdatedTime")
fun setLastUpdatedTimeText(tv: TextView, value: String) {
    val format: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val date: Date = format.parse(value)!!
    tv.text = tv.resources.getString(
        R.string.last_updated_text,
        SimpleDateFormat("dd MMMM hh:mm aa", Locale.US).format(date)
    )
}
