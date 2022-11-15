package com.example.stateflowdemo.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stateflowdemo.UserState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class UserViewModel : ViewModel() {

    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()


    init {
        getUsers()
    }


    private fun getUsers() = viewModelScope.launch {
        _userState.update { it.copy(isLoading = true) }
        delay(2000L)


        val userList = listOf("Sumit", "Ankit", "Kripa", "Sita", "Umashankar")
        _userState.update { it.copy(isLoading = false, data = userList) }
        delay(2000L)


        _userState.update { it.copy(isLoading = true) }
        delay(1500L)


        _userState.update { it.copy(isLoading = false, data = null, errorMsg = "Not Found !") }

    }








    private val _user = MutableStateFlow(UserState())
    val user : StateFlow<UserState> = _user.asStateFlow()


    private fun getUsersERR() = viewModelScope.launch {
        _user.update { it.copy(isLoading = true) }
        delay(3000L)

        val userList = listOf("8848","RUM","DIAMOND","ARNA_8")
        _user.update { it.copy(isLoading = false, data = userList) }
        delay(2000L)

        _user.update { it.copy(isLoading = true) }
        delay(3000L)

        _user.update { it.copy(isLoading = false, data = null, errorMsg = "NOT FOUND?") }


    }





}