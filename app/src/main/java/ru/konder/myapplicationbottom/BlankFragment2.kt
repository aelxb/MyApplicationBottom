package ru.konder.myapplicationbottom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val titles = arguments?.getStringArrayList("KEY1")
        val dates = arguments?.getStringArrayList("KEY2")
        val rates = arguments?.getStringArrayList("KEY3")
        val patches = arguments?.getStringArrayList("KEY4")
        val overviews=arguments?.getStringArrayList("KEY5")
        var imageList = arrayListOf<ItemOfList>()
        if (patches != null && titles != null && rates != null && dates != null&&overviews!=null) {
            for (i in 0..19)
                imageList.add(ItemOfList(patches[i], titles[i], rates[i], dates[i],overviews[i]))
        }
        val recyclerview =
            rootView.findViewById(R.id._imageRecyclerView2) as RecyclerView // Add this
        recyclerview.layoutManager = LinearLayoutManager(context)
        val animfortext = AnimationUtils.loadAnimation(context, R.anim.opacity_anim)
        recyclerview.adapter = ItemAdapter(context, imageList, animfortext){
            it
        }
        return rootView
    }
}