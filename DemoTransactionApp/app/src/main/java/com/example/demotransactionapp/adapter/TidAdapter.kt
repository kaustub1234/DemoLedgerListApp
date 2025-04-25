package com.example.demotransactionapp.adapter

import TidGroup
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demotransactionapp.R
import com.example.demotransactionapp.databinding.ItemTidBinding

class TidAdapter(var context: Context) : RecyclerView.Adapter<TidAdapter.TidViewHolder>() {

    private var items: List<TidGroup> = listOf()

    fun update(data: List<TidGroup>) {
        items = data
        notifyDataSetChanged()
    }

    inner class TidViewHolder(private val binding: ItemTidBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tidGroup: TidGroup) {
            binding.tvTid.text = "Tid: ${tidGroup.tid}"
            val txnAdapter = TransactionAdapter(context)
            txnAdapter.update(tidGroup.items)
            binding.recyclerTxn.adapter = txnAdapter
            binding.recyclerTxn.layoutManager = LinearLayoutManager(context)
            binding.recyclerTxn.isVisible = tidGroup.isExpanded

            if (tidGroup.isExpanded)
                binding.arrow.setImageResource(R.drawable.arrow_up)
            else
                binding.arrow.setImageResource(R.drawable.arrow_down)

            binding.root.setOnClickListener {
                tidGroup.isExpanded = !tidGroup.isExpanded
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TidViewHolder {
        val binding = ItemTidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TidViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}