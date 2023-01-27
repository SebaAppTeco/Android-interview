package com.sebapp.challengeteco.ui.characterList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebapp.challengeteco.R
import com.sebapp.challengeteco.data.model.CharacterData
import com.sebapp.challengeteco.databinding.ItemRowBinding

class RecyclerViewAdapter() : PagingDataAdapter<CharacterData, RecyclerViewAdapter.MyViewHolder>(
    DiffUtilCallBack()
) {
    private lateinit var context: Context
    private var onItemClickListener: ((CharacterData) -> Unit)? = null

    fun setOnItemClickListener(listener: (CharacterData) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val inflater = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return MyViewHolder(inflater)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRowBinding.bind(view)
        private var currentItem: CharacterData? = null

        init {
            binding.itemList.setOnClickListener {
                currentItem?.let { item ->
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }

        fun bind(data: CharacterData) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            currentItem = data
            binding.textViewName.text = data.name
            binding.textViewGender.text = data.gender
            Glide.with(binding.imageView)
                .load(data.image)
                .centerCrop()
                .into(binding.imageView)
            itemView.startAnimation(animation)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.species == newItem.species
        }
    }
}
