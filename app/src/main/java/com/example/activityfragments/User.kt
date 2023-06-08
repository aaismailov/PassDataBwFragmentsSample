package com.example.activityfragments

import java.io.Serializable

data class User(
    val name:String,
    val age: String,
    val address: String,
    val email: String
): Serializable
