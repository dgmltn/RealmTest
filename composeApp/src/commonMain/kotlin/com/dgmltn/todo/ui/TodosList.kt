package com.dgmltn.todo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.dgmltn.todo.domain.ToDo
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TodosList(
    todos: List<ToDo>,
    onUpdate: (ToDo) -> Unit,
    onDelete: (ToDo) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(8.dp),
        content = {
            items(todos) { item: ToDo ->
                TodoRow(
                    todo = item,
                    onUpdate = { onUpdate(it) },
                    onDelete = { onDelete(item) }
                )
            }
        })
}

@Composable
@Preview
private fun Preview_TodosList() {
    val todos = listOf(
        ToDo(task = "mow the lawn"),
        ToDo(task = "wash the car")
    )
    TodosList(todos, {}, {})
}