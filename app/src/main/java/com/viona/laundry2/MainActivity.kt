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
import com.viona.laundry2.Pegawai.DataPegawaiActivity
import com.viona.laundry2.Pelanggan.DataPelangganActivity
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity(),  View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val tvSalam = findViewById<TextView>(R.id.tvSalam)
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        tvSalam.text = when {
            hour in 5..11 -> getString(R.string.salam_pagi)  // 05:00 - 11:59
            hour in 12..17 -> getString(R.string.salam_siang) // 12:00 - 17:59
            else -> getString(R.string.salam_malam) // 18:00 - 04:59
        }

        val tvTanggal = findViewById<TextView>(R.id.tvtanggal)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        val tanggalHariIni = dateFormat.format(calendar.time)

        tvTanggal.text = tanggalHariIni

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

        val cardMasukPegawai = findViewById<LinearLayout>(R.id.icons_Pegawai)
        cardMasukPegawai.setOnClickListener {
            val intent = Intent(this, DataPegawaiActivity::class.java)
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