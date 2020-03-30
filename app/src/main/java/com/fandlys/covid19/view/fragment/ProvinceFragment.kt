/**
 * Created by Fandly on 30/3/2020.
 * Made With Love
 */

package com.fandlys.covid19.view.fragment

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fandlys.covid19.R
import com.fandlys.covid19.adapter.ProvinceAdapter
import com.fandlys.covid19.pojos.DataPerProvince
import com.fandlys.covid19.viewmodel.ProvinceViewModel
import kotlinx.android.synthetic.main.fragment_province.*
import kotlinx.android.synthetic.main.province_items.*
import java.util.*
import kotlin.collections.ArrayList


class ProvinceFragment : Fragment() {
    private lateinit var adapter: ProvinceAdapter
    private lateinit var viewModel: ProvinceViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_province, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter= ProvinceAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(ProvinceViewModel::class.java)
        viewModel.setProvince()
        viewModel.getProvince().observe(this, androidx.lifecycle.Observer { listProvince ->
            if (listProvince != null){
                adapter.setData(listProvince)
                rv_province.layoutManager = LinearLayoutManager(context)
                rv_province.adapter = adapter
            }
        })


    }

}
