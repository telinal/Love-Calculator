package com.example.lovecalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lovecalculator.models.PercentageData
import com.example.lovecalculator.repository.PercentageRepository
import com.example.lovecalculator.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val repository: PercentageRepository) : ViewModel() {

    val percents: LiveData<Response<PercentageData>>
        get() = repository.percents

    fun getPercentage(fname: String, sname: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPercent(fname, sname)
        }
    }
}