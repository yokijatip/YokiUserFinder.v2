package com.enigma.yokiuserfinderv2.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.enigma.yokiuserfinderv2.data.remote.response.UserData
import com.enigma.yokiuserfinderv2.databinding.ItemHomeUserBinding
import com.enigma.yokiuserfinderv2.ui.detail.DetailActivity
import com.enigma.yokiuserfinderv2.ui.search.SearchActivity

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.UserViewHolder>() {

    private val list = ArrayList<UserData>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    //    Setter
    @SuppressLint("NotifyDataSerChanged", "NotifyDataSetChanged")
    fun setList(newList: List<UserData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemHomeUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: UserData) {

            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(userData)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(userData.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivAvatar)
                tvUsername.text = userData.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemHomeUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    //    Interface buat on item click listener
    interface OnItemClickCallback {
        fun onItemClicked(userData: UserData)
    }

}