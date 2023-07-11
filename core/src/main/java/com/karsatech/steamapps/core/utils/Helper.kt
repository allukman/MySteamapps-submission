package com.karsatech.steamapps.core.utils

import android.graphics.DashPathEffect
import android.graphics.Paint
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale

fun Int.withCurrencyFormat(): String {
    val number = this / 100
    val formattedNumber = NumberFormat.getNumberInstance(Locale("id", "ID")).format(number)
    return "Rp. $formattedNumber"
}

fun TextView.applyStrikeThrough() {
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    this.paint.pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
}