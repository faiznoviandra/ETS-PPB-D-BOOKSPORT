package com.example.book_sport

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerVenue: Spinner
    private lateinit var tvSelectedSport: TextView
    private lateinit var btnDate: Button
    private lateinit var btnTime: Button
    private lateinit var btnSubmit: Button

    private var selectedDate: String? = null
    private var selectedTime: String? = null
    private var selectedSport: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi view
        spinnerVenue = findViewById(R.id.spinnerVenue)
        tvSelectedSport = findViewById(R.id.tvSelectedSport)
        btnDate = findViewById(R.id.btnDate)
        btnTime = findViewById(R.id.btnTime)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Adapter untuk venue
        ArrayAdapter.createFromResource(
            this,
            R.array.venue_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerVenue.adapter = adapter
        }

        // Tangkap data dari intent
        selectedSport = intent.getStringExtra("selected_sport") ?: "Belum dipilih"
        tvSelectedSport.text = selectedSport

        // DatePickerDialog
        btnDate.setOnClickListener {
            val c = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    selectedDate = "%04d-%02d-%02d".format(year, month + 1, day)
                    btnDate.text = selectedDate
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // TimePickerDialog
        btnTime.setOnClickListener {
            val c = Calendar.getInstance()
            TimePickerDialog(
                this,
                { _, hour, minute ->
                    selectedTime = "%02d:%02d".format(hour, minute)
                    btnTime.text = selectedTime
                },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                true
            ).show()
        }

        // Tombol Submit
        btnSubmit.setOnClickListener {
            val venue = spinnerVenue.selectedItem as String

            if (selectedDate.isNullOrBlank() || selectedTime.isNullOrBlank()) {
                Toast.makeText(this, "Tanggal dan waktu harus dipilih", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val message = """
                Tempat   : $venue
                Olahraga : $selectedSport
                Tanggal  : $selectedDate
                Waktu    : $selectedTime
            """.trimIndent()

            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Pemesanan")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
