package com.example.learningproject

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>,

): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView = itemView.findViewById<TextView>(R.id.tvTodoTitle)
        val checkBox = itemView.findViewById<CheckBox>(R.id.chdone)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_todo_item,
                parent,
                false,
            )
        )
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView , isChecked: Boolean){
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneTodo(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current_todo = todos[position]
        holder.itemView.apply {
            holder.titleTextView.text = current_todo.title
            holder.checkBox.isChecked = current_todo.isChecked

            toggleStrikeThrough(holder.titleTextView, current_todo.isChecked)

            holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(holder.titleTextView, isChecked)
                current_todo.isChecked = !current_todo.isChecked
            }
        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}