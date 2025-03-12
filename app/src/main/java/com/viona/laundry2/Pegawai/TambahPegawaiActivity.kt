package com.viona.laundry2.Pegawai

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import com.viona.laundry2.R
import com.viona.laundry2.modeldata.ModelPegawai

class TambahPegawaiActivity : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pegawai")
    lateinit var tvTambahPegawai: TextView
    lateinit var tvNamaLengkapPegawai: TextView
    lateinit var etNamaLengkapPegawai: EditText
    lateinit var tvAlamatPegawai: TextView
    lateinit var etAlamatPegawai: EditText
    lateinit var tvNoHPPegawai: TextView
    lateinit var etNoHPPegawai: EditText
    lateinit var tvCabangPegawai: TextView
    lateinit var etCabangPegawai: EditText
    lateinit var btSimpan_TambahPegawai: Button

    var idPegawai:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pegawai)
        init()
        getData()
        btSimpan_TambahPegawai.setOnClickListener{
            CekValidasi()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init() {
        tvTambahPegawai = findViewById(R.id.tvTambahPegawai)
        tvNamaLengkapPegawai = findViewById(R.id.tvNamaLengkapPegawai)
        etNamaLengkapPegawai = findViewById(R.id.etNamaLengkapPegawai)
        tvAlamatPegawai = findViewById(R.id.tvAlamatPegawai)
        etAlamatPegawai = findViewById(R.id.etAlamatPegawai)
        tvNoHPPegawai = findViewById(R.id.tvNoHPPegawai)
        etNoHPPegawai = findViewById(R.id.etNoHPPegawai)
        tvCabangPegawai = findViewById(R.id.tvCabangPegawai)
        etCabangPegawai = findViewById(R.id.etCabangPegawai)
        btSimpan_TambahPegawai = findViewById(R.id.btSimpan_TambahPegawai)
    }

    fun getData(){
        idPegawai = intent.getStringExtra("idPegawai").toString()
        val judul = intent.getStringExtra("judul")
        val nama = intent.getStringExtra("namaPegawai")
        val alamat = intent.getStringExtra("alamatPegawai")
        val hp = intent.getStringExtra("noHPPegawai")
        val cabang = intent.getStringExtra("idCabang")
        tvTambahPegawai.text = judul
        etNamaLengkapPegawai.setText(nama)
        etAlamatPegawai.setText(alamat)
        etNoHPPegawai.setText(hp)
        etCabangPegawai.setText(cabang)
        if(!tvTambahPegawai.text.equals(this.getString(R.string.tvTambahPegawai))){
            if(judul.equals("Edit Pegawai")){
                mati()
                btSimpan_TambahPegawai.text="Sunting"
            }
        }else{
            hidup()
            etNamaLengkapPegawai.requestFocus()
            btSimpan_TambahPegawai.text="Simpan"
        }
    }

    fun mati(){
        etNamaLengkapPegawai.isEnabled=false
        etAlamatPegawai.isEnabled=false
        etNoHPPegawai.isEnabled=false
        etCabangPegawai.isEnabled=false
    }

    fun hidup(){
        etNamaLengkapPegawai.isEnabled=true
        etAlamatPegawai.isEnabled=true
        etNoHPPegawai.isEnabled=true
        etCabangPegawai.isEnabled=true
    }

    fun update(){
        val pegawaiRef = database.getReference("pegawai").child(idPegawai)
        val data = ModelPegawai(
            idPegawai,
            etNamaLengkapPegawai.text.toString(),
            etAlamatPegawai.text.toString(),
            etNoHPPegawai.text.toString(),
            etCabangPegawai.text.toString())
        //Buat Map untuk update data
        val updateData = mutableMapOf<String, Any>()
        updateData["namaPegawai"] = data.namaPegawai.toString()
        updateData["alamatPegawai"] = data.alamatPegawai.toString()
        updateData["noHPPegawai"] = data.noHPPegawai.toString()
        updateData["idCabang"] = data.idCabangPegawai.toString()
        pegawaiRef.updateChildren(updateData).addOnSuccessListener{
            Toast.makeText(this@TambahPegawaiActivity,"Data Pegawai Berhasil Diperbarui", Toast.LENGTH_SHORT).show()
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@TambahPegawaiActivity, "Data Pegawai Gagal Diperbarui", Toast.LENGTH_SHORT).show()
        }
    }

    fun simpan() {
        val pegawaiBaru = myRef.push()
        val idPegawai = pegawaiBaru.key
        val data = ModelPegawai(
            idPegawai.toString(),
            etNamaLengkapPegawai.text.toString(),
            etAlamatPegawai.text.toString(),
            etNoHPPegawai.text.toString(),
            etCabangPegawai.text.toString(),
            "timestamp"
        )
        pegawaiBaru.setValue(data)
            .addOnSuccessListener{
                Toast.makeText(
                    this,
                    this.getString(R.string.sukses_simpan_pegawai),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
            .addOnFailureListener() {
                Toast.makeText(
                    this,
                    this.getString(R.string.gagal_simpan_pegawai),
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun CekValidasi() {
        val nama = etNamaLengkapPegawai.text.toString()
        val alamat = etAlamatPegawai.text.toString()
        val noHP = etNoHPPegawai.text.toString()
        val cabang = etCabangPegawai.text.toString()
        // Validasi data
        if (nama.isEmpty()) {
            etNamaLengkapPegawai.error = this.getString(R.string.validasi_etNama)
            Toast.makeText(this, this.getString(R.string.validasi_etNama), Toast.LENGTH_SHORT).show()
            etNamaLengkapPegawai.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etAlamatPegawai.error = this.getString(R.string.validasi_etAlamat)
            Toast.makeText(this, this.getString(R.string.validasi_etAlamat), Toast.LENGTH_SHORT).show()
            etAlamatPegawai.requestFocus()
            return
        }
        if (noHP.isEmpty()) {
            etNoHPPegawai.error = this.getString(R.string.validasi_etNoHP)
            Toast.makeText(this, this.getString(R.string.validasi_etNoHP), Toast.LENGTH_SHORT).show()
            etNoHPPegawai.requestFocus()
            return
        }
        if (cabang.isEmpty()) {
            etCabangPegawai.error = this.getString(R.string.validasi_etCabang)
            Toast.makeText(this, this.getString(R.string.validasi_etCabang), Toast.LENGTH_SHORT).show()
            etCabangPegawai.requestFocus()
            return
        }
        if (btSimpan_TambahPegawai.text.equals("Simpan")){
            simpan()
        }else if (btSimpan_TambahPegawai.text.equals("Sunting")){
            hidup()
            etNamaLengkapPegawai.requestFocus()
            btSimpan_TambahPegawai.text="Perbarui"
        }else if (btSimpan_TambahPegawai.text.equals("Perbarui")){
            update()
        }
    }

}