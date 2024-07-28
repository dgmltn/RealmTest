package com.dgmltn.todo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.dgmltn.todo.domain.ToDo
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TodoRow(
    todo: ToDo,
    onUpdate: (ToDo) -> Unit,
    onDelete: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp),
    )
    ) {
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = {
                onUpdate(todo.copy(isDone = !todo.isDone))
            }
        )
        Text(
            text = todo.task,
            style = TextStyle(textDecoration = TextDecoration.LineThrough.takeIf { todo.isDone }),
            modifier = Modifier.weight(1f)
        )
        IconButton(onDelete) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "",
            )
        }
    }
}

@Composable
@Preview
private fun Preview_TodoRow() {
    var todo by remember { mutableStateOf(ToDo("mow the lawn")) }
    TodoRow(todo, { todo = it }, {})
}