package id.vincent.neoz

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class update_patch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_patch)

        val heroName = intent.getStringExtra("name")
        val heroRole = intent.getStringExtra("role")
        val bgHr = intent.getIntExtra("bghr", 0)
        val imageR = intent.getIntExtra("imager",0)
        val desBefore = intent.getStringExtra("desbefore")
        val desAfter = intent.getStringExtra("desafter")
        val patchImg = intent.getIntExtra("patchh", 0)
        val textPatch = intent.getStringExtra("textpatchh")


        val bghero = findViewById<ImageView>(R.id.bghero)
        val imgHero = findViewById<ImageView>(R.id.logohero)
        val nameHero = findViewById<TextView>(R.id.titlehero)
        val roleHero = findViewById<TextView>(R.id.rolehero)
        val deskbeforee = findViewById<TextView>(R.id.desksebelum)
        val deskafterr = findViewById<TextView>(R.id.desksesudah)
        val patchimg = findViewById<LinearLayout>(R.id.patch)
        val textpatch = findViewById<TextView>(R.id.textpatch)

        bghero.setImageResource(bgHr)
        imgHero.setImageResource(imageR)
        nameHero.text = heroName ?: "Null"
        roleHero.text = heroRole ?: "Null"
        deskbeforee.text = desBefore ?: "Null"
        deskafterr.text = desAfter ?: "Null"
        patchimg.setBackgroundResource(patchImg)
        textpatch.text = textPatch ?: "Null"


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
