package com.example.learningproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var  todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvTodoList = findViewById<RecyclerView>(R.id.rvTodoList)
        val btnAddTodo = findViewById<Button>(R.id.add_todo)
        val deleteTodo = findViewById<Button>(R.id.delete_todo)
        val etTodoItem = findViewById<TextView>(R.id.edit_etitle)

        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoList.adapter = todoAdapter

        rvTodoList.layoutManager = LinearLayoutManager(this)

        // Add items to the to-do list
        btnAddTodo.setOnClickListener{
            val todoItem = etTodoItem.text.toString()
            if(todoItem.isNotEmpty()){
                val todo = Todo(todoItem)
                todoAdapter.addTodo(todo)
            }
        }

        // Delete to-do items that are unchecked
        deleteTodo.setOnClickListener{
            todoAdapter.deleteDoneTodo()
        }

    }
}