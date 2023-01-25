package com.example.mapbox.view.main.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.mapbox.R
import com.example.mapbox.data.api.RetrofitHelper
import com.example.mapbox.view.adapter.RecordAdapter
import com.example.mapbox.databinding.ActivityMainBinding
import com.example.mapbox.data.model.RecordItem
import com.example.mapbox.data.repository.MainRepository
import com.example.mapbox.viewmodel.MainActivityViewModel
import com.example.mapbox.viewmodel.countryViewModel


class MainActivity : AppCompatActivity() {



    private val viewModel: MainActivityViewModel by viewModels()
    private val countiesViewModel: countryViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    var recordAdapter: RecordAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainRepository = MainRepository(RetrofitHelper)
        val recyclerView = binding.rvLocations


//        val viewModel: MainActivityViewModel by viewModels()
//        viewModel.getRecordList().observe(this, Observer<List<RecordItem>> { records ->  })
//        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        setupUI(recyclerView)
        setupObserver(recyclerView)

      //  binding.rvLocations.setOnClickListener { showInMap() }

        //val recordsApi = RetrofitHelper.getInstance().create(API::class.java)
        // launching a new coroutine
        /*GlobalScope.launch {
            //val result = recordsApi.getRecords()
            if (viewModel.getRecordList() != null) {
                //Log.d("TAG_DATA", result.body()?.record.toString())
                val recordList = viewModel.getRecordList().toString()
                recordAdapter = RecordAdapter(recordList as List<RecordItem>)

                runOnUiThread(Runnable { setupUI() })

            }
        }*/

    }

    private fun setupUI(recyclerView: RecyclerView) {
        Log.d("TAG_DATA", "setupUI")

        recordAdapter = RecordAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = recordAdapter
        recyclerView.setHasFixedSize(true)
    }

   /* private fun showInMap(){
        val country = binding.rvLocations.toString()
        viewModel.country_name = country
    }*/

    private fun setupObserver(recyclerView: RecyclerView) {

        viewModel.getRecordList().observe(this, Observer { recordItemList ->
            Log.d("TAG_DATA", "setupObserver: "+recordItemList)
            if (recordItemList == null) {
                binding.progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                renderList(recordItemList)

            }
        })
    }

    private fun renderList(records: List<RecordItem>) {
        recordAdapter?.addData(records)
        recordAdapter?.notifyDataSetChanged()
    }

    companion object{
        var mainRepository: MainRepository? = null
    }

}