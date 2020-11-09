package ru.konder.myapplicationbottom

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.UiThread
import java.io.InputStream
import java.net.URL
class DownloadImageTask(var bmImage: ImageView, val progressBar: ProgressBar) : AsyncTask<String?, Void?, Bitmap?>() {
    override fun doInBackground(vararg params: String?): Bitmap? {
        val urldisplay = params[0]
        var mIcon11: Bitmap? = null
        try {
            val `in`: InputStream = URL(urldisplay).openStream()
            mIcon11 = BitmapFactory.decodeStream(`in`)
        } catch (e: Exception) {
            Log.e("Ошибка", e.message)
            e.printStackTrace()
        }
        return mIcon11
    }
    override fun onPostExecute(result: Bitmap?) {
        bmImage.setImageBitmap(result)
    }
}