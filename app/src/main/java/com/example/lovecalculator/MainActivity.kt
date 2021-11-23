package com.example.lovecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.lovecalculator.databinding.ActivityMainBinding
import com.example.lovecalculator.repository.Response
import com.example.lovecalculator.viewmodel.MainViewModel
import com.example.lovecalculator.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERCENTAGE = "percentage"
    }

    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val repository = (application as PercentageApplication).percentageRepository

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(repository = repository)
        ).get(MainViewModel::class.java)

        mainViewModel.percents.observe(this, Observer {
            when (it) {
                is Response.Loading -> {
                    binding.progressBar.visibility = VISIBLE
                }
                is Response.Success -> {
                    binding.progressBar.visibility = GONE
                    val intent = Intent(this, PercentageActivity::class.java)
                    intent.putExtra(PERCENTAGE, it.data)
                    startActivity(intent)
                }
                is Response.Error -> {
                    binding.progressBar.visibility = GONE
                    Toast.makeText(this, it.errorMessage.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.calculateButton.setOnClickListener {
            mainViewModel.getPercentage(binding.yourNameET.text.toString(), binding.loveET.text.toString())

        }

    }
}