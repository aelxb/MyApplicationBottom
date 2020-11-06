package ru.konder.myapplicationbottom


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class BlankFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rates = arguments?.getStringArrayList("KEYRATE")
        val dates = arguments?.getStringArrayList("KEYRELEASE")
        val titles = arguments?.getStringArrayList("KEYTITLE")
        var imageList = arrayListOf<ItemOfList>()
        if ((rates != null)&&(dates != null)&&(titles != null)) {
            for (i in 0..rates.size-2){
                val item = ItemOfList(R.drawable.ic_home_black_24dp, titles[i],rates[i],dates[i])
                imageList.add(item)
                Toast.makeText(context,titles[i]+rates[i]+dates[i],Toast.LENGTH_LONG)
                TimeUnit.SECONDS.sleep(2);
            }
        }
        else
            Toast.makeText(context,"LOH",Toast.LENGTH_LONG)
        val rootView = inflater.inflate(R.layout.fragment_blank, container, false)
        val recyclerview = rootView.findViewById(R.id._imageRecyclerView) as RecyclerView // Add this
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = ItemAdapter(context, imageList){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("OBJECT_INTENT", it)
            startActivity(intent)
        }
        return rootView
    }
}
