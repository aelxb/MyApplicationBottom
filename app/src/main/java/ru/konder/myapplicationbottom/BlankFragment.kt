package ru.konder.myapplicationbottom


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.list_item.view.*

class BlankFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
        val rootView = inflater.inflate(R.layout.fragment_blank, container, false)
        val recyclerview = rootView.findViewById(R.id._imageRecyclerView) as RecyclerView // Add this
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = MainAdapter()
        return rootView
    }
}
    class MainAdapter : RecyclerView.Adapter<CustomViewHolder>(){
        val videoTitles = listOf("First title", "Second", "3rd", "Moore Title")

        //number of item
        override fun getItemCount(): Int {
            return videoTitles.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            //create view
            val layoutInflater = LayoutInflater.from(parent.context)
            val cellForRow = layoutInflater.inflate(R.layout.list_item, parent, false)
            return CustomViewHolder(cellForRow)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            val videoTitle = videoTitles.get(position)
            holder?.view?.title.text= videoTitle
        }
    }

    class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

    }