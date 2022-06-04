package com.csibtn.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.csibtn.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binder : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        val startBtn = binder.flStart
        startBtn.setOnClickListener{
            Intent(this,ExerciseActivity::class.java).also{
                startActivity(it)
            }
        }
    }


}