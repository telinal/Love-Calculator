package com.example.lovecalculator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.lovecalculator.MainActivity.Companion.PERCENTAGE
import com.example.lovecalculator.databinding.ActivityMainBinding
import com.example.lovecalculator.databinding.ActivityPercentageBinding
import com.example.lovecalculator.models.PercentageData

class PercentageActivity : AppCompatActivity() {

    lateinit var binding: ActivityPercentageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_percentage)

        val percentage = intent.getSerializableExtra(PERCENTAGE) as PercentageData
        binding.percentagedata = percentage

        binding.myProfile.setOnClickListener {
            val url = "https://in.linkedin.com/in/telina-laiphangbam-54970b1b8"
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }

    }
}