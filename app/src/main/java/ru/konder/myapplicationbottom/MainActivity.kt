package ru.konder.myapplicationbottom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var sunsetPhoto:Array<SunsetPhoto>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment = BlankFragment()
        homeFragment.arguments = bundleOf("key" to sunsetPhoto)
        makeCurrentFragment(homeFragment)
        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home->makeCurrentFragment(homeFragment)
            }
            true
        }
        val url:String = "https://api.themoviedb.org/3/movie/top_rated?api_key=ab9d01d6538c94cad37d1af4c042140d"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonObject = (JSONObject(response!!.body()!!.string()).getJSONObject("results"))
                val jsonArray = jsonObject.optJSONArray("title")
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val title = jsonObject.optString("title").toString()
                    val date = jsonObject.optString("release_date").toString()
                    val rating = jsonObject.optString("vote_average").toString()
                    val imgUrl = jsonObject.optString("poster_path").toString()
                    val suns=SunsetPhoto(title,imgUrl,rating,date)
                    sunsetPhoto+=suns
                }
            }
        })
    }
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.wrap,fragment)
            commit()
        }
}
