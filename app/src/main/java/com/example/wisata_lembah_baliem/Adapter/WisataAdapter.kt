package com.example.wisata_lembah_baliem.Adapter

import android.graphics.ColorSpace.Model
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wisata_lembah_baliem.Model.ModelWisata
import com.example.wisata_lembah_baliem.R

class WisataAdapter(private val wisataList:ArrayList<ModelWisata>) :
    RecyclerView.Adapter<WisataAdapter.WisataViewHolder>(){

    var onItemClick : ((ModelWisata) -> Unit)? = null

    class WisataViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambar: ImageView = itemView.findViewById(R.id.img_wisata)
        val title: TextView = itemView.findViewById(R.id.nama_wisata)
        val desc: TextView = itemView.findViewById(R.id.desc_wisata)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wisata, parent, false)
        return WisataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wisataList.size
    }

    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {
        val wisata = wisataList[position]
        holder.gambar.setImageResource(wisata.gambarWisata)
        holder.title.text = wisata.namaWisata
        holder.desc.text = wisata.descWisata

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(wisata)
        }
    }
}