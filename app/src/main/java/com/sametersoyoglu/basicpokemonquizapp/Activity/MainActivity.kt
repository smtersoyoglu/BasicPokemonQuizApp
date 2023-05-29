package com.sametersoyoglu.basicpokemonquizapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sametersoyoglu.basicpokemonquizapp.Helper.DatabaseCopyHelper
import com.sametersoyoglu.basicpokemonquizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseCoppy()

        binding.buttonStart.setOnClickListener {
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            startActivity(intent)
        }

    }

    fun databaseCoppy() {
        val copyHelper = DatabaseCopyHelper(this)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}