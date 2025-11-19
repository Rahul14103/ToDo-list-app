package com.example.todolistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.database.TaskDao
import com.example.todolistapp.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class TaskViewModel(private val dao: TaskDao) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _completedCount = MutableStateFlow(0)
    val completedCount: StateFlow<Int> = _completedCount

    private val _totalCount = MutableStateFlow(0)
    val totalCount: StateFlow<Int> = _totalCount

    init {
        viewModelScope.launch {
            combine(
                dao.getAllTasks(),
                dao.getCompletedCount(),
                dao.getTotalCount()
            ) { taskList, completed, total ->
                Triple(taskList, completed, total)
            }.collect { (taskList, completed, total) ->
                _tasks.value = taskList
                _completedCount.value = completed
                _totalCount.value = total
            }
        }
    }

    fun addTask(title: String, dueDate: Long?, priority: String) {
        viewModelScope.launch {
            dao.insertTask(Task(title = title, dueDate = dueDate, priority = priority))
        }
    }

    fun toggleCompletion(task: Task) {
        viewModelScope.launch {
            dao.updateTask(task.copy(isCompleted = !task.isCompleted))
        }
    }
}

class TaskViewModelFactory(private val dao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
