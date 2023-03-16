package com.example.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlincountries.R
import com.example.kotlincountries.adapter.CountryAdapter
import com.example.kotlincountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed_fragmentt.*


class FeedFragmentt : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private var countryAdapter = CountryAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed_fragmentt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this)[FeedViewModel::class.java]
        viewModel.refreshData()
//Rowları alt alta oluşturuyo. Yanyana olsa greed layout
        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter




        swipeRefreshLayout.setOnRefreshListener {

            countryList.visibility = View.GONE
            countryError.visibility = View.GONE
            countryLoading.visibility = View.GONE

            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->
            countries?.let {
                countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)

            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if(it){
                    countryError.visibility = View.VISIBLE
                    countryLoading.visibility = View.GONE

                }else{
                    countryError.visibility = View.GONE
                }

            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {loading ->
            loading?.let {
                if (it){
                    countryLoading.visibility = View.VISIBLE
                    countryError.visibility = View.GONE
                    countryList.visibility = View.GONE

                }else{
                    countryLoading.visibility = View.GONE
                }
            }

        })
    }


}