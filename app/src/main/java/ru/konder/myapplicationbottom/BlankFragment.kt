package ru.konder.myapplicationbottom


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.util.Log
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
        val titles = arguments?.get("KEY1")
        val tArray = arrayListOf <String>()
        val dates=arguments?.get("KEY2")
        val dArray = arrayListOf <String>()
        val rates=arguments?.get("KEY3")
        val rArray = arrayListOf <String>()
        val patches=arguments?.get("KEY4")
        val pArray = arrayListOf <String>()
        var i=-1
        for(char in titles.toString()){
            if(char=='_'){
                i+=1
                tArray.add("")
            }
            else{
                tArray[i]+=char.toString()
            }
        }
        i=-1
        for(char in dates.toString()){
            if(char=='_'){
                i+=1
                dArray.add("")
            }
            else{
                dArray[i]+=char.toString()
            }
        }
        i=-1
        for(char in rates.toString()){
            if(char=='_'){
                i+=1
                rArray.add("")
            }
            else{
                rArray[i]+=char.toString()
            }
        }
        i=-1
        for(char in patches.toString()){
            if(char=='_'){
                i+=1
                pArray.add("")
            }
            else{
                pArray[i]+=char.toString()
            }
        }
        var imageList = arrayListOf<ItemOfList>()
        for(i in 0..19)
            imageList.add(ItemOfList(pArray[i],tArray[i],rArray[i],dArray[i]))
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
