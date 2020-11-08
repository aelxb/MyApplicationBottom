package ru.konder.myapplicationbottom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class BlankFragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_blank2, container, false)
        return rootView
    }
}
interface MyInterface {
    fun getapi() {
        var URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=ab9d01d6538c94cad37d1af4c042140d"
        var okHttpClient: OkHttpClient = OkHttpClient()
        var titles: String=""
        var dates: String=""
        var rates: String=""
        var patches: String=""
            val request: Request = Request.Builder().url(URL).build()
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("Error",e.toString())
                }
                override fun onResponse(call: Call, response: Response) {
                    val joke = (JSONObject(response!!.body()!!.string()).getJSONArray("results"))
                    for (i in 0 until joke.length()) {
                        val item = joke.getJSONObject(i)
                        val title = item.get("title").toString()
                        titles+="_"
                        titles+= title
                        val date = item.get("release_date").toString()
                        dates+="_"
                        dates+= date
                        val rate = item.get("vote_average").toString()
                        rates+="_"
                        rates+= rate
                        val patch = item.get("poster_path").toString()
                        patches+="_"
                        patches+= patch
                    }
                    //runOnUiThread{
                    //
                    //}
                }
            })

    }
}