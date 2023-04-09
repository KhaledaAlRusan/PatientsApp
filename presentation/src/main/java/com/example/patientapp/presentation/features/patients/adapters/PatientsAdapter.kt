package com.example.patientapp.presentation.features.patients.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.patientapp.domain.model.patients.PatientResponse
import com.example.patientapp.presentation.databinding.RowPatientBinding

class PatientsAdapter(
    private val onDeletePatient:(id:String?) ->Unit,
    private val onClickItem: (id:String?) -> Unit
):
    ListAdapter<PatientResponse,PatientsAdapter.PatientsViewHolder>(DiffCallBack){

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
        fun bind(model: PatientResponse, position: Int){
            binding.model = model

            binding.cardView.setOnClickListener{
                if(position != indexLastSelected){

                    // Set the 'selected' property of all items to false
                    for (i in 0 until itemCount) {
                        getItem(i)?.selected = false
                        notifyItemChanged(i)
                    }

                    indexLastSelected = position
                    getItem(position)?.selected = true
                    notifyItemChanged(position)
                }
                onClickItem(model.id)
            }
            binding.ivDelete.setOnClickListener {
                onDeletePatient(model.id)
            }

        }
    }

    private object DiffCallBack:DiffUtil.ItemCallback<PatientResponse?>(){
        override fun areItemsTheSame(oldItem: PatientResponse, newItem: PatientResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PatientResponse, newItem: PatientResponse): Boolean {
            return oldItem == newItem
        }

    }

}