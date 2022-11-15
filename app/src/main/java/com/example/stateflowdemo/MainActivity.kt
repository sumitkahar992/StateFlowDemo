package com.example.stateflowdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stateflowdemo.ui.theme.UserViewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(mainViewModel)
        }
    }
}


@Composable
fun MyApp(
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val text by viewModel.counter.collectAsState()
    val state by userViewModel.userState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = text.toString(),
            fontSize = 60.sp
        )

        Row(horizontalArrangement = Arrangement.spacedBy(22.dp)) {

            Button(onClick = {
                viewModel.incrementCount()
            }) {
                Text(text = "Increment +")
            }

            Button(onClick = {
                viewModel.decrementCount()
            }) {
                Text(text = "Decrement -")
            }
        }

        when {
            !state.errorMsg.isNullOrEmpty() -> {
                Text(text = state.errorMsg!!)
            }

            state.isLoading -> {
                CircularProgressIndicator()
            }

            !state.data.isNullOrEmpty() -> {
                val userList = state.data!!
                LazyColumn {
                    items(userList) {
                        Text(text = it)
                    }
                }
            }


        }

    }
}










