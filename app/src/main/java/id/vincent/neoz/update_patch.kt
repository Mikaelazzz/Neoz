package id.vincent.neoz

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class update_patch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_patch)

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
        val desBefore = intent.getStringExtra("desbefore")
        val desAfter = intent.getStringExtra("desafter")


        val bghero = findViewById<ImageView>(R.id.bghero)
        val imgHero = findViewById<ImageView>(R.id.logohero)
        val nameHero = findViewById<TextView>(R.id.titlehero)
        val roleHero = findViewById<TextView>(R.id.rolehero)
        val deskbeforee = findViewById<TextView>(R.id.desksebelum)
        val deskafterr = findViewById<TextView>(R.id.desksesudah)

        bghero.setImageResource(bgHr)
        imgHero.setImageResource(imageR)
        nameHero.text = heroName ?: "Null"
        roleHero.text = heroRole ?: "Null"
        deskbeforee.text = desBefore ?: "Null"
        deskafterr.text = desAfter ?: "Null"


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

        // Set padding untuk window insets
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
