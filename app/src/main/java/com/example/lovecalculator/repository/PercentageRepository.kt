package com.example.lovecalculator.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lovecalculator.api.PercentageAPI
import com.example.lovecalculator.models.PercentageData
import com.example.lovecalculator.utils.NetworkUtils

class PercentageRepository(
    private val percentageAPI: PercentageAPI,
    private val applicationContext: Context
    ) {

    private val percentLiveData = MutableLiveData<Response<PercentageData>>()
    val percents: LiveData<Response<PercentageData>>
    get() = percentLiveData

    suspend fun getPercent(fname: String, sname: String) {
        percentLiveData.postValue(Response.Loading())
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            try {
                val result = percentageAPI.getPercent(fname = fname, sname = sname)
                if (result?.body() != null) {
//                    quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                    percentLiveData.postValue(Response.Success(result.body()))
                }

            }
            catch (e: Exception) {
                percentLiveData.postValue(Response.Error(e.message.toString()))
            }
        }
        else{
//            val quotes = quoteDatabase.quoteDao().getQuotes()
//            val quoteList = QuoteList(1,1,1,quotes, 1, 1)
            percentLiveData.postValue(Response.Error("No Internet Connection"))
        }

    }
}