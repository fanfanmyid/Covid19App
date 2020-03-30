/**
 * Created by Fandly on 30/3/2020.
 * Made With Love
 */

package com.fandlys.covid19.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.fandlys.covid19.R
import com.fandlys.covid19.pojos.DataPerCase
import kotlinx.android.synthetic.main.case_items.view.*

class CaseAdapter : RecyclerView.Adapter<CaseAdapter.CaseViewHolder>(){

    private val mData = ArrayList<DataPerCase>()

    fun setData(item: ArrayList<DataPerCase>){
        mData.clear()
        mData.addAll(item)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CaseAdapter.CaseViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.case_items,viewGroup,false)
        return CaseViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: CaseAdapter.CaseViewHolder, position: Int) {
       holder.bind(mData[position])
    }

    class CaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(dataPerCase: DataPerCase){
            with(itemView){
                txt_klaster.text = dataPerCase.cluster
                txt_kasuske.text = "Kasus Ke-${dataPerCase.caseId.toString()}"
                txt_gender.text = "Jenis Kelamin : ${dataPerCase.gender}"
                //txt_umur.text = dataPerCase.age.toString()
                txt_status.text = "Status : ${dataPerCase.status}"

            }
        }
    }
}