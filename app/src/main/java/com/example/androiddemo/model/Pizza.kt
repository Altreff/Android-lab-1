package com.example.androiddemo.model

import java.util.UUID

data class Pizza(
    val imageRes: Int,
    val title: String,
    val id: String = UUID.randomUUID().toString(),
    val shortDescription: String,
    val price: String
) {


}
