package com.example.stateflowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun incrementCount() = _counter.value++
    fun decrementCount() = _counter.value--

    init {
        counter()
    }

    private fun counter() =
        viewModelScope.launch {
            counter.collect { value ->
                _counter.value = value
            }
        }


}