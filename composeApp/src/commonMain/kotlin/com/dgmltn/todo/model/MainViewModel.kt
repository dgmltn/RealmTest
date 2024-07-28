package com.dgmltn.todo.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benasher44.uuid.Uuid
import com.dgmltn.todo.data.RealmRepo
import com.dgmltn.todo.domain.ToDo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repo = RealmRepo()
    
    val queries = repo.query()

    fun insert(todo: ToDo) {
        viewModelScope.launch {
            repo.insert(todo)
        }
    }
    
    fun update(todo: ToDo) {
        viewModelScope.launch {
            repo.update(todo)
        }
    }
    
    fun delete(todo: ToDo) {
        viewModelScope.launch {
            repo.delete(todo)
        }
    }
}