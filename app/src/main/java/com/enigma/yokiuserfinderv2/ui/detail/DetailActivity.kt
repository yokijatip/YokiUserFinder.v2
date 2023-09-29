package com.enigma.yokiuserfinderv2.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.enigma.yokiuserfinderv2.databinding.ActivityDetailBinding
import com.enigma.yokiuserfinderv2.model.detail.DetailViewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        // Mendapatkan username dari Intent
        val username = intent.getStringExtra(EXTRA_USERNAME)

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
