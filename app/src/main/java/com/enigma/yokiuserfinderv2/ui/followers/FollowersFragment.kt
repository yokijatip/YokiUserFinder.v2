package com.enigma.yokiuserfinderv2.ui.followers

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enigma.yokiuserfinderv2.R
import com.enigma.yokiuserfinderv2.adapter.HomeAdapter
import com.enigma.yokiuserfinderv2.databinding.FragmentFollowersBinding
import com.enigma.yokiuserfinderv2.model.followers.FollowersViewModel
import com.enigma.yokiuserfinderv2.ui.detail.DetailActivity

class FollowersFragment : Fragment(R.layout.fragment_followers) {

    private var _binding: FragmentFollowersBinding? = null
    private val followBinding get() = _binding!!

    private lateinit var followersAdapter: HomeAdapter
    private lateinit var followersViewModel: FollowersViewModel
    private lateinit var username: String

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Get Username dari argument
        val argument = arguments
        username = argument?.getString(DetailActivity.EXTRA_USERNAME).toString()

//        inisiasi binding buat tampilan fragment
        _binding = FragmentFollowersBinding.bind(view)

//        Inisialisasi Adapter
        followersAdapter = HomeAdapter()
        followersAdapter.notifyDataSetChanged()
        followBinding.rvFollowers.setHasFixedSize(true)
        followBinding.rvFollowers.layoutManager = LinearLayoutManager(activity)
        followBinding.rvFollowers.adapter = followersAdapter

//        Kondisi Awal Loading
        showLoading(true)

//        View Model dari ini di inisialisasi pake view Model Provider
        followersViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FollowersViewModel::class.java]

//        Memanggil fungsi get followers
        followersViewModel.getFollower(username)

//        Observer
        followersViewModel.getListFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                // Mengupdate daftar followers pada adapter dan menghilangkan loading indicator
                followersAdapter.setList(it)
//                Kalo datanya sudah ada fungsi show loading berubah nilai nya menjadi false dan loading akan dihilangkan atau disembunyikan
                showLoading(false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //    Inisiasi Fungsi ShowLoading
    private fun showLoading(state: Boolean) {
        followBinding.loadingFollowers.visibility = if (state) View.VISIBLE else View.GONE
    }
}