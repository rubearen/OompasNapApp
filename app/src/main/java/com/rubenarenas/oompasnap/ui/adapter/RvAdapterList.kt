package com.rubenarenas.oompasnap.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rubenarenas.oompasnap.R
import com.rubenarenas.oompasnap.databinding.OompaCardViewBinding
import com.rubenarenas.oompasnap.domain.entities.OompaMain
import com.rubenarenas.oompasnap.ui.OompaDetailsFragment


class RvAdapterList : ListAdapter<OompaMain, RvAdapterList.ItemViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = DataBindingUtil.inflate<OompaCardViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.oompa_card_view,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(val binding: OompaCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OompaMain) = with(itemView) {

            binding.oompa = item
            binding.executePendingBindings()

            setOnClickListener {
                val activity = it.getContext() as AppCompatActivity
                val myFragment: Fragment = OompaDetailsFragment(item.id)
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.conslayout, myFragment).addToBackStack(null).commit()
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<OompaMain>() {
    override fun areItemsTheSame(oldItem: OompaMain, newItem: OompaMain): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OompaMain, newItem: OompaMain): Boolean {
        return oldItem.id == newItem.id
    }
}