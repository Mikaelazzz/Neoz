package id.vincent.neoz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val buttonNext = findViewById<Button>(R.id.button_next)

        buttonNext.setOnClickListener{

            val intent = Intent (this,Beranda::class.java)

            startActivity(intent)
        }
    }
}
