package id.vincent.neoz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Beranda : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val btnlain : Button = findViewById(R.id.btnlainnya)
        val hero : LinearLayout = findViewById(R.id.hero1)
        val heror : LinearLayout = findViewById(R.id.heror1)
        val schero : ImageView = findViewById(R.id.schero1)

        btnlain.setOnClickListener{
            val intent = Intent (this,lainnya::class.java)
            startActivity(intent)
        }

        hero.setOnClickListener{
            val intent = Intent (this,tampilan1::class.java)
            startActivity(intent)
        }

        heror.setOnClickListener{
            val intent = Intent (this,tampilan1::class.java)
            startActivity(intent)
        }

        schero.setOnClickListener{
            val intent = Intent (this,update_patch::class.java)
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
                    replaceFragment(KalkulatorFragment())
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
    }
}

