package com.example.task19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task19.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = MsgAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener{
            Intent(this, RequestService::class.java).also{
                startService(it)
            }
        }

        binding.btnStop.setOnClickListener{
            Intent(this, RequestService::class.java).also{
                stopService(it)
            }
        }

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter

        binding.btnInfo.setOnClickListener {
            if (!MsgAdapter.getList().isNullOrEmpty()) {
                for (msg in MsgAdapter.getList()!!) {
                    adapter.addMsg(msg)
                }
            }
            else{
                Log.d("MsgList", "MsgList is empty")
            }
        }
    }
}