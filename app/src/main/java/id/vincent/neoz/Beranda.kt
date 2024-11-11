package id.vincent.neoz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.vincent.neoz.lainnya.Hero
import id.vincent.neoz.lainnya.HeroAdapter

class Beranda : AppCompatActivity() {





    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

//        String Desk Tier
        val Splus = getString(R.string.splus)
        val S = getString(R.string.s)
        val Aplus = getString(R.string.aplus)
        val A = getString(R.string.a)
        val B = getString(R.string.b)

//        String Role
        val jungle = getString(R.string.jungle)
        val gold = getString(R.string.gold)
        val exp = getString(R.string.exp)
        val roam = getString(R.string.roam)
        val mid = getString(R.string.mid)


            val heroes = listOf(
                Hero(R.drawable.gambar1, "Claude", "Mage", 24.99, "Damage / Crowd Control", R.drawable.serene, R.drawable.hero1, "32000", "599", "0", R.drawable.jung, Splus, R.drawable.jung, "Jungle", jungle, "null", "null", R.drawable.buff, "Buff"),
                Hero(R.drawable.gambar1, "Claude", "Mage", 24.99, "Damage / Crowd Control", R.drawable.freya, R.drawable.hero1, "32000", "599", "0", R.drawable.jung, Splus, R.drawable.jung, "Jungle", jungle, "null", "null", R.drawable.buff, "Buff")
        )

             val heroesgrid = listOf(
                 Hero(R.drawable.gambar1, "Ling", "Assasin", 26.27, "Chase / Burst", R.drawable.serene, R.drawable.hero1,"32000","599", "0", R.drawable.jung, Splus,R.drawable.jung,"Jungle",jungle,"null", "null", R.drawable.buff, "Buff" ),
                 Hero(R.drawable.gambar1, "Freya", "Fighter", 1.26, "Chase / Damage", R.drawable.freya, R.drawable.hero2,"0","599", "0", R.drawable.gold,Splus,R.drawable.gold,"Gold",gold,"null", "null", R.drawable.buff, "Buff" ),
                 Hero(R.drawable.gambar1, "Aldous", "Fighter", 20.5, "Damage", R.drawable.serene, R.drawable.hero3,"32000","599", "0", R.drawable.exp, B,R.drawable.exp,"Exp",exp,"null", "null", R.drawable.buff, "Buff" ),
                 Hero(R.drawable.gambar1, "Selena", "Assassin / Mage", 18.75, "Damage / Burst", R.drawable.freya, R.drawable.hero4,"32000","599", "0", R.drawable.roam,B,R.drawable.roam,"Roam",roam,"null", "null", R.drawable.buff, "Buff" ),
                 Hero(R.drawable.gambar1, "Lancelot", "Assassin", 18.75, "Damage / Burst", R.drawable.serene, R.drawable.hero5,"32000","599", "0", R.drawable.jung,A,R.drawable.jung,"Jungle", jungle,"null", "null", R.drawable.buff, "Buff" )
             )
             val heroeshori = listOf(
                 Hero(R.drawable.gambar1, "Ling", "Assasin", 26.27, "Chase / Burst", R.drawable.serene, R.drawable.hero1,"32000","599", "0", R.drawable.jung, Splus,R.drawable.jung,"Jungle",jungle,"null", "null", R.drawable.buff, "Buff" ),
                 Hero(R.drawable.gambar1, "Freya", "Fighter", 1.26, "Chase / Damage", R.drawable.freya, R.drawable.hero2,"0","599", "0", R.drawable.gold,Splus,R.drawable.gold,"Gold",gold,"null", "null", R.drawable.nerf, "Nerf" ),
                 Hero(R.drawable.gambar1, "Aldous", "Fighter", 20.5, "Damage", R.drawable.serene, R.drawable.hero3,"32000","599", "0", R.drawable.exp, B,R.drawable.exp,"Exp",exp,"null", "null", R.drawable.penyesuaian, "Penyesuaian" ),
                 Hero(R.drawable.gambar1, "Selena", "Assassin / Mage", 18.75, "Damage / Burst", R.drawable.freya, R.drawable.hero4,"32000","599", "0", R.drawable.roam,B,R.drawable.roam,"Roam",roam,"null", "null", R.drawable.buff, "Buff" ),
                 Hero(R.drawable.gambar1, "Lancelot", "Assassin", 18.75, "Damage / Burst", R.drawable.serene, R.drawable.hero5,"32000","599", "0", R.drawable.jung,A,R.drawable.jung,"Jungle", jungle,"null", "null", R.drawable.buff, "Buff" )
             )

        // Tambahkan ini di dalam onCreate setelah inisialisasi heroRecyclerView
        val recyclerViewGrid = findViewById<RecyclerView>(R.id.heroRecyclerViewGrid)
        recyclerViewGrid.layoutManager = GridLayoutManager(this, 4) // 4 adalah jumlah kolom
        recyclerViewGrid.adapter = HeroAdapterGrid(heroesgrid) // Gunakan adapter untuk grid


