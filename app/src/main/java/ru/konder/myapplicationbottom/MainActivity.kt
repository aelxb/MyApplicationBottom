package ru.konder.myapplicationbottom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.collections.*

class MainActivity : AppCompatActivity() {
    var URL = "${API_Url.BSIC_URL}top_rated${BuildConfig.API_KEY}"
    var okHttpClient: OkHttpClient = OkHttpClient()
    var titles = arrayListOf<String>()
    var dates=arrayListOf<String>()
    var rates=arrayListOf<String>()
    var patches=arrayListOf<String>()
    var overviews= arrayListOf<String>()
    val homeFragment = BlankFragment()
    val awaitedFragment = BlankFragment2()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runOnUiThread{
            progressBar.visibility = View.VISIBLE
        }
        var request:Request = Request.Builder().url(URL).build()
        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error",e.toString())
            }
            override fun onResponse(call: Call, response: Response) {
                val joke = (JSONObject(response!!.body()!!.string()).getJSONArray("results"))
                for (i in 0 until joke.length()) {
                    val item = joke.getJSONObject(i)
                    val title = item.get("title").toString()
                    titles.add(title)
                    val date = item.get("release_date").toString()
                    dates.add(date)
                    val rate = item.get("vote_average").toString()
                    rates.add(rate)
                    val patch = item.get("poster_path").toString()
                    patches.add(patch)
                    val overview = item.get("overview").toString()
                    overviews.add(overview)
                }
                runOnUiThread{
                    homeFragment.arguments= bundleOf("KEY1" to titles, "KEY2" to dates, "KEY3" to rates, "KEY4" to patches, "KEY5" to overviews)
                }
            }
        })
        var titlesForUp = arrayListOf<String>()
        var datesForUp=arrayListOf<String>()
        var ratesForUp=arrayListOf<String>()
        var patchesForUp=arrayListOf<String>()
        var overviewsForUp=arrayListOf<String>()
        URL = "${API_Url.BSIC_URL}upcoming${BuildConfig.API_KEY}"
        request = Request.Builder().url(URL).build()
        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error",e.toString())
            }
            override fun onResponse(call: Call, response: Response) {
                val joke = (JSONObject(response!!.body()!!.string()).getJSONArray("results"))
                for (i in 0 until joke.length()) {
                    val item = joke.getJSONObject(i)
                    val title = item.get("title").toString()
                    titlesForUp.add(title)
                    val date = item.get("release_date").toString()
                    datesForUp.add(date)
                    val rate = item.get("vote_average").toString()
                    ratesForUp.add(rate)
                    val patch = item.get("poster_path").toString()
                    patchesForUp.add(patch)
                    val overview = item.get("overview").toString()
                    overviewsForUp.add(overview)
                }
                runOnUiThread{
                    awaitedFragment.arguments= bundleOf("KEY1" to titlesForUp, "KEY2" to datesForUp, "KEY3" to ratesForUp, "KEY4" to patchesForUp,"KEY5" to overviewsForUp)
                    progressBar.visibility = View.GONE
                    makeCurrentFragment(homeFragment)
                    bottom_nav.setOnNavigationItemSelectedListener {
                        when (it.itemId){
                            R.id.ic_home->makeCurrentFragment(homeFragment)
                            R.id.ic_other->makeCurrentFragment(awaitedFragment)
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
