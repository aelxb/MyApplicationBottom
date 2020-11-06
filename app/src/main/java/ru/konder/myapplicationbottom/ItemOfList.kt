package ru.konder.myapplicationbottom

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class ItemOfList (
    var imageSrc: Int,
    var title: String,
    var rate: String,
    var date: String ) : Parcelable