        val recyclerView = findViewById<RecyclerView>(R.id.heroRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HeroAdapter(heroes)

        val recyclerViewhori = findViewById<RecyclerView>(R.id.heroRecyclerViewhori)
        recyclerViewhori.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewhori.adapter = HeroHori(heroeshori)
        recyclerViewhori.setHasFixedSize(true)


        val btnlain : Button = findViewById(R.id.btnlainnya)


        btnlain.setOnClickListener{
            val intent = Intent (this,lainnya::class.java)
            startActivity(intent)
        }



        bottomNavigationView = findViewById(R.id.footer)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    showGuideContent()
                    true
                }
                R.id.kalkulator -> {
                    replaceFragment(kalkulator())
                    true
                }
                else -> false
            }
        }

        // Set default to show Guide content
        showGuideContent()
    }

    private fun showGuideContent() {
        // Show the ScrollView content (Guide)
        findViewById<ScrollView>(R.id.scroll).visibility = View.VISIBLE
        supportFragmentManager.findFragmentById(R.id.fragmentContainer)?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        // Hide the ScrollView content
        findViewById<ScrollView>(R.id.scroll).visibility = View.GONE
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()

        class HeroAdapter(private val heroes: List<Hero>) : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

            inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                private val imgHero: ImageView = itemView.findViewById(R.id.imghero)
                private val namaHero: TextView = itemView.findViewById(R.id.namahero)
                private val roleHero: TextView = itemView.findViewById(R.id.rolehero)
                private val banHero: TextView = itemView.findViewById(R.id.banhero)
                private val tipeHero: TextView = itemView.findViewById(R.id.tipehero)

                fun bind(hero: Hero) {
                    imgHero.setImageResource(hero.image)
                    namaHero.text = hero.name
                    roleHero.text = hero.role
                    banHero.text = "Ban Rate: ${hero.banRate}%"
                    tipeHero.text = hero.type
                }
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.activity_hero_item, parent, false)
                return HeroViewHolder(view)
            }

            override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
                holder.bind(heroes[position])
                holder.itemView.setOnClickListener {
                    val context = holder.itemView.context
                    val hero = heroes[position]
                    val intent = Intent(context, tampilan1::class.java).apply {
                        putExtra("image", hero.image)
                        putExtra("name", hero.name)
                        putExtra("role", hero.role)
                        putExtra("banRate", hero.banRate)
                        putExtra("type", hero.type)
                        putExtra("bghr", hero.bghr)
                        putExtra("imager", hero.imager)
                        putExtra("gold", hero.gold)
                        putExtra("berlian", hero.berlian)
                        putExtra("tikett", hero.tikett)
                        putExtra("tier", hero.tier)
                        putExtra("destier", hero.destier)
                        putExtra("lane", hero.lane)
                        putExtra("tlane", hero.tlane)
                        putExtra("deslane", hero.deslane)
                    }
                    context.startActivity(intent)
                }
            }

            override fun getItemCount() = heroes.size
        }
    }
}
class HeroAdapterGrid(private val heroesgrid: List<Hero>) : RecyclerView.Adapter<HeroAdapterGrid.HeroViewHolder>() {

    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgHero: ImageView = itemView.findViewById(R.id.logohero)
        private val namaHero: TextView = itemView.findViewById(R.id.titlehero)

        fun bind(hero: Hero) {
            imgHero.setImageResource(hero.imager)
            namaHero.text = hero.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hero_grid, parent, false) // Gunakan item_hero_grid
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(heroesgrid[position])
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val hero = heroesgrid[position]
            val intent = Intent(context, tampilan1::class.java).apply {
                putExtra("image", hero.image)
                putExtra("name", hero.name)
                putExtra("role", hero.role)
                putExtra("banRate", hero.banRate)
                putExtra("type", hero.type)
                putExtra("bghr", hero.bghr)
                putExtra("imager", hero.imager)
                putExtra("gold", hero.gold)
                putExtra("berlian", hero.berlian)
                putExtra("tikett", hero.tikett)
                putExtra("tier", hero.tier)
                putExtra("destier", hero.destier)
                putExtra("lane", hero.lane)
                putExtra("tlane", hero.tlane)
                putExtra("deslane", hero.deslane)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = heroesgrid.size
}


class HeroHori(private val heroeshori: List<Hero>) : RecyclerView.Adapter<HeroHori.HeroViewHolder>() {


    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgHero: ImageView = itemView.findViewById(R.id.imghero)
        private val patchImg: LinearLayout = itemView.findViewById(R.id.patch)
        private val textPatch: TextView = itemView.findViewById(R.id.textpatch)

        fun bind(hero: Hero) {
            imgHero.setImageResource(hero.image)
            patchImg.setBackgroundResource(hero.patchimg)
            textPatch.text = hero.textpatch
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.horizontal_item, parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        Log.d("HeroHori", "Binding item at position: $position")
        holder.bind(heroeshori[position])
        holder.itemView.visibility = View.VISIBLE

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val hero = heroeshori[position]
            val intent = Intent(context, update_patch::class.java).apply {
                putExtra("image", hero.image)
                putExtra("name", hero.name)
                putExtra("role", hero.role)
                putExtra("banRate", hero.banRate)
                putExtra("type", hero.type)
                putExtra("bghr", hero.bghr)
                putExtra("imager", hero.imager)
                putExtra("gold", hero.gold)
                putExtra("berlian", hero.berlian)
                putExtra("tikett", hero.tikett)
                putExtra("tier", hero.tier)
                putExtra("destier", hero.destier)
                putExtra("lane", hero.lane)
                putExtra("tlane", hero.tlane)
                putExtra("deslane", hero.deslane)
                putExtra("desbefore", hero.desbefore)
                putExtra("desafter", hero.desafter)
                putExtra("patchh", hero.patchimg)
                putExtra("textpatchh", hero.textpatch)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = heroeshori.size

}

