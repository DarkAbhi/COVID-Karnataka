package com.darkabhi.covidproject.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Abhishek AN on 3/13/2021.
 */

// Show positive and negative options alert dialog
fun Context.showDoubleOptionAlertDialog(
        positiveButtonLabel: String,
        negativeButtonLabel: String,
        title: String,
        message: String,
        actionOnPositiveButton: () -> Unit
) {
    val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(positiveButtonLabel) { dialog, _ ->
                dialog.cancel()
                actionOnPositiveButton()
            }.setNegativeButton(negativeButtonLabel) { dialog, _ ->
                dialog.cancel()
            }
    val alert = builder.create()
    alert.show()
}

// Show alert dialog
fun Context.showAlertDialog(
        positiveButtonLabel: String,
        title: String,
        message: String,
        actionOnPositiveButton: () -> Unit
) {
    val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(positiveButtonLabel) { dialog, _ ->
                dialog.cancel()
                actionOnPositiveButton()
            }
    val alert = builder.create()
    alert.show()
}

// Toast extensions
fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

// SnackBar Extensions
fun View.showShortSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.showLongSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun View.snackBarWithAction(
        message: String, actionLabel: String,
        block: () -> Unit
) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
            .setAction(actionLabel) {
                block()
            }
}