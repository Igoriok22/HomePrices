package com.example.testapp.domain.model

data class ShoppingList(
        var date: String,
        var products: MutableList<Product>

)

data class Product(
        var name: String,
        var number: Double,
        var price: Double
)