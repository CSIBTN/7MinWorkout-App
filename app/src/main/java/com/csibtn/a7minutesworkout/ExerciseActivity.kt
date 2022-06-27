package com.csibtn.a7minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.csibtn.a7minutesworkout.databinding.ActivityExerciseBinding
import java.lang.Exception
import java.util.*

const val countDownConstant = 10
const val countDownConstantExercise = 30
class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var exerciseBinder: ActivityExerciseBinding
    private lateinit var restTimer: CountDownTimer
    private var rest = 0
    private var songVariation = 1

    lateinit var exerciseTimer: CountDownTimer
    private var exerciseProgress = 0

    lateinit var exerciseList : MutableList<ExerciseModel>
    private var currentExercisePosition = -1

    lateinit var tts : TextToSpeech
    lateinit var player : MediaPlayer
    lateinit var exerciseAdapter : ExerciseStatusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseBinder = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(exerciseBinder.root)
        setSupportActionBar(exerciseBinder.tbMain)
        exerciseList = Constants.defaultExerciseList()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        exerciseBinder.tbMain.setNavigationOnClickListener {
            onBackPressed()
        }
        player = MediaPlayer()

        setupRestView()
        adapterSetUp()
        tts = TextToSpeech(this,this,"")
    }

    private fun adapterSetUp(){
        exerciseBinder.rvExerciseStatus.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList)
        exerciseBinder.rvExerciseStatus.adapter = exerciseAdapter
    }

    private fun setProgressBar() {
        exerciseBinder.pgBar.progress = rest
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                rest++
                exerciseBinder.pgBar.progress = (countDownConstant - rest)
                exerciseBinder.tvTimer.text = (countDownConstant - rest).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                exerciseList[currentExercisePosition].setIsSelected(true)
                exerciseAdapter.notifyDataSetChanged()

                setUpExerciseView()
            }
        }.start()
    }

    private fun setupRestView() {
        player.stop()
        startPlaying(R.raw.success)
        exerciseBinder.flProgressBar.visibility = View.VISIBLE
        exerciseBinder.tvTimer.visibility = View.VISIBLE
        exerciseBinder.flExerciseView.visibility = View.INVISIBLE
        exerciseBinder.tvExercise.visibility = View.INVISIBLE
        exerciseBinder.ivImage.visibility = View.INVISIBLE
        exerciseBinder.tvReady.visibility = View.VISIBLE
        exerciseBinder.tvUpcomingExercise.visibility = View.VISIBLE
        exerciseBinder.tvUpcomingExerciseName.visibility = View.VISIBLE
        rest = 0
        exerciseBinder.tvUpcomingExerciseName.text = exerciseList[currentExercisePosition+1].getName()
        setProgressBar()
    }

    private fun setExerciseProgress() {
        exerciseBinder.pgBar.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                exerciseBinder.exerciseBar.progress = (countDownConstantExercise - exerciseProgress)
                exerciseBinder.tvTimerExercise.text =
                    (countDownConstantExercise - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition > exerciseList.lastIndex) {
                    exerciseList[currentExercisePosition].setIsSelected(false)
                    exerciseList[currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter.notifyDataSetChanged()
                    setupRestView()
                }
                else{
                    Intent(applicationContext,FinishActivity::class.java).also{
                        startActivity(it)
                        finish()
                    }
                }

            }
        }.start()
    }
    private fun setUpExerciseView(){
        player.stop()
        exerciseBinder.flProgressBar.visibility = View.INVISIBLE
        exerciseBinder.tvTimer.visibility = View.INVISIBLE
        exerciseBinder.flExerciseView.visibility = View.VISIBLE
        exerciseBinder.tvExercise.visibility = View.VISIBLE
        exerciseBinder.ivImage.visibility = View.VISIBLE
        exerciseBinder.tvReady.visibility = View.INVISIBLE
        exerciseBinder.tvUpcomingExercise.visibility = View.INVISIBLE
        exerciseBinder.tvUpcomingExerciseName.visibility = View.INVISIBLE
        exerciseProgress = 0
        speak(exerciseList[currentExercisePosition].getName())
        exerciseBinder.ivImage.setImageResource(exerciseList[currentExercisePosition].getImage())
        exerciseBinder.tvExercise.text = exerciseList[currentExercisePosition].getName()

        if(songVariation % 2 == 0){
            startPlaying(R.raw.best_gym_music)
        }else{
            startPlaying(R.raw.cossacks)
        }
        songVariation++
        setExerciseProgress()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                println("Bruh")
            }
        }else{
            println("Baza")
        }
    }
    private fun speak(text : String){
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
    private fun startPlaying(song : Int){
        val soundURI = Uri.parse("android.resource://com.csibtn.a7minutesworkout/$song")
        try{
            player = MediaPlayer.create(applicationContext,soundURI)
            player.isLooping = false
            player.start()
        }catch(e :Exception){
            e.printStackTrace()
        }

    }
}