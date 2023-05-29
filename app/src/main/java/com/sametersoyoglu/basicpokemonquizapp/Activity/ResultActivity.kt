package com.sametersoyoglu.basicpokemonquizapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sametersoyoglu.basicpokemonquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val trueScore = intent.getIntExtra("trueScore",0)

        binding.textViewResult.text = "$trueScore True  ${10-trueScore} False"
        binding.textViewPercent.text = "% ${(trueScore*100)/10} Success"

        binding.buttonAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}