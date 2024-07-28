package com.dgmltn.todo.domain

import com.benasher44.uuid.Uuid
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

data class ToDo(
    val task: String,
    val isDone: Boolean = false,
    val id: Uuid = Uuid.randomUUID(),
)