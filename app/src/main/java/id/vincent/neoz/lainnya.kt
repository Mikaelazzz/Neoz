package id.vincent.neoz

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class lainnya : AppCompatActivity() {



        data class Hero(
        val image: Int,
        val name: String,
        val role: String,
        val banRate: Double,
        val type: String,
        val bghr: Int,
        val imager: Int,
        val gold: String,
        val berlian: String,
        val tikett: String,
        val tier: Int,
        val destier: String,
        val lane: Int,
        val tlane: String,
        val deslane: String,
        val desbefore: String,
        val desafter: String,
        val patchimg : Int,
        val textpatch : String
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lainnya)

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
            Hero(R.drawable.gambar1, "Ling", "Assasin", 26.27, "Chase / Burst", R.drawable.serene, R.drawable.hero1,"32000","599", "0", R.drawable.jung, Splus,R.drawable.jung,"Jungle",jungle,"null", "null", R.drawable.buff, "Buff" ),
            Hero(R.drawable.gambar1, "Freya", "Fighter", 1.26, "Chase / Damage", R.drawable.freya, R.drawable.hero2,"0","599", "0", R.drawable.gold,Splus,R.drawable.gold,"Gold",gold,"null", "null", R.drawable.buff, "Buff" ),
            Hero(R.drawable.gambar1, "Aldous", "Fighter", 20.5, "Damage", R.drawable.serene, R.drawable.hero3,"32000","599", "0", R.drawable.exp, B,R.drawable.exp,"Exp",exp,"null", "null", R.drawable.buff, "Buff" ),
            Hero(R.drawable.gambar1, "Selena", "Assassin / Mage", 18.75, "Damage / Burst", R.drawable.freya, R.drawable.hero4,"32000","599", "0", R.drawable.roam,B,R.drawable.roam,"Roam",roam,"null", "null", R.drawable.buff, "Buff" ),
            Hero(R.drawable.gambar1, "Lancelot", "Assassin", 18.75, "Damage / Burst", R.drawable.serene, R.drawable.hero5,"32000","599", "0", R.drawable.jung,A,R.drawable.jung,"Jungle", jungle,"null", "null", R.drawable.buff, "Buff" )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.heroRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HeroAdapter(heroes)

        setupToolbar()
        handleBackPress()
        applyWindowInsets()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.navigationIcon?.setTint(resources.getColor(R.color.font))
    }

    private fun handleBackPress() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    class HeroAdapter(private val heroes: List<Hero>) : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

        class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                    putExtra("desbefore", hero.desbefore)
                    putExtra("desafter", hero.desafter)

                }
                context.startActivity(intent)
            }
        }

        override fun getItemCount() = heroes.size
    }
}
