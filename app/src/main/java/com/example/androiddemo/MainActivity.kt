package com.example.androiddemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.androiddemo.adapter.PizzaAdapter
import com.example.androiddemo.databinding.ActivityMainBinding
import com.example.androiddemo.model.Pizza
import com.example.androiddemo.model.PizzaDataSource
import java.util.Locale
import com.example.androiddemo.SelectedPizzaContainer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var adapter: PizzaAdapter? = null

    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<Pizza>
    private lateinit var dataList: ArrayList<Pizza>
    lateinit var imageList: Array<Int>
    lateinit var titleList:Array<String>
    lateinit var descList: Array<String>
    lateinit var priceList: Array<String>
    lateinit var idList: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pizzaList = PizzaDataSource.pizzaLists
        imageList = pizzaList.map { it.imageRes }.toTypedArray()
        titleList = pizzaList.map { it.title }.toTypedArray()
        descList = pizzaList.map { it.shortDescription }.toTypedArray()
        priceList = pizzaList.map { it.price }.toTypedArray()
        idList = pizzaList.map { it.id }.toTypedArray()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PizzaAdapter(
            onPizzaClick = {
                handlePizzaClick(it)
            }
        )

        adapter = PizzaAdapter(
            onPizzaClick = {
                handlePizzaClick(it)
            }
        )

        binding.recyclerView.adapter = adapter

        adapter?.setData(PizzaDataSource.pizzaLists)
        searchView = findViewById(R.id.search)
        searchList = arrayListOf()
        dataList = arrayListOf()
        getData();
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText?.lowercase(Locale.getDefault()) ?: ""
                val filteredList = pizzaList.filter { pizza ->
                    pizza.title.lowercase(Locale.getDefault()).contains(searchText)
                }
                val filteredArrayList = ArrayList<Pizza>(filteredList)
                adapter?.setData(filteredArrayList)
                return true
            }
        })

    }


    private fun getData(){
        for (i in imageList.indices){
            val dataClass = Pizza(imageList[i], titleList[i], idList[i], descList[i], priceList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
    }

    private fun handlePizzaClick(pizza: Pizza) {
        SelectedPizzaContainer.selectedPizza = pizza
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }






}