package ru.konder.myapplicationbottom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(

    private val context: Context?,
    private val images: List<ItemOfList>,
    val listener: (ItemOfList) -> Unit

): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageSrc = view.findViewById<ImageView>(R.id._image)
        val tvTitle = view.findViewById<TextView>(R.id.title)
        fun bind(image: ItemOfList, listener: (ItemOfList) -> Unit){
            imageSrc.setImageResource(image.imageSrc)
            tvTitle.text = image.title
            itemView.setOnClickListener{
                (listener(image))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position], listener)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
}