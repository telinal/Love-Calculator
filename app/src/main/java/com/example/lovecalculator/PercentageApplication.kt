package com.example.lovecalculator

import android.app.Application
import com.example.lovecalculator.api.PercentageAPI
import com.example.lovecalculator.api.PercentageObject
import com.example.lovecalculator.repository.PercentageRepository

class PercentageApplication: Application() {
    lateinit var percentageRepository: PercentageRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val percentageAPI = PercentageObject.getInstance().create(PercentageAPI::class.java)
        percentageRepository = PercentageRepository(percentageAPI, applicationContext)
    }
}

