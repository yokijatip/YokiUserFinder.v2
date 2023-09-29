package com.enigma.yokiuserfinderv2.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enigma.yokiuserfinderv2.R
import com.enigma.yokiuserfinderv2.adapter.HomeAdapter
import com.enigma.yokiuserfinderv2.data.remote.response.UserData
import com.enigma.yokiuserfinderv2.databinding.ActivityHomeBinding
import com.enigma.yokiuserfinderv2.model.home.HomeViewModel
import com.enigma.yokiuserfinderv2.ui.detail.DetailActivity
import com.enigma.yokiuserfinderv2.ui.search.SearchActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var homeRecyclerView: RecyclerView
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

//        Fungsi setup Recycler View
        homeRecyclerView()

//        Fungsi Toolbar
        toolbar()

//        Fungsi Toolbar
        floatingSearchButton()

//        Kondisi Awal Loading kalo true maka muncul
        showLoading(true)

//        Inisialisasi Home View Model
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]


        val homeAdapter = HomeAdapter()
        homeRecyclerView.adapter = homeAdapter
        homeRecyclerView.layoutManager = LinearLayoutManager(this)

//        oberserver menggunakan Live data
        homeViewModel.getUserListLiveData().observe(this) { userList ->
            homeAdapter.setList(userList)
            showLoading(false) //   Loading ini akan menjadi false dan akan menghilang ketika datanya sudah muncul
        }

//        Handler buat On Item Click
        homeAdapter.setOnItemClickCallback(object : HomeAdapter.OnItemClickCallback {
            override fun onItemClicked(userData: UserData) {
                // mengarah ke halaman detail
                navigateToUserDetail(userData)
            }
        })

    }

    //    Inisiasi Fungsi Floating Action Button
    private fun floatingSearchButton() {
        homeBinding.apply {
            fabSearch.setOnClickListener {
                toast("Masukin namanya harus kumplit 😛")
                startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
            }
        }
    }

//    Direct ke halaman Detail User
    private fun navigateToUserDetail(userData: UserData) {
        val intent = Intent(this@HomeActivity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_USERNAME, userData.login)
        }
        startActivity(intent)
    }

    //    Inisiasi Fungsi Toolbar
    private fun toolbar() {
        homeBinding.apply {
            toolbar.btnFavorite.setOnClickListener {
                toast("Ini buat halaman favorit")
            }
        }
    }

    //    Inisiasi Fungsi Buat Toast Message
    private fun toast(pesan: String) {
        Toast.makeText(this@HomeActivity, pesan, Toast.LENGTH_SHORT).show()
    }

    //    Inisiasi Fungsi Buat Recycler View
    private fun homeRecyclerView() {
        homeRecyclerView = findViewById(R.id.rv_home)
        homeRecyclerView.setHasFixedSize(true)
        homeRecyclerView.layoutManager = LinearLayoutManager(this)
    }

//    Inisiasi Fungsi ShowLoading
    private fun showLoading(state: Boolean) {
        homeBinding.loadingHome.visibility = if (state) View.VISIBLE else View.GONE
    }

}