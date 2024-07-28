package com.dgmltn.todo.data

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidFrom
import com.dgmltn.todo.domain.ToDo
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

// Rules for RealmObject's that make me not want to use them as domain objects directly:
// 1. It must have a zero-arg constructor
// 2. It must have mutable members
// 3. Separation of concerns: RealmObject ties the data (ToDo) to the implementation (Realm)
class RealmToDo : RealmObject {
    @PrimaryKey
    var _id: String = Uuid.randomUUID().toString()
    var task: String = ""
    
    var isDone: Boolean = false
    
    fun toDomain(): ToDo {
        return ToDo(
            id = uuidFrom(this._id),
            task = this.task,
            isDone = this.isDone,
        )
    }
    
    fun updateFrom(other: RealmToDo) {
        task = other.task
        isDone = other.isDone
    }

    companion object {
        fun fromDomain(todo: ToDo): RealmToDo {
            return RealmToDo().apply {
                this._id = todo.id.toString()
                this.task = todo.task
                this.isDone = todo.isDone
            }
        }
    }
}

fun ToDo.toRealmToDo() = RealmToDo.fromDomain(this)