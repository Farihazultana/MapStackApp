package com.example.mapbox.view.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mapbox.databinding.LocationsBinding
import com.example.mapbox.data.model.RecordItem


class RecordAdapter: RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    private var recordsList: ArrayList<RecordItem> = arrayListOf()

    inner class RecordViewHolder(private val binding: LocationsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(recordList: RecordItem){
            Log.d("TAG_Adapter", "bind: $recordList")
            binding.myActivity = recordList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val binding: LocationsBinding = LocationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("TAG_Adapter","onCreateViewHolder: ")
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = recordsList[position]
        Log.d("TAG_Adapter", "onBindViewHolder: $record")
        holder.bind(record)
    }

    override fun getItemCount(): Int = recordsList.size

    fun addData(list: List<RecordItem>) {
        recordsList.addAll(list)
    }
}