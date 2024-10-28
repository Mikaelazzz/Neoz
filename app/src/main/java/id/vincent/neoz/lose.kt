package id.vincent.neoz

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import android.content.Intent
import android.os.Build
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.abs
import kotlin.math.ceil

class lose : AppCompatActivity() {
    private lateinit var totalmatch: EditText
    private lateinit var totalwr: EditText
    private lateinit var targetwr: EditText
    private lateinit var jumlah: Button
    private lateinit var resultText: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lose)

        // Initialize views
        totalmatch = findViewById(R.id.totalmatch)
        totalwr = findViewById(R.id.totalwr)
        targetwr = findViewById(R.id.target)
        jumlah = findViewById(R.id.hitung)
        resultText = findViewById(R.id.result_text)

        // Hide result text initially
        resultText.visibility = View.GONE

        setupToolbar()
        handleBackPress()
        applyWindowInsets()
        setupCalculationButton()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupCalculationButton() {
        jumlah.setOnClickListener {
            calculateRequiredMatches()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateRequiredMatches() {
        try {
            // Ambil nilai input
            val totalMatches = totalmatch.text.toString().toDouble()
            val currentWR = totalwr.text.toString().toDouble()
            val loseStreak = targetwr.text.toString().toDouble()

            if (currentWR > 100 || loseStreak < 0) {
                if (resultText.visibility == View.GONE) {
                    resultText.visibility = View.VISIBLE
                }
                resultText.text = "Win Rate tidak bisa lebih dari 100% dan lose streak tidak bisa kurang dari 0"
                resultText.setTextColor(resources.getColor(R.color.font))
                resultText.typeface = resources.getFont(R.font.poppinsbold)
                return
            }

            if (loseStreak >= currentWR) {
                if (resultText.visibility == View.GONE) {
                    resultText.visibility = View.VISIBLE
                }
                resultText.text = "Silakan beralih ke Kalkulator Win Streak untuk meningkatkan Win Rate"
                resultText.setTextColor(resources.getColor(R.color.font))
                resultText.typeface = resources.getFont(R.font.poppinsbold)
                return
            }

            // Hitung jumlah kemenangan saat ini
            val currentWins = (totalMatches * currentWR) / 100
            val newTotalMatches = totalMatches + loseStreak

            // Hitung Win Rate baru setelah lose streak
            val newWinRate = (currentWins / newTotalMatches) * 100

            // Tampilkan hasil
            if (resultText.visibility == View.GONE) {
                resultText.visibility = View.VISIBLE
            }
            resultText.text = "Jika Anda losestreak sebanyak ${loseStreak.toInt()} kali, maka Win Rate Anda menjadi ${"%.1f".format(newWinRate)}%"
            resultText.setTextColor(resources.getColor(R.color.font))
            resultText.typeface = resources.getFont(R.font.poppinsbold)

        } catch (e: NumberFormatException) {
            if (resultText.visibility == View.GONE) {
                resultText.visibility = View.VISIBLE
            }
            resultText.text = "Mohon masukkan angka yang valid"
            resultText.setTextColor(resources.getColor(R.color.font))
            resultText.typeface = resources.getFont(R.font.poppinsbold)
        } catch (e: Exception) {
            if (resultText.visibility == View.GONE) {
                resultText.visibility = View.VISIBLE
            }
            resultText.text = "Terjadi kesalahan dalam perhitungan"
            resultText.setTextColor(resources.getColor(R.color.font))
            resultText.typeface = resources.getFont(R.font.poppinsbold)
        }
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
}