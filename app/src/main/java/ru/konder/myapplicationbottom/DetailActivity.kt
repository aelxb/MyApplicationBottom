package ru.konder.myapplicationbottom

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val item = intent.getParcelableExtra<ItemOfList>("OBJECT_INTENT")
        val imgSrc = findViewById<ImageView>(R.id._imageD)
        val imgTitle = findViewById<TextView>(R.id.titleD)
        val imgDesc = findViewById<TextView>(R.id.datereleaseD)
        val imgRate = findViewById<TextView>(R.id.ratingD)
        imgTitle.text=item.title
        imgDesc.text=item.date
        imgRate.text=item.rate
    }
}