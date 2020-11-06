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
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.list_item.view.*

class BlankFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val url:String = "https://api.themoviedb.org/3/movie/top_rated?api_key=ab9d01d6538c94cad37d1af4c042140d"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonObject = (JSONObject(response!!.body()!!.string()).getJSONObject("results"))
                val jsonArray = jsonObject.optJSONArray("title")
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val title = jsonObject.optString("title").toString()
                    val date = jsonObject.optString("release_date").toString()
                    val rating = jsonObject.optString("vote_average").toString()
                    val imgUrl = jsonObject.optString("poster_path").toString()
                }
            }
        })
        val imageList = listOf<ItemOfList>(
            ItemOfList(R.drawable.ic_home_black_24dp,
                "Гран-при Азербайджана все еще может состояться до середины октября",
                "Организаторы Гран-при Азербайджана надеются договориться с правительством страны о проведении гонки до середины октября.\n" +
                        "Если эпидемиологическая ситуация в Азербайджане не позволит это сделать до данного срока, то гран-при в этом сезоне будет отменен.\n" +
                        "Изначально проведение гран-при в Баку было запланировано на 7 июня, но гонку перенесли из-за пандемии коронавируса.\n" +
                        "Формула-1 может стартовать 5 июля, пока не проведен ни один этап. Летом планируется проведение гран-при Австрии, Великобритании и Венгрии."),
            ItemOfList(R.drawable.ic_home_black_24dp,
                "Венгрия и Китай будут строить железную дорогу в Белград",
                "Венгрия и Китай подписали кредитное соглашение для финансирования строительства железной дороги между Будапештом и Белградом. Кредит на сумму 1,855 млрд долларов США сроком на 20 лет выдан по ставке 2,5% годовых и предусматривает возможность досрочного погашения. Магистраль «Будапешт – Белград» – часть амбициозной инфраструктурной инициативы Китая «Пояс и путь». Это первый крупный китайский инфраструктурный проект такого масштаба на территории Центральной Европы. Железная дорога даст возможность быстро доставлять грузы и товары из Греции в Западную Европу. Финансирование строительства обеспечено, в основном, за счет заемных средств, часть денег вносит венгерская сторона. Китай, Сербия и Венгрия впервые подписали меморандум о железнодорожном проекте в 2014 году. Строительство в Сербии началось в 2018 году после того, как она взяла в долг у Китая 297,6 млн долларов."),
            ItemOfList(R.drawable.ic_home_black_24dp,
                "Канцлер Себастиан Курц лично позвонил протестующему владельцу фитнес-центра",
                "«Курц звучал очень умно и представительно. Канцлер проговорил со мной в общей сложности двадцать минут. Он терпеливо рассказал  про фонд для предпринимателей, из которого государство может покрыть до 75 процентов постоянных расходов, и даже пообещал дополнительную помощь от канцелярии». Канцлер был впечатлен решимостью предпринимателя и в конце беседы сказал, что «уверен, что все будет хорошо»."),
            ItemOfList(R.drawable.ic_home_black_24dp,
                "В Йошкар-Оле продолжается ямочный ремонт на дорогах",
                "Ремонтные работы ведутся на улице Герцена, на участке от улицы Первомайской до круговой транспортной развязки с улицей Суворова. По чётной стороне дороги проводится фрезерование, а по нечетной - укладывают горячую асфальтобетонную смесь. Также ямочный ремонт ведётся на улице Панфилова. Ранее «Марийская правда» сообщала, что в Йошкар-Оле стартовали дорожные работы в рамках нацпроекта «Безопасные и качественные автомобильные дороги».")
        )

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
