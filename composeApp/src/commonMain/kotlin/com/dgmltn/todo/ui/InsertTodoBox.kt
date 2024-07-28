package com.dgmltn.todo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dgmltn.todo.domain.ToDo


@Composable
fun InsertTodoBox(
    modifier: Modifier = Modifier,
    onInsert: (ToDo) -> Unit,
) {
    val queryText = remember { mutableStateOf("") }

    Box(modifier = modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text(text = "Add a todo") },
            trailingIcon = {
                Icon(
                    Icons.AutoMirrored.Filled.Send,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        onInsert(ToDo(task = queryText.value))
                        queryText.value = ""
                    })
            },
            value = queryText.value,
            onValueChange = {
                queryText.value = it
            })
    }
}
