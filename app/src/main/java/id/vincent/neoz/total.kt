package id.vincent.neoz

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class total : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_total)

        setupToolbar()
        handleBackPress()
        applyWindowInsets()

        // Inisialisasi elemen UI
        val totalMatchInput = findViewById<EditText>(R.id.totalmatch)
        val winRateInput = findViewById<EditText>(R.id.totalwr)
        val resultText = findViewById<TextView>(R.id.result_text)
        val hitungButton = findViewById<Button>(R.id.hitung)

        // Sembunyikan resultText di awal
        resultText.visibility = View.GONE

        // Fungsi ketika tombol "Hitung" ditekan
        hitungButton.setOnClickListener {
            val totalMatchStr = totalMatchInput.text.toString()
            val winRateStr = winRateInput.text.toString()

            // Validasi input
            if (totalMatchStr.isEmpty() || winRateStr.isEmpty()) {
                tampilkanPesanKesalahan(resultText, "Masukkan nilai yang valid")
                return@setOnClickListener
            }

            // Konversi input ke angka desimal
            val totalMatch = totalMatchStr.toDoubleOrNull()
            val winRate = winRateStr.replace(",", ".").toDoubleOrNull()

            if (totalMatch == null || winRate == null || winRate > 100) {
                tampilkanPesanKesalahan(resultText, "Impossible broo")
                return@setOnClickListener
            }

            // Hitung total win dan total lose
            val totalWin = (totalMatch * winRate / 100).toInt()
            val totalLose = totalMatch.toInt() - totalWin

            // Tampilkan hasil dan ubah visibilitas resultText
            resultText.text = "Total win: $totalWin match\nTotal lose: $totalLose match"
            resultText.setTextColor(resources.getColor(R.color.font))
            resultText.typeface = resources.getFont(R.font.poppinsbold)
            resultText.visibility = View.VISIBLE
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun tampilkanPesanKesalahan(resultText: TextView, pesan: String) {
        resultText.text = pesan
        resultText.setTextColor(resources.getColor(R.color.font))
        resultText.typeface = resources.getFont(R.font.poppinsbold)
        resultText.visibility = View.VISIBLE
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
