package id.vincent.neoz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logo: ImageView = findViewById(R.id.logo)
        val loaText: TextView = findViewById(R.id.loading)

        val animate = AnimationUtils.loadAnimation(this, R.anim.fade)


        logo.startAnimation(animate)
        loaText.startAnimation(animate)


        Handler(Looper.getMainLooper()).postDelayed({
            if (isFirstTime()){
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@MainActivity, Beranda::class.java)
                startActivity(intent)
            }
            finish()
        }, 3000)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun isFirstTime(): Boolean {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val isFirstTime = sharedPref.getBoolean("isFirstTime", true)
        if (isFirstTime) {
            with(sharedPref.edit()) {
                putBoolean("isFirstTime", false)
                apply()
            }
        }
        return isFirstTime
    }
}
