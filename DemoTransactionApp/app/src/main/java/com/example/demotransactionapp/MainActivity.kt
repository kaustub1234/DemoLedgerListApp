package com.example.demotransactionapp

import TransactionItem
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demotransactionapp.databinding.ActivityMainBinding
import com.example.demotransactionapp.adapter.MidAdapter
import com.example.ledger.viewmodel.LedgerViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: LedgerViewModel by viewModels()
    private val adapter = MidAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerMain.adapter = adapter

        viewModel.midGroups.observe(this) {
            adapter.update(it)
        }

        val sampleData = listOf(
            TransactionItem(1, 123456797, 12.32, 11234684654),
            TransactionItem(2, 123456794, 12.32, 11234684654),
            TransactionItem(3, 123456791, 12.32, 11234684654),
            TransactionItem(3, 123456791, 12.32, 11234684654),
            TransactionItem(1, 123456797, 12.32, 11234684654),
            TransactionItem(2, 123456794, 12.32, 11234684654),
            TransactionItem(1, 123456797, 12.32, 11234684654),
            TransactionItem(3, 123456791, 12.32, 11234684654),
            TransactionItem(1, 123456795, 12.32, 11234684654),
            TransactionItem(1, 123456795, 12.32, 11234684654),
            TransactionItem(3, 123456791, 12.32, 11234684654)
        )

        viewModel.loadData(sampleData)
    }
}