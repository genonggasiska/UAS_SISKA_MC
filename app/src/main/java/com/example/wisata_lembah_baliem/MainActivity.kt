package com.example.wisata_lembah_baliem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.wisata_lembah_baliem.Adapter.HorizontalRVAdapter
import com.example.wisata_lembah_baliem.Adapter.ViewPagerAdapter
import com.example.wisata_lembah_baliem.Fragment.HomeFragment
import com.example.wisata_lembah_baliem.Fragment.ProfileFragment
import com.example.wisata_lembah_baliem.Fragment.SearchFragment
import com.example.wisata_lembah_baliem.Fragment.VaforiteFragment
import com.example.wisata_lembah_baliem.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTab()

    }

    private fun setupTab() {

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "")
        adapter.addFragment(VaforiteFragment(), "")
        adapter.addFragment(ProfileFragment(), "")
        adapter.addFragment(SearchFragment(), "")

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_favorite)
        binding.tabs.getTabAt(2)!!.setIcon(R.drawable.ic_person)
        binding.tabs.getTabAt(3)!!.setIcon(R.drawable.ic_search)
    }


}
