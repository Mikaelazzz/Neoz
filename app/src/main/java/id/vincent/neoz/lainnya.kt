package id.vincent.neoz

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
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
        val type: String
    )

    private val heroes = listOf(
        Hero(R.drawable.gambar1, "Claude", "Mage", 24.99, "Damage / Crowd Control"),
        Hero(R.drawable.gambar1, "Alice", "Mage", 30.0, "Burst / Crowd Control"),
        Hero(R.drawable.gambar1, "Aldous", "Fighter", 20.5, "Damage"),
        Hero(R.drawable.gambar1, "Lancelot", "Assassin", 18.75, "Damage / Burst")
        // Add more heroes as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lainnya)

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
        }

        override fun getItemCount() = heroes.size
    }
}
