package com.droid.newsclient.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.droid.newsapiclient.databinding.LayoutTechSourceItemBinding
import com.droid.newsclient.data.model.sources.Source

class SourcesAdapter : RecyclerView.Adapter<SourcesAdapter.SourcesViewHolder>() {
    inner class SourcesViewHolder(
            val binding: LayoutTechSourceItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(source: Source) {
            with(binding) {
                name.text = source.name
                country.text = source.country
                category.text = source.category
                description.text = source.description

                binding.root.setOnClickListener {
                    onItemClickListener?.let { it(source) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesViewHolder {
        val view = LayoutTechSourceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SourcesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        val source = differ.currentList[position]
        holder.bind(source)
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Source>() {
        override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Source) -> Unit)? = null

    fun setOnItemClickListener(listener: (Source) -> Unit) {
        onItemClickListener = listener
    }


}