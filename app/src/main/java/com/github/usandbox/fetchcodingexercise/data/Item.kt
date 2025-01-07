package com.github.usandbox.fetchcodingexercise.data

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val listId: Int,
    val name: String?,
)
