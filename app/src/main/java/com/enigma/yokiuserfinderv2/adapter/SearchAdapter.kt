package com.enigma.yokiuserfinderv2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.enigma.yokiuserfinderv2.data.remote.response.UserData
import com.enigma.yokiuserfinderv2.databinding.ItemHomeUserBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val list = ArrayList<UserData>()
    private var onItemClickCallback: HomeAdapter.OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        onItemClickCallback.also { this.onItemClickCallback = it }
    }

    @SuppressLint("NotifyDataSerChanged", "NotifyDataSetChanged")
    fun setList(newList: List<UserData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(private val binding: ItemHomeUserBinding) :
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val view = ItemHomeUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder((view))
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickCallback : HomeAdapter.OnItemClickCallback {
        override fun onItemClicked(userData: UserData)
    }

}