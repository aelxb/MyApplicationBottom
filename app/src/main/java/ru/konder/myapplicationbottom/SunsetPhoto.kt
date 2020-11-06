package ru.konder.myapplicationbottom

import android.media.Image
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class SunsetPhoto (
    var filmName: String,
    var imageTitleUrl: String,
    var filmRating: String,
    var filmDate: String) : Parcelable
