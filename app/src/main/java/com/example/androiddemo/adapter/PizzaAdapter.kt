package com.example.androiddemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.databinding.ItemPizzaBinding
import com.example.androiddemo.model.Pizza

class PizzaAdapter(
    private val onPizzaClick: (Pizza) -> Unit
) : RecyclerView.Adapter<PizzaAdapter.ViewHolder>() {

    companion object {
        private const val Pizza_ADAPTER_TAG = "PizzaAdapter"
    }

    private val pizzaList: ArrayList<Pizza> = ArrayList()

    fun setData(pizzas: ArrayList<Pizza>) {
        pizzaList.clear()
        pizzaList.addAll(pizzas)

        /**
         * метод для обновления списка элементов
         */
        notifyDataSetChanged()
    }

    /**
     * метод, который будет создавать view для каждого объекта
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(Pizza_ADAPTER_TAG, "onCreateViewHolder")
        return ViewHolder(
            ItemPizzaBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    /**
     * метод, для определения количесвта элементов списка
     */
    override fun getItemCount() = pizzaList.size

    /**
     * для вызова метода из ViewHolder'a
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(Pizza_ADAPTER_TAG, "onBindViewHolder: $position")
        holder.bind(pizzaList[position])
    }

    inner class ViewHolder(
        private val binding: ItemPizzaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pizza: Pizza) {
            with(binding) {
                pizzaImage.setImageResource(pizza.imageRes)
                pizzaTitle.text = pizza.title
                pizzaDescription.text = pizza.shortDescription
                pizzaPrice.text = pizza.price

                root.setOnClickListener {
                    onPizzaClick(pizza)
                }
            }
        }
    }
}