package com.example.androiddemo

import com.example.androiddemo.model.Pizza
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiddemo.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {


    val selectedPizza = SelectedPizzaContainer.selectedPizza

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pizza: Pizza? = selectedPizza

        if (pizza != null) {
            binding.imageView.setImageResource(pizza.imageRes)
            binding.resultTextView.text = pizza.title
            binding.resultDescription.text = pizza.shortDescription
        }
    }
    fun goBackToMainActivity() {
        if (!isFinishing) {
            finish()
        }
    }

}