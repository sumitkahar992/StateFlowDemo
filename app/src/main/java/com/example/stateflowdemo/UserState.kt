package com.example.stateflowdemo

data class UserState(
    val data : List<String>? = null,
    val isLoading : Boolean = false,
    val errorMsg : String? = null
)
