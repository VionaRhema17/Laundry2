package com.viona.laundry2.Pelanggan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import com.viona.laundry2.R
import com.viona.laundry2.modeldata.ModelPelanggan

class TambahPelangganActivity : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pelanggan")
    lateinit var tvJudul: TextView
    lateinit var tvNamalengkap: TextView
    lateinit var etNamalengkap: EditText
    lateinit var tvAlamat: TextView
    lateinit var etAlamat: EditText
    lateinit var tvNoHP: TextView
    lateinit var etNoHP: EditText
    lateinit var tvCabang: TextView
    lateinit var etCabang: EditText
    lateinit var btSimpan_TambahPelanggan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pelanggan)
        init()
        btSimpan_TambahPelanggan.setOnClickListener {
            cekValidasi()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    fun init() {
        tvJudul = findViewById(R.id.tvJudul)
        tvNamalengkap = findViewById(R.id.tvNamaLengkap)
        etNamalengkap = findViewById(R.id.etNamaLengkap)
        tvAlamat = findViewById(R.id.tvAlamat)
        etAlamat = findViewById(R.id.etAlamat)
        tvNoHP = findViewById(R.id.tvNoHP)
        etNoHP = findViewById(R.id.etNoHP)
        tvCabang = findViewById(R.id.tvCabang)
        etCabang = findViewById(R.id.etCabang)
        btSimpan_TambahPelanggan = findViewById(R.id.btSimpan_TambahPelanggan)
    }

    fun simpan() {
        val pelangganBaru = myRef.push()
        val idPelanggan = pelangganBaru.key
        val data = ModelPelanggan(
            idPelanggan.toString(),
            etNamalengkap.text.toString(),
            etAlamat.text.toString(),
            etNoHP.text.toString(),
            etCabang.text.toString(),
            "timestamp"

            )
        pelangganBaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(
                    this,
                    this.getString(R.string.sukses_simpan_pelanggan),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
            .addOnFailureListener() {
                Toast.makeText(
                    this,
                    this.getString(R.string.gagal_simpan_pelanggan),
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun cekValidasi() {
        val nama = etNamalengkap.text.toString()
        val alamat = etAlamat.text.toString()
        val noHP = etNoHP.text.toString()
        val cabang = etCabang.toString()
        // Validasi data
        if (nama.isEmpty()) {
            etNamalengkap.error = this.getString(R.string.validasi_etNama)
            Toast.makeText(this, this.getString(R.string.validasi_etNama), Toast.LENGTH_SHORT).show()
            etNamalengkap.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etAlamat.error = this.getString(R.string.validasi_etAlamat)
            Toast.makeText(this, this.getString(R.string.validasi_etAlamat), Toast.LENGTH_SHORT).show()
            etAlamat.requestFocus()
            return
        }
        if (noHP.isEmpty()) {
            etNoHP.error = this.getString(R.string.validasi_etNoHP)
            Toast.makeText(this, this.getString(R.string.validasi_etNoHP), Toast.LENGTH_SHORT).show()
            etNoHP.requestFocus()
            return
        }
        if (cabang.isEmpty()) {
            etCabang.error = this.getString(R.string.validasi_etCabang)
            Toast.makeText(this, this.getString(R.string.validasi_etCabang), Toast.LENGTH_SHORT).show()
            etCabang.requestFocus()
            return
        }
        simpan()

    }
}