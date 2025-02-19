package com.viona.laundry2

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.viona.laundry2.Layanan.LayananActivity
import com.viona.laundry2.Pelanggan.DataPelangganActivity
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val cardMasukLayananActivity = findViewById<CardView>(R.id.cvLayanan2)
        cardMasukLayananActivity.setOnClickListener {
            val intent = Intent(this, LayananActivity::class.java)
            startActivity(intent)
        }

        val cardMasukPelanggan = findViewById<LinearLayout>(R.id.icons_Pelanggan)
        cardMasukPelanggan.setOnClickListener {
            val intent = Intent(this, DataPelangganActivity::class.java)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(v: View) {

    }
}