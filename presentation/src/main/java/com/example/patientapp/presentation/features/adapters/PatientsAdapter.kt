package com.example.patientapp.presentation.features.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patientapp.domain.model.patients.Data
import com.example.patientapp.presentation.databinding.RowPatientBinding

class PatientsAdapter(private val patients:List<Data?>?):RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>(){

    var indexLastSelected = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding  = RowPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PatientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        val model = patients?.get(position)
        holder.bind(model,position)
    }

    override fun getItemCount() = patients?.size ?:0




    inner class PatientsViewHolder(private val binding:RowPatientBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Data?, position: Int){
            binding.model = model

            binding.cardView.setOnClickListener{
                if(position != indexLastSelected){

                    //if not default
                    if(indexLastSelected != -1){
                        patients?.get(indexLastSelected)?.selected = false
                        notifyItemChanged(indexLastSelected)
                    }

                    indexLastSelected = position
                    patients?.get(position)?.selected = true
                    notifyItemChanged(indexLastSelected)

                }
            }

        }
    }


}