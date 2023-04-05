package com.example.patientapp.presentation.features.patients.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.patientapp.domain.model.patients.Data
import com.example.patientapp.presentation.databinding.RowPatientBinding

class PatientsAdapter():
    ListAdapter<Data?,PatientsAdapter.PatientsViewHolder>(DiffCallBack){

    var indexLastSelected = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding  = RowPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PatientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model,position)
    }




    inner class PatientsViewHolder(private val binding:RowPatientBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Data?, position: Int){
            binding.model = model

            binding.cardView.setOnClickListener{
                if(position != indexLastSelected){

                    //if not default
                    if(indexLastSelected != -1){
                        getItem(indexLastSelected)?.selected = false
                        notifyItemChanged(indexLastSelected)
                    }

                    indexLastSelected = position
                    getItem(position)?.selected = true
                    notifyItemChanged(position)
                }
            }

        }
    }

    private object DiffCallBack:DiffUtil.ItemCallback<Data?>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }


}