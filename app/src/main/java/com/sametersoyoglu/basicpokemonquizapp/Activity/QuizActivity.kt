package com.sametersoyoglu.basicpokemonquizapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.sametersoyoglu.basicpokemonquizapp.Helper.DatabaseHelper
import com.sametersoyoglu.basicpokemonquizapp.dao.Pokemons
import com.sametersoyoglu.basicpokemonquizapp.dao.Pokemonsdao
import com.sametersoyoglu.basicpokemonquizapp.R
import com.sametersoyoglu.basicpokemonquizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var question: ArrayList<Pokemons>
    private lateinit var wrongOptions: ArrayList<Pokemons>
    private lateinit var rightQuestion: Pokemons
    private lateinit var allOptions: HashSet<Pokemons>
    private lateinit var db: DatabaseHelper
    private var trueScore = 0
    private var falseScore = 0
    private var questionLoop = 0

    private lateinit var countDownTimer: CountDownTimer
    private val countdownDuration: Long = 15500 // 10 saniye

    private lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        question = Pokemonsdao().random5Pokemon(db)


        if (question.isEmpty()) {
            Toast.makeText(this, "soru bulunamadı", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            uploadQuestion()
        }

        startCountDown()
        //uploadQuestion()

        binding.buttonA.setOnClickListener {

            trueControl(binding.buttonA)
            questionLoopCheck()
        }
        binding.buttonB.setOnClickListener {
            trueControl(binding.buttonB)
            questionLoopCheck()
        }
        binding.buttonC.setOnClickListener {
            trueControl(binding.buttonC)
            questionLoopCheck()
        }
        binding.buttonD.setOnClickListener {
            trueControl(binding.buttonD)
            questionLoopCheck()
        }
    }

    fun uploadQuestion() {

        binding.textViewNumber.text = "${questionLoop + 1}. Question"

        rightQuestion = question.get(questionLoop)

        binding.imageViewPokemon.setImageResource(
            resources.getIdentifier(
                rightQuestion.pokemon_resim,
                "drawable",
                packageName
            )
        )

        wrongOptions = Pokemonsdao().random3FalsePokemon(db, rightQuestion.pokemon_id)

        allOptions = HashSet()
        allOptions.add(rightQuestion)
        allOptions.add(wrongOptions.get(0))
        allOptions.add(wrongOptions.get(1))
        allOptions.add(wrongOptions.get(2))


        binding.buttonA.text = allOptions.elementAt(0).pokemon_ad
        binding.buttonB.text = allOptions.elementAt(1).pokemon_ad
        binding.buttonC.text = allOptions.elementAt(2).pokemon_ad
        binding.buttonD.text = allOptions.elementAt(3).pokemon_ad

    }

    fun questionLoopCheck() {


        questionLoop++

        if (questionLoop != 10) {
            uploadQuestion()
        } else {

            val intent = Intent(this@QuizActivity, ResultActivity::class.java)
            intent.putExtra("trueScore", trueScore)
            startActivity(intent)
            finish()
        }
    }

    fun trueControl(button: Button) {

        val greenColor = ContextCompat.getColor(this, R.color.green)
        val redColor = ContextCompat.getColor(this, R.color.red)

        val buttonText = button.text.toString()
        val trueAnswer = rightQuestion.pokemon_ad


        binding.buttonA.setBackgroundResource(R.drawable.pokemon_ball_button)
        binding.buttonB.setBackgroundResource(R.drawable.pokemon_ball_button)
        binding.buttonC.setBackgroundResource(R.drawable.pokemon_ball_button)
        binding.buttonD.setBackgroundResource(R.drawable.pokemon_ball_button)


        if (buttonText == trueAnswer) {
            trueScore++
            button.setBackgroundColor(greenColor)

        } else {
            falseScore++
            button.setBackgroundColor(redColor)

            when (trueAnswer) {
                binding.buttonA.text.toString() -> {
                    binding.buttonA.setBackgroundColor(greenColor)
                }
                binding.buttonB.text.toString() -> {
                    binding.buttonB.setBackgroundColor(greenColor)
                }
                binding.buttonC.text.toString() -> {
                    binding.buttonC.setBackgroundColor(greenColor)
                }
                binding.buttonD.text.toString() -> {
                    binding.buttonD.setBackgroundColor(greenColor)
                }
            }
        }
        binding.textViewTrue.text = "True : $trueScore"
        binding.textViewFalse.text = "False : $falseScore"

    }

    private fun startCountDown() {
        countDownTimer = object : CountDownTimer(countdownDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding.textViewTimer.text = "Time: $secondsRemaining seconds"            }

            override fun onFinish() {
                val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                intent.putExtra("trueScore", trueScore)
                intent.putExtra("falseScore", falseScore)
                startActivity(intent)
            }
        }
        countDownTimer.start()
    }

}