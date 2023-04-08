package com.example.patientapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment<T : ViewBinding>(
   @LayoutRes private val layoutId:Int
) : Fragment() {
    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preformDataBinding(inflater,container)
        return binding.root
    }

    private fun preformDataBinding(inflater: LayoutInflater,container: ViewGroup?){
        binding = DataBindingUtil.inflate(inflater,layoutId,container,false)
    }
}