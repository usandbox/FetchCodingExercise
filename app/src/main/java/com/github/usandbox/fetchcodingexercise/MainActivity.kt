package com.github.usandbox.fetchcodingexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.usandbox.fetchcodingexercise.data.Item
import com.github.usandbox.fetchcodingexercise.ui.MainViewModel
import com.github.usandbox.fetchcodingexercise.ui.ViewState
import com.github.usandbox.fetchcodingexercise.ui.theme.FetchCodingExerciseTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels {
        (application as FetchApplication).appComponent.viewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchCodingExerciseTheme {
                val viewState = viewModel.viewState.collectAsState()

                when (val state = viewState.value) {
                    is ViewState.Loading -> {
                        LoadingState()
                    }

                    is ViewState.Success -> {
                        FetchList(state.items)
                    }

                    is ViewState.Error -> {
                        ErrorState(state.message)
                    }
                }
            }
        }
    }
}

@Composable
fun FetchList(data: List<Item>, modifier: Modifier = Modifier) {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(data) { item ->
            ListItem(item, modifier)
        }
    }
}

@Composable
fun ListItem(item: Item, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(all = 8.dp)
        ) {
            Text(
                text = "Name: ${item.name}",
                modifier = modifier
            )
            Text(
                text = "Id: ${item.id}",
                modifier = modifier
            )
            Text(
                text = "List Id: ${item.listId}",
                modifier = modifier
            )
        }
    }
}

@Composable
fun LoadingState() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorState(message: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp)
    ) {
        Text(
            text = "Error: $message",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}
