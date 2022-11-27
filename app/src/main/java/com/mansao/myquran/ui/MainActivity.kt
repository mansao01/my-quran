package com.mansao.myquran.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mansao.myquran.adapter.ListQuranAdapter
import com.mansao.myquran.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: ListQuranAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListQuranAdapter()
        binding.apply {
            rvQuran.layoutManager = LinearLayoutManager(this@MainActivity)
            rvQuran.setHasFixedSize(true)
            rvQuran.adapter = adapter
        }

        getAllSurah()

    }

    private fun getAllSurah() {
        viewModel.apply {
            getAllSurah()
            listQuran.observe(this@MainActivity) {
                adapter.setListQuran(it)
            }
            isLoading.observe(this@MainActivity) {
                showProgressbar(it)
            }
        }
    }

    private fun showProgressbar(state: Boolean) {
        binding.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.GONE

            }
        }
    }
}