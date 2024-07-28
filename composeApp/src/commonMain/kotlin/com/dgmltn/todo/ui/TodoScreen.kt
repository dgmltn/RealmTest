package com.dgmltn.todo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dgmltn.todo.model.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen() {
    val viewModel = viewModel<MainViewModel>()
    val todos = viewModel.queries.collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null) },
                title = { Text("ToDos") }
            )
        },
        containerColor = Color(0xffF9F9F9)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            TodosList(
                todos = todos,
                onUpdate = viewModel::update,
                onDelete = viewModel::delete
            )

            Spacer(modifier = Modifier.weight(1f))
            
            InsertTodoBox(
                onInsert = viewModel::insert
            )
        }
    }
}


