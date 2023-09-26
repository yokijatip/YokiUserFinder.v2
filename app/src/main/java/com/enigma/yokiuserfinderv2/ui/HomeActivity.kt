package com.enigma.yokiuserfinderv2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.enigma.yokiuserfinderv2.R
import com.enigma.yokiuserfinderv2.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        toolbar()
    }

    private fun toolbar() {
        homeBinding.apply {
            toolbar.btnFavorite.setOnClickListener {
                Toast.makeText(this@HomeActivity, "Ini untuk Halaman Favorit", Toast.LENGTH_SHORT).show()
            }
        }
    }

}