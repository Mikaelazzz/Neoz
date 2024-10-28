package id.vincent.neoz

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
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

    private fun setupCalculationButton() {
        jumlah.setOnClickListener {
            calculateRequiredMatches()
        }
    }

    private fun calculateRequiredMatches() {
        try {
            // Get input values
            val totalMatches = totalmatch.text.toString().toDouble()
            val currentWR = totalwr.text.toString().toDouble()
            val targetWR = targetwr.text.toString().toDouble()

            if (currentWR > 100 || targetWR > 100) {
                if (resultText.visibility == View.GONE) {
                    resultText.visibility = View.VISIBLE
                }
                resultText.text = "Win Rate tidak boleh lebih dari 100%"
                resultText.setTextColor(resources.getColor(android.R.color.holo_red_light))
                return
            }
            // Validate if target WR is less than current WR
            if (targetWR <= currentWR) {
                if (resultText.visibility == View.GONE) {
                    resultText.visibility = View.VISIBLE
                }
                resultText.text = "Silakan beralih ke Kalkulator Lose Streak untuk menghitung penurunan Win Rate"
                resultText.setTextColor(resources.getColor(android.R.color.holo_red_light))

                // Optional: Navigate to Lose Streak calculator after delay
//                resultText.postDelayed({
//                    val intent = Intent(this, lose::class.java)
//                    startActivity(intent)
//                }, 2000) // 2 second delay
//
//                return
            }

            // Calculate current wins
            val currentWins = (totalMatches * currentWR) / 100

            // Calculate required wins for target WR
            // Formula: x = (TWR * TM - 100W) / (TWR - 100)
            // Where: TWR = Target Win Rate, TM = Total Matches, W = Current Wins
            val requiredWins = abs(ceil((targetWR * totalMatches - 100 * currentWins) / (targetWR - 100)).toInt())

            // Show result TextView if it's hidden
            if (resultText.visibility == View.GONE) {
                resultText.visibility = View.VISIBLE
            }

            // Display result
            resultText.text = "Membutuhkan $requiredWins menang Tanpa Kalah untuk mendapatkan ${targetWR.toInt()}% Win Rate"
            resultText.setTextColor(resources.getColor(R.color.white))

        } catch (e: NumberFormatException) {
            if (resultText.visibility == View.GONE) {
                resultText.visibility = View.VISIBLE
            }
            resultText.text = "Mohon masukkan angka yang valid"
            resultText.setTextColor(resources.getColor(android.R.color.holo_red_light))
        } catch (e: Exception) {
            if (resultText.visibility == View.GONE) {
                resultText.visibility = View.VISIBLE
            }
            resultText.text = "Terjadi kesalahan dalam perhitungan"
            resultText.setTextColor(resources.getColor(android.R.color.holo_red_light))
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