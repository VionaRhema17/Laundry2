package com.viona.laundry2.Pegawai

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.viona.laundry2.R
import com.viona.laundry2.adapter.DataPegawaiAdapter
import com.viona.laundry2.modeldata.ModelPegawai

class DataPegawaiActivity : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference()
    lateinit var rvDATA_PEGAWAI : RecyclerView
    lateinit var fabDATA_PEGAWAI_TAMBAH : FloatingActionButton
    lateinit var pegawaiList: ArrayList<ModelPegawai>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pegawai)

        init()

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rvDATA_PEGAWAI.layoutManager=layoutManager
        rvDATA_PEGAWAI.setHasFixedSize(true)
        pegawaiList = arrayListOf<ModelPegawai>()

        getData()

        val fabDATA_PEGAWAI_TAMBAH : FloatingActionButton = findViewById(R.id.fabDATA_PEGAWAI_TAMBAH)
        fabDATA_PEGAWAI_TAMBAH.setOnClickListener {
            val intent = Intent(this, TambahPegawaiActivity::class.java)
            intent.putExtra("judul", this.getString(R.string.tvTambahPegawai))
            intent.putExtra("idPegawai", "")
            intent.putExtra("namaPegawai", "")
            intent.putExtra("alamatPegawai", "")
            intent.putExtra("")
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init(){
        rvDATA_PEGAWAI = findViewById(R.id.rvDATA_PEGAWAI)
        fabDATA_PEGAWAI_TAMBAH = findViewById(R.id.fabDATA_PEGAWAI_TAMBAH)
    }

    fun getData() {
        val query = myRef.orderByChild("idPelanggan").limitToLast(100)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    pegawaiList.clear()
                    for (dataSnapshot in snapshot.children){
                        val pegawai = dataSnapshot.getValue(ModelPegawai::class.java)
                        pegawaiList.add(pegawai!!)
                    }
                    val adapter = DataPegawaiAdapter(pegawaiList)
                    rvDATA_PEGAWAI.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DataPegawaiActivity, error.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}