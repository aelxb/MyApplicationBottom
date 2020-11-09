package ru.konder.myapplicationbottom

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class ItemOfList (
    var imageSrc: String,
    var title: String,
    var rate: String,
    var date: String,
    var description:String) : Parcelable
