package com.csibtn.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.csibtn.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    lateinit var exerciseBinder : ActivityExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseBinder = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(exerciseBinder.root)
        setSupportActionBar(exerciseBinder.tbMain)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        exerciseBinder.tbMain.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}