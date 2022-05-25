package com.dzakwan.kisahnabiapp_kotlin_.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dzakwan.kisahnabiapp_kotlin_.R
import com.dzakwan.kisahnabiapp_kotlin_.data.KisahResponse
import com.dzakwan.kisahnabiapp_kotlin_.databinding.ActivityMainBinding
import com.dzakwan.kisahnabiapp_kotlin_.ui.detail.DetailActivity
import com.dzakwan.kisahnabiapp_kotlin_.ui.detail.KisahAdapter
import com.dzakwan.utils.OnItemClickCallback

class MainActivity : AppCompatActivity(), OnItemClickCallback {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _viewModel : MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getData()

        viewModel.kisahResponse.observe(this){
            showData(it)
        }

        viewModel.ifLoading.observe(this){
            showLoading(it)
        }

        viewModel.ifError.observe(this){
            showError(it)
        }


    }
    private fun showData(data: List<KisahResponse>?) {
        binding.recyclerMain.apply {
            val mAdapter = KisahAdapter()
            mAdapter.setData(data)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = mAdapter
            mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun onItemClicked(item: KisahResponse) {
                    startActivity(Intent(applicationContext, DetailActivity::class.java).putExtra("extra_data",item))
                }
            })
        }
    }


    private fun showLoading(ifLoading: Boolean?) {
        if (ifLoading == true) {
            binding.progressMain.visibility = View.VISIBLE
            binding.recyclerMain.visibility = View.INVISIBLE
        }
        else {
            binding.progressMain.visibility = View.INVISIBLE
            binding.recyclerMain.visibility = View.VISIBLE
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError: $error", )
    }

}