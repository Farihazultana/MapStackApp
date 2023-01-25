package com.example.mapbox.view.adapter


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mapbox.data.model.CountryModel
import com.example.mapbox.databinding.LocationsBinding


class RecordAdapter(private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    private var recordsList: ArrayList<CountryModel> = arrayListOf()

    inner class RecordViewHolder(private val binding: LocationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recordList: CountryModel) {
            Log.d("TAG_Adapter", "bind: $recordList")
            binding.myActivity = recordList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val binding: LocationsBinding =
            LocationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("TAG_Adapter", "onCreateViewHolder: ")
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = recordsList[position]
        Log.d("TAG_Adapter", "onBindViewHolder: $record")
        holder.bind(record)

        holder.itemView.setOnClickListener {
            val mapBundle = Bundle()
            mapBundle.putString("Location", record.location)
            record.lat?.let { it1 -> mapBundle.putDouble("Lat", it1) }
            record.longitude?.let { it1 -> mapBundle.putDouble("Long", it1) }

            Log.d("mapBundle", "Location: " + mapBundle)
            Log.d("mapBundle", "listener: " + cellClickListener)

            cellClickListener?.onCellClickListener(mapBundle)
        }
    }

    override fun getItemCount(): Int = recordsList.size

    fun addData(list: List<CountryModel>) {
        recordsList.clear()
        recordsList.addAll(list)
        Log.d("TAG_DATA", "setupObserver: " + recordsList.size)
    }

    interface CellClickListener {
        fun onCellClickListener(bundle: Bundle)
    }


}