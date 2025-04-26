package com.example.book_sport

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CatalogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        findViewById<Button>(R.id.btnFutsal).setOnClickListener {
            openForm("Futsal")
        }

        findViewById<Button>(R.id.btnBadminton).setOnClickListener {
            openForm("Badminton")
        }

        findViewById<Button>(R.id.btnBasket).setOnClickListener {
            openForm("Basket")
        }
    }

    private fun openForm(sport: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("selected_sport", sport)
        startActivity(intent)
    }
}
