package com.viona.laundry2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
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

    }

    override fun getItemCount(): Int {
        return listPelanggan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCARD = itemView.findViewById<View>(R.id.cvDataPelanggan)
        val tvID = itemView.findViewById<View>(R.id.cvTextIdPelanggan)
        val tvNama = itemView.findViewById<View>(R.id.NamaPelanggan)
        val tvNoHP = itemView.findViewById<View>(R.id.tvNoHP)
        val tvTerdaftar = itemView.findViewById<View>(R.id.tvTerdaftar)
        val tvAlamat = itemView.findViewById<View>(R.id.tvAlamat)
    }

}