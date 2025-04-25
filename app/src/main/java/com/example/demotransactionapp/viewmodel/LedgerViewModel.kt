package com.example.ledger.viewmodel

import MidGroup
import TidGroup
import TransactionItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LedgerViewModel : ViewModel() {
    private val _midGroups = MutableLiveData<List<MidGroup>>()
    val midGroups: LiveData<List<MidGroup>> = _midGroups

    fun loadData(transactions: List<TransactionItem>) {
        val grouped = transactions.groupBy { it.mid }.map { (mid, midItems) ->
            val tidGroups = midItems.groupBy { it.tid }.map { (tid, tidItems) ->
                TidGroup(tid, tidItems)
            }.sortedBy { it.tid }
            MidGroup(mid, tidGroups)
        }.sortedBy { it.mid }
        _midGroups.value = grouped
    }
}