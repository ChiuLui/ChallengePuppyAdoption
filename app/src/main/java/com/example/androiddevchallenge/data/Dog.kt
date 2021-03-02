package com.example.androiddevchallenge.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

/**
 * Dog$
 * @author    神经大条蕾弟
 * @date      2021/03/01 23:10
 */
@Parcelize
data class Dog (
    val name: String,
    val breed: String,
    val age: String,
    val gender: String,
    val address: String,
    @DrawableRes val photo: Int,
) : Parcelable
