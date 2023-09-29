package com.enigma.yokiuserfinderv2.ui.following

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
import com.enigma.yokiuserfinderv2.model.followers.FollowingViewModel
import com.enigma.yokiuserfinderv2.ui.detail.DetailActivity

class FollowingFragment : Fragment(R.layout.fragment_followers) {

    private var _binding: FragmentFollowersBinding? = null
    private val followingBinding get() = _binding!!

    private lateinit var followingAdapter: HomeAdapter
    private lateinit var followingViewModel: FollowingViewModel
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
        followingAdapter = HomeAdapter()
        followingAdapter.notifyDataSetChanged()

        followingBinding.rvFollowers.setHasFixedSize(true)
        followingBinding.rvFollowers.layoutManager = LinearLayoutManager(activity)
        followingBinding.rvFollowers.adapter = followingAdapter

//        Kondisi Awal Loading
        showLoading(true)

//        View Model dari ini di inisialisasi pake view Model Provider
        followingViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FollowingViewModel::class.java]

//        Memanggil fungsi get followers
        followingViewModel.getFollowing(username)

//        Observer
        followingViewModel.getListFollowing().observe(viewLifecycleOwner) {
            if (it != null) {
                // Mengupdate daftar followers pada adapter dan menghilangkan loading indicator
                followingAdapter.setList(it)
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
        followingBinding.loadingFollowers.visibility = if (state) View.VISIBLE else View.GONE
    }
}