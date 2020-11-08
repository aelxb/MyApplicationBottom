package ru.konder.myapplicationbottom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=ab9d01d6538c94cad37d1af4c042140d"
    var okHttpClient: OkHttpClient = OkHttpClient()
    var titles: String=""
    var dates: String=""
    var rates: String=""
    var patches: String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val request:Request = Request.Builder().url(URL).build()
        okHttpClient.newCall(request).enqueue(object : Callback{
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
                runOnUiThread{
                    val homeFragment = BlankFragment()
                    homeFragment.arguments= bundleOf("KEY1" to titles, "KEY2" to dates, "KEY3" to rates, "KEY4" to patches)
                    makeCurrentFragment(homeFragment)
                    bottom_nav.setOnNavigationItemSelectedListener {
                        when (it.itemId){
                            R.id.ic_home->makeCurrentFragment(homeFragment)
                        }
                        true
                    }
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
