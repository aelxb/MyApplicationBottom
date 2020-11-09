package ru.konder.myapplicationbottom

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView


class ItemAdapter(

    val context: Context?,
    private val images: List<ItemOfList>,
    val animn: Animation,
    val listener: (ItemOfList) -> Unit

): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view) {
        val imageSrc = view.findViewById<ImageView>(R.id._image)
        val title = view.findViewById<TextView>(R.id.title)
        val date = view.findViewById<TextView>(R.id.daterelease)
        val rate = view.findViewById<TextView>(R.id.rating)
        val over = view.findViewById<TextView>(R.id.overwiew)
        val prBar = view.findViewById<ProgressBar>(R.id.progressBarForlist)
        fun bind(image: ItemOfList, animochka: Animation, listener: (ItemOfList) -> Unit){
            title.text = image.title
            date.text = image.date
            rate.text = image.rate
            itemView.setOnClickListener{
                onCl(image, animochka)
            }
        }
        private fun onCl(image: ItemOfList, animochka: Animation) {
            if(over.visibility==View.GONE){
                over.visibility = View.VISIBLE
                over.startAnimation(animochka)
                over.text = image.description
            }
            else
                over.visibility = View.GONE
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]
        DownloadImageTask(holder.imageSrc, holder.prBar).execute("http://image.tmdb.org/t/p/w600_and_h900_bestv2"+image.imageSrc)
        holder.bind(images[position], animn, listener)
    }
    override fun getItemCount(): Int {
        return images.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
}
