package com.enigma.yokiuserfinderv2.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.enigma.yokiuserfinderv2.R
import com.enigma.yokiuserfinderv2.databinding.ActivityDetailBinding
import com.enigma.yokiuserfinderv2.model.detail.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.enigma.yokiuserfinderv2.adapter.PagerAdapter

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        // Mendapatkan username dari Intent
        val username = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        // Inisialisasi DetailViewModel
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        // Memanggil fungsi untuk mendapatkan data detail pengguna
        if (username != null) {
            detailViewModel.getUserDetail(username)
            showLoading(true)
        }

        // Mengamati LiveData dari DetailViewModel
        detailViewModel.fetchUserDetail().observe(this) { userDetailResponse ->
            if (userDetailResponse != null) {
                // Mengisi tampilan dengan data detail pengguna
                detailBinding.apply {
                    tvUsername.text = userDetailResponse.login
                    tvName.text = userDetailResponse.name
                    tvLocation.text = userDetailResponse.location
                    tvFollowersCount.text = userDetailResponse.followers.toString()
                    tvFollowingCount.text = userDetailResponse.following.toString()
                    Glide.with(this@DetailActivity)
                        .load(userDetailResponse.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivAvatar)
                    showLoading(false)
                }
            }
        }


        val pagerAdapter = PagerAdapter(this, supportFragmentManager, bundle)
        detailBinding.apply {
            viewPager.adapter = pagerAdapter
            tab.setupWithViewPager(viewPager)
        }

    }

    // Fungsi untuk menampilkan pesan Toast
    private fun toast(message: String) {
        Toast.makeText(this@DetailActivity, message, Toast.LENGTH_SHORT).show()
    }

    // Fungsi untuk menampilkan atau menyembunyikan loading indicator
    private fun showLoading(state: Boolean) {
        detailBinding.loadingDetail.visibility = if (state) View.VISIBLE else View.GONE
    }
}
