package com.viona.laundry2.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.viona.laundry2.Pegawai.TambahPegawaiActivity
import com.viona.laundry2.R
import com.viona.laundry2.modeldata.ModelPegawai

class DataPegawaiAdapter (
    private val listPegawai: List<ModelPegawai>) : RecyclerView.Adapter<DataPegawaiAdapter.ViewHolder>() {
    lateinit var appContext: Context
    lateinit var databaseReference: DatabaseReference

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_datapegawai, parent, false)
        appContext = parent.context
        databaseReference = FirebaseDatabase.getInstance().getReference("pegawai")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPegawai[position]
        holder.tvIdPegawai.text = item.idPegawai ?: "Tidak ada ID"
        holder.tvcardNamaPegawai.text = item.namaPegawai
        holder.tvcardAlamatPegawai.text = item.alamatPegawai
        holder.tvcardNoHPPegawai.text = item.noHPPegawai
        holder.tvcardTerdaftarPegawai.text = item.terdaftar

        holder.cvDataPegawai.setOnClickListener {
            val intent = Intent(appContext, TambahPegawaiActivity::class.java)
            intent.putExtra("judul", "Edit Pegawai")
            intent.putExtra("idPegawai", item.idPegawai)
            intent.putExtra("namaPegawai", item.namaPegawai)
            intent.putExtra("noHPPegawai", item.noHPPegawai)
            intent.putExtra("alamatPegawai", item.alamatPegawai)
            intent.putExtra("idCabang", item.idCabangPegawai)
            appContext.startActivity(intent)
        }
        holder.btHubungiPegawai.setOnClickListener {

        }
        holder.btLihatPegawai.setOnClickListener {
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvDataPegawai = itemView.findViewById<CardView>(R.id.cvDataPegawai)
        val tvIdPegawai = itemView.findViewById<TextView>(R.id.tvIdPegawai)
        val tvcardNamaPegawai = itemView.findViewById<TextView>(R.id.tvcardNamaPegawai)
        val tvcardNoHPPegawai = itemView.findViewById<TextView>(R.id.tvNoHP)
        val tvcardTerdaftarPegawai = itemView.findViewById<TextView>(R.id.tvcardTerdaftarPegawai)
        val tvcardAlamatPegawai = itemView.findViewById<TextView>(R.id.tvcardAlamatPegawai)
        val btHubungiPegawai = itemView.findViewById<Button>(R.id.btHubungiPegawai)
        val btLihatPegawai = itemView.findViewById<Button>(R.id.btLihatPegawai)
    }
    override fun getItemCount(): Int {
        return listPegawai.size
    }
}