/**
 * Created by Fandly on 30/3/2020.
 * Made With Love
 */

package com.fandlys.covid19.adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.fandlys.covid19.R
import com.fandlys.covid19.pojos.DataPerProvince
import kotlinx.android.synthetic.main.province_items.view.*

class ProvinceAdapter : RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>() {

    private val mData = ArrayList<DataPerProvince>()

    fun setData(item: ArrayList<DataPerProvince>) {
        mData.clear()
        mData.addAll(item)
        notifyDataSetChanged()
    }

    fun addItem(item: DataPerProvince) {
        mData.add(item)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData.clear()
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup,position: Int): ProvinceViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.province_items,viewGroup,false)
        return ProvinceViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(provinceViewHolder: ProvinceViewHolder, position: Int) {
        provinceViewHolder.bind(mData[position])
    }

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(dataPerProvince: DataPerProvince){
            with(itemView){
                nama_provinsi.text = dataPerProvince.province
                data_provinsi.text = "${dataPerProvince.casePositive} positif," +
                        "${dataPerProvince.caseRecovered} sembuh, " + "${dataPerProvince.caseDie} meninggal"

            }
        }

    }
}