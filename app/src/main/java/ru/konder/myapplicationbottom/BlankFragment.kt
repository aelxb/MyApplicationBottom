package ru.konder.myapplicationbottom


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.util.Log
import android.view.animation.AnimationUtils
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
        val titles = arguments?.getStringArrayList("KEY1")
        val dates=arguments?.getStringArrayList("KEY2")
        val rates=arguments?.getStringArrayList("KEY3")
        val patches=arguments?.getStringArrayList("KEY4")
        val overviews=arguments?.getStringArrayList("KEY5")
        var imageList = arrayListOf<ItemOfList>()
        if(patches!=null&&titles!=null&&rates!=null&&dates!=null&&overviews!=null){
        for(i in 0..19)
            imageList.add(ItemOfList(patches[i],titles[i],rates[i],dates[i],overviews[i]))}
        val rootView = inflater.inflate(R.layout.fragment_blank, container, false)
        val recyclerview = rootView.findViewById(R.id._imageRecyclerView) as RecyclerView // Add this
        recyclerview.layoutManager = LinearLayoutManager(context)
        val animfortext = AnimationUtils.loadAnimation(context, R.anim.opacity_anim)
        recyclerview.adapter = ItemAdapter(context, imageList, animfortext){
            it
        }
        return rootView
    }
}