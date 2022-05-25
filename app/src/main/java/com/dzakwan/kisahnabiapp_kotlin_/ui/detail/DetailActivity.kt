package com.dzakwan.kisahnabiapp_kotlin_.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dzakwan.kisahnabiapp_kotlin_.R
import com.dzakwan.kisahnabiapp_kotlin_.data.KisahResponse
import com.dzakwan.kisahnabiapp_kotlin_.databinding.ActivityDetailBinding
import com.dzakwan.kisahnabiapp_kotlin_.ui.MainViewModel

class DetailActivity : AppCompatActivity() {
    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<KisahResponse>(EXTRA_DATA)
        data?.let {
            binding.apply {

                Glide.with(detailImage.context)
                    .load(data.imageUrl)
                    .apply(RequestOptions())
                    .override(500, 500)
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(detailImage)


                detailDesk.text = data.description
                detailNama.text = data.name
                detailTahun.text = data.thnKelahiran
                detailUsia.text = data.usia
                detailTempat.text = data.tmp

            }
        }

    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
