package ru.konder.myapplicationbottom

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class ItemOfList (
    var imageSrc: Int,
    var imageTitle: String,
    var imageDesc: String ) : Parcelable
