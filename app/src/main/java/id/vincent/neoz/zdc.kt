package id.vincent.neoz

import android.os.Bundle
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class zdc : AppCompatActivity() {
    private lateinit var seekBar: SeekBar
    private lateinit var starPointsText: TextView
    private lateinit var diamondText: TextView
    private lateinit var resultText: TextView

    // Updated constants
    private val INITIAL_DIAMONDS = 1700
    private val MAX_STARS = 100  // Set maximum stars to 100 for dynamic DIAMONDS_PER_STAR calculation

    // Calculate DIAMONDS_PER_STAR dynamically based on INITIAL_DIAMONDS and MAX_STARS
    private val diamondsPerStar: Int
        get() = INITIAL_DIAMONDS / MAX_STARS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_zdc)

        // Initialize views
        seekBar = findViewById(R.id.magicWheelSeekBar)
        starPointsText = findViewById(R.id.starPointsValue)
        diamondText = findViewById(R.id.diamondValue)
        resultText = findViewById(R.id.result_text)

        // Configure SeekBar
        seekBar.max = MAX_STARS
        seekBar.progress = 0

        // Set up SeekBar listener
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateCalculations(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Initialize calculations
        updateCalculations(0)

        // Set up edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup toolbar and back press handling
        setupToolbar()
        handleBackPress()
    }

    private fun updateCalculations(stars: Int) {
        // Calculate remaining diamonds dynamically
        val remainingDiamonds = INITIAL_DIAMONDS - (stars * diamondsPerStar)

        // Update UI elements
        starPointsText.text = stars.toString()

        // Only show remaining diamonds if there are any
        if (remainingDiamonds >= 0) {
            diamondText.text = remainingDiamonds.toString()

            // Update result text
            resultText.text = String.format(
                "Poin Bintang Kamu %d\nMembutuhkan Maksimal %d Diamond",
                stars,
                remainingDiamonds
            )
        } else {
            // If we've exceeded the maximum diamonds, cap at 0
            diamondText.text = "0"
            resultText.text = String.format(
                "Poin Bintang Kamu %d\nMembutuhkan Maksimal 0 Diamond",
                stars
            )
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
