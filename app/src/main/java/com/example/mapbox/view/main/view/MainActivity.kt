package com.example.mapbox.view.main.view


import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mapbox.R
import com.example.mapbox.data.api.RetrofitHelper
import com.example.mapbox.data.model.CountriesDataBase
import com.example.mapbox.data.model.CountryModel
import com.example.mapbox.view.adapter.RecordAdapter
import com.example.mapbox.databinding.ActivityMainBinding
import com.example.mapbox.data.repository.MainRepository
import com.example.mapbox.viewmodel.MainActivityViewModel
import android.os.Bundle as Bundle


class MainActivity : AppCompatActivity(), RecordAdapter.CellClickListener {
    companion object {
        var mainRepository: MainRepository? = null
        var countriesDataBase: CountriesDataBase? = null
        var countryModel: CountryModel? = null

    }
    var binding: ActivityMainBinding? = null
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var recordAdapter: RecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        countriesDataBase = CountriesDataBase.getDatabase(this)
        mainRepository = MainRepository(this,RetrofitHelper)
        val recyclerView = binding?.rvLocations

        setupUI(recyclerView)
        setupObserver(recyclerView)
    }


    private fun setupUI(recyclerView: RecyclerView?) {
        Log.d("TAG_DATA", "setupUI")

        recordAdapter = RecordAdapter(this)
        recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView?.adapter = recordAdapter
        recyclerView?.setHasFixedSize(true)
    }

    private fun setupObserver(recyclerView: RecyclerView?) {

        viewModel.getRecordList()?.observe(this, Observer { recordItemList ->
            Log.d("TAG_DATA", "setupObserver: " + recordItemList)
            if (recordItemList == null) {
                binding?.progressBar?.visibility = View.VISIBLE
                recyclerView?.visibility = View.GONE
            } else {
                binding?.progressBar?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
                renderList(recordItemList)
            }
        })
    }

    private fun renderList(records: List<CountryModel>) {
        recordAdapter.addData(records)
        recordAdapter.notifyDataSetChanged()
    }

    override fun onCellClickListener(bundle: Bundle) {
        Log.d("mapBundle", "onCellClickListener: ")
        val mapFragment = MapFragment()
        mapFragment.arguments = bundle
        mapFragment.show(supportFragmentManager, "BottomSheet")
    }

}

