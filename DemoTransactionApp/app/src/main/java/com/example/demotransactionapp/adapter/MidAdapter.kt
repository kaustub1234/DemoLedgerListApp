package com.example.demotransactionapp.adapter

import MidGroup
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demotransactionapp.R
import com.example.demotransactionapp.databinding.ItemMidBinding


class MidAdapter(var context: Context) : RecyclerView.Adapter<MidAdapter.MidViewHolder>() {

    var items: List<MidGroup> = listOf()

    fun update(newItems: List<MidGroup>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class MidViewHolder(private val binding: ItemMidBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(midGroup: MidGroup) {
            binding.tvMid.text = "Mid : ${midGroup.mid}"
            val tidAdapter = TidAdapter(context)
            tidAdapter.update(midGroup.tidGroups)
            binding.recyclerTid.adapter = tidAdapter
            binding.recyclerTid.layoutManager = LinearLayoutManager(context)

            binding.recyclerTid.isVisible = midGroup.isExpanded

            if (midGroup.isExpanded)
                binding.arrow.setImageResource(R.drawable.arrow_up)
            else
                binding.arrow.setImageResource(R.drawable.arrow_down)

            binding.root.setOnClickListener {
                midGroup.isExpanded = !midGroup.isExpanded
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MidViewHolder {
        val binding = ItemMidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MidViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}