/**
 * Created by Fandly on 30/3/2020.
 * Made With Love
 */

package com.fandlys.covid19.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fandlys.covid19.R
import com.fandlys.covid19.adapter.CaseAdapter
import com.fandlys.covid19.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.fragment_case.*

class CaseFragment : Fragment() {
    private lateinit var adapter: CaseAdapter
    private lateinit var viewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_case, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = CaseAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DataViewModel::class.java)
        viewModel.setDataPerCase()
        viewModel.getDataPerCase().observe(this, Observer { listDataPerCase->
            if (listDataPerCase != null){
                adapter.setData(listDataPerCase)
                rv_case.layoutManager = LinearLayoutManager(context)
                rv_case.adapter = adapter
            }
        })
    }

}
