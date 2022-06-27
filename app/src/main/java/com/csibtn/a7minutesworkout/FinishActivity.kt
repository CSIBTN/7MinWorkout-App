package com.csibtn.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.csibtn.a7minutesworkout.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private lateinit var finishBinding : ActivityFinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finishBinding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(finishBinding.root)

        setSupportActionBar(finishBinding.tbFinish)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        finishBinding.tbFinish.setNavigationOnClickListener{
            onBackPressed()
        }
        finishBinding.btnFinish.setOnClickListener{
            finish()
        }
    }
}