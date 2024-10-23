package id.vincent.neoz

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class tampilan1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tampilan1)

        val heroImage = intent.getIntExtra("image", 0)
        val heroName = intent.getStringExtra("name")
        val heroRole = intent.getStringExtra("role")
        val heroBanRate = intent.getDoubleExtra("banRate", 0.0)
        val heroType = intent.getStringExtra("type")
        val bgHr = intent.getIntExtra("bghr", 0)
        val imageR = intent.getIntExtra("imager",0)
        val goldBp = intent.getStringExtra("gold")
        val berlianDm = intent.getStringExtra("berlian")
        val tiketMl = intent.getStringExtra("tikett")
        val tierHr = intent.getIntExtra("tier",0)
        val desTier = intent.getStringExtra("destier")
        val laneHr = intent.getIntExtra("lane",0)
        val tlaneHr = intent.getStringExtra("tlane")
        val deslaneHr = intent.getStringExtra("deslane")


        val bghero = findViewById<ImageView>(R.id.bghero)
        val imgHero = findViewById<ImageView>(R.id.logohero)
        val nameHero = findViewById<TextView>(R.id.titlehero)
        val roleHero = findViewById<TextView>(R.id.rolehero)
        val bpoin = findViewById <TextView>(R.id.bpoin)
        val dm = findViewById <TextView>(R.id.dm)
        val tiket = findViewById <TextView>(R.id.tiket)
        val tierHero = findViewById<ImageView>(R.id.tierhero)
        val desktier = findViewById<TextView>(R.id.desktier)
        val heroLane = findViewById<ImageView>(R.id.heroLane)
        val titleHeroLane = findViewById<TextView>(R.id.titleherolane)
        val deskHeroLane = findViewById<TextView>(R.id.deskherolane)

        bghero.setImageResource(bgHr)
        imgHero.setImageResource(imageR)
        nameHero.text = heroName ?: "Null"
        roleHero.text = heroRole ?: "Null"
        bpoin.text = goldBp ?: "0"
        dm.text = berlianDm ?: "0"
        tiket.text = tiketMl ?: "0"
        tierHero.setImageResource(tierHr)
        desktier.text = desTier ?: "Null"
        heroLane.setImageResource(laneHr)
        titleHeroLane.text = tlaneHr ?: "Null"
        deskHeroLane.text = deslaneHr ?: "Null"

        // Mengaktifkan tombol back pada action bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.navigationIcon?.setTint(resources.getColor(R.color.font))

        // Handle fungsi Back dengan sistem
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish() // Menutup activity saat tombol back ditekan
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    // Menangani event ketika tombol di action bar ditekan
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Menutup activity saat tombol back di action bar ditekan
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}