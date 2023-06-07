package com.example.wisata_lembah_baliem.Fragment

import android.content.Intent
import android.graphics.ColorSpace.Model
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wisata_lembah_baliem.Adapter.WisataAdapter
import com.example.wisata_lembah_baliem.DetailActivity
import com.example.wisata_lembah_baliem.Model.ModelWisata
import com.example.wisata_lembah_baliem.R
import com.example.wisata_lembah_baliem.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var wisataList: ArrayList<ModelWisata>
    lateinit var wisataAdapter: WisataAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        //Tumbnail
        binding.tumbnail.setImageResource(R.drawable.tw)



        init()

        return view
    }

    private fun init() {
        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)//LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        wisataList = ArrayList()
        addDataToList()

        wisataAdapter = WisataAdapter(wisataList)
        recyclerView.adapter = wisataAdapter

        wisataAdapter.onItemClick = {it ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("wisata", it)
            startActivity(intent)
        }
    }

    private fun addDataToList() {
        wisataList.add(ModelWisata(R.drawable.danau_habema, "Danau Habema", "Danau Habema Danau Habema Danau Habema Danau Habema Danau Habema Danau HabemaDanau Habema Danau Habema"))
        wisataList.add(ModelWisata(R.drawable.goa_nona, "Goa Nona", "Goa NonaGoa NonaGoa Nona Goa Nona Goa NonaGoa Nona Goa Nona Goa Nona Goa NonaGoa Nona"))
        wisataList.add(ModelWisata(R.drawable.pasir_putihp_egunungan, "Pasir Putih Pegunungan", "Pasir Putih PegununganPasir Putih Pegunungan Pasir Putih Pegunungan Pasir Putih ngan"))
        wisataList.add(ModelWisata(R.drawable.ladang_rumput_mei, "Ladang Rumput Mei", "Ladang Rumput MeiLadang Rumput MeiLadang Rumput Mei Ladang Rumput Mei Ladang Rumput Mei"))
        wisataList.add(ModelWisata(R.drawable.telaga_biru, "Telaga Biru","Telaga BiruTelaga BiruTelaga Biru Telaga Biru Telaga Biru Telaga BiruTelaga Biru Telaga BiruTelaga Biru"))
        wisataList.add(ModelWisata(R.drawable.side_jembatan_kuning, "Jembatan Kuning", "Jembatan KuningJembatan KuningJembatan Kuning Jembatan Kuning Jembatan Kuning Jembatan Kuning"))

        wisataList.add(ModelWisata(R.drawable.danau_habema, "Danau Habema", "Danau Habema Danau Habema Danau Habema Danau Habema Danau Habema Danau HabemaDanau Habema Danau Habema"))
        wisataList.add(ModelWisata(R.drawable.goa_nona, "Goa Nona", "Goa NonaGoa NonaGoa Nona Goa Nona Goa NonaGoa Nona Goa Nona Goa Nona Goa NonaGoa Nona"))
        wisataList.add(ModelWisata(R.drawable.pasir_putihp_egunungan, "Pasir Putih Pegunungan", "Pasir Putih PegununganPasir Putih Pegunungan Pasir Putih Pegunungan Pasir Putih ngan"))
        wisataList.add(ModelWisata(R.drawable.ladang_rumput_mei, "Ladang Rumput Mei", "Ladang Rumput MeiLadang Rumput MeiLadang Rumput Mei Ladang Rumput Mei Ladang Rumput Mei"))
        wisataList.add(ModelWisata(R.drawable.telaga_biru, "Telaga Biru","Telaga BiruTelaga BiruTelaga Biru Telaga Biru Telaga Biru Telaga BiruTelaga Biru Telaga BiruTelaga Biru"))
        wisataList.add(ModelWisata(R.drawable.side_jembatan_kuning, "Jembatan Kuning", "Jembatan KuningJembatan KuningJembatan Kuning Jembatan Kuning Jembatan Kuning Jembatan Kuning"))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}