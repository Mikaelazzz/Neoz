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

class win : AppCompatActivity() {
    private lateinit var totalmatch: EditText
    private lateinit var totalwr: EditText
    private lateinit var targetwr: EditText
    private lateinit var jumlah: Button
    private lateinit var resultText: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_win)

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
            val targetWR = targetwr.text.toString().toDouble()

            if (currentWR > 100 || targetWR > 100) {
                if (resultText.visibility == View.GONE) {
                    resultText.visibility = View.VISIBLE
                }
                resultText.text = "Win Rate tidak bisa lebih dari 100%"
                resultText.setTextColor(resources.getColor(R.color.font))
                resultText.typeface = resources.getFont(R.font.poppinsbold)
                return
            }

            if (targetWR == 100.0) {
                if (resultText.visibility == View.GONE) {
                    resultText.visibility = View.VISIBLE
                }
                resultText.text = "Impossible broo"
                resultText.setTextColor(resources.getColor(R.color.font))
                resultText.typeface = resources.getFont(R.font.poppinsbold)
                return
            }

            // Validasi jika target WR lebih rendah dari WR saat ini
            if (targetWR <= currentWR) {
                if (resultText.visibility == View.GONE) {
                    resultText.visibility = View.VISIBLE
                }
                resultText.text = "Silakan beralih ke Kalkulator Lose Streak untuk menghitung penurunan Win Rate"
                resultText.setTextColor(resources.getColor(R.color.font))
                resultText.typeface = resources.getFont(R.font.poppinsbold)
                return
            }

            // Hitung jumlah kemenangan saat ini
            val currentWins = (totalMatches * currentWR) / 100

            // Hitung jumlah kemenangan yang dibutuhkan untuk mencapai target Win Rate
            // Formula: x = (TWR * TM - 100W) / (TWR - 100)
            // di mana TWR = Target Win Rate, TM = Total Matches, W = Current Wins
            val requiredWins = abs(ceil((targetWR * totalMatches - 100 * currentWins) / (targetWR - 100)).toInt())

            // Tampilkan hasil jika belum terlihat
            if (resultText.visibility == View.GONE) {
                resultText.visibility = View.VISIBLE
            }

            // Tampilkan hasil
            resultText.text = "Membutuhkan $requiredWins menang Tanpa Kalah untuk mendapatkan ${"%.0f".format(targetWR)}% Win Rate"
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