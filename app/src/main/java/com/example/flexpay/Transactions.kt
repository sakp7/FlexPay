package com.example.flexpay

    data class Transaction(
        var id: String,
        val description: String,
        val amount: String,
        val date: String
    )

