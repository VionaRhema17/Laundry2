package com.viona.laundry2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.viona.laundry2.R
import com.viona.laundry2.modeldata.ModelPelanggan

class DataPelangganAdapter (
    private val listPelanggan: ArrayList<ModelPelanggan>) :
    RecyclerView.Adapter<DataPelangganAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_datapelanggan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = listPelanggan[position]
        holder.tvIdPelanggan.text = item.idPelanggan ?: "Tidak ada ID"
        holder.NamaPelanggan.text = item.namaPelanggan
        holder.tvAlamat.text = item.alamatPelanggan
        holder.tvNoHP.text = item.noHPPelanggan
        holder.tvTerdaftar.text = item.terdaftar

        holder.cvCARD.setOnClickListener {

        }
        holder.btHubungi.setOnClickListener {

        }
        holder.btLihat.setOnClickListener {
        }

    }

    override fun getItemCount(): Int {
        return listPelanggan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCARD = itemView.findViewById<CardView>(R.id.cvDataPelanggan)
        val tvIdPelanggan = itemView.findViewById<TextView>(R.id.tvIdPelanggan)
        val NamaPelanggan = itemView.findViewById<TextView>(R.id.NamaPelanggan)
        val tvNoHP = itemView.findViewById<TextView>(R.id.tvNoHP)
        val tvTerdaftar = itemView.findViewById<TextView>(R.id.tvTerdaftar)
        val tvAlamat = itemView.findViewById<TextView>(R.id.tvAlamat)
        val btHubungi = itemView.findViewById<Button>(R.id.btHubungi)
        val btLihat = itemView.findViewById<Button>(R.id.btLihat)
    }

}