package com.enigma.yokiuserfinderv2.ui.search

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigma.yokiuserfinderv2.R
import com.enigma.yokiuserfinderv2.adapter.SearchAdapter
import com.enigma.yokiuserfinderv2.data.remote.response.UserData
import com.enigma.yokiuserfinderv2.databinding.ActivitySearchBinding
import com.enigma.yokiuserfinderv2.model.search.SearchViewModel
import com.enigma.yokiuserfinderv2.ui.detail.DetailActivity
import java.security.Key

class SearchActivity : AppCompatActivity() {

    private lateinit var searchBinding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter: SearchAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(searchBinding.root)



//        Inisiasi View Model Provider
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

//        Inisiasi Adapter
        searchAdapter = SearchAdapter()
        searchAdapter.notifyDataSetChanged()

        // On item click call back biar bisa nge klik setiap item yang ada di recycler view
        searchAdapter.setOnItemClickCallback(object : SearchAdapter.OnItemClickCallback {
            override fun onItemClicked(userData: UserData) {
                // mengarah ke halaman detail
                navigateToUserDetail(userData)
            }
        })

//        Fungsi buat Recycler View
        setupRecyclerView()

//        Setup Search Action
        setupSearchActions()

//        Running Observer
        observeUserSearchResults()

    }

//    Fungsi Observer
    private fun observeUserSearchResults() {
        searchViewModel.getSearchUsers().observe(this) {
            if (it != null) {
                searchAdapter.setList(it)
                showLoading(false)
            }
        }
    }

    //    Set Recycler view
    private fun setupRecyclerView() {
        searchBinding.rvSearch.layoutManager = LinearLayoutManager(this@SearchActivity)
        searchBinding.rvSearch.setHasFixedSize(true)
        searchBinding.rvSearch.adapter = searchAdapter
    }

    //    Action buat di search
    private fun setupSearchActions() {

        searchBinding.searchField.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchUser()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

    }

    //    Fungsi Query dan buat nangkap apa  yang di ketik di search field
    private fun searchUser() {
        val query = searchBinding.searchField.text.toString()
        toast(query)
        if (query.isEmpty()) return
        showLoading(true)
        searchViewModel.setSearchUsers(query)
    }

    //    Show Loading
    private fun showLoading(state: Boolean) {
        searchBinding.loadingSearch.visibility = if (state) View.VISIBLE else View.GONE
    }

    //    fungsi buat navigasi ke detail user ketika setiap item nya di klik
    private fun navigateToUserDetail(userData: UserData) {
        val intent = Intent(this@SearchActivity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_USERNAME, userData.login)
        }
        startActivity(intent)
    }

    //    Toast
    private fun toast(pesan: String) {
        Toast.makeText(this@SearchActivity, pesan, Toast.LENGTH_SHORT).show()
    }

}