package com.example.demotransactionapp.adapter

import TransactionItem
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demotransactionapp.databinding.ItemTxnBinding


class TransactionAdapter(var context: Context) : RecyclerView.Adapter<TransactionAdapter.TxnViewHolder>() {

    private var txns = listOf<TransactionItem>()

    fun update(data: List<TransactionItem>) {
        txns = data
        notifyDataSetChanged()
    }

    inner class TxnViewHolder(private val binding: ItemTxnBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TransactionItem) {
            binding.tvAmount.text = "Amount : ${item.amount}"
            binding.tvNarration.text = "Narration : ${item.narration}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TxnViewHolder {
        val binding = ItemTxnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TxnViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TxnViewHolder, position: Int) {
        holder.bind(txns[position])
    }

    override fun getItemCount(): Int = txns.size
}