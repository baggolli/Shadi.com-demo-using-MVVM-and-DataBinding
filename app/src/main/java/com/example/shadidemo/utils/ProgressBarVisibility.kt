package com.example.shadidemo.utils

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

fun loadProgressBar(isLoading: Boolean, progressBar: ProgressBar, recyclerView: RecyclerView) {
    if (isLoading) {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    } else {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}