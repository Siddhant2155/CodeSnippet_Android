package com.example.todoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.todoapp.db.MyDbHelpe
import com.example.todoapp.db.TodoTable

class MainActivity : AppCompatActivity() {

    var todos = ArrayList<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listAdaptor = ArrayAdapter<Todo>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            todos
        )

        val listView = findViewById<ListView>(R.id.lstView)
        val btn = findViewById<Button>(R.id.btn)
        val editText = findViewById<EditText>(R.id.editText)

        listView.adapter = listAdaptor
//        val todoAdaptor = TodoAdaptor(todos, this)
//        listView.adapter = todoAdaptor

        val db = MyDbHelpe(this).writableDatabase

        fun refreshTodoList() {
            todos.clear()
            todos.addAll(TodoTable.getAllTodos(db))
            Log.d("TODOS", todos.toString())
//            todoAdaptor.notifyDataSetChanged()
            listAdaptor.notifyDataSetChanged()
        }
        refreshTodoList()

        btn.setOnClickListener {
            val newTodo = Todo(
                editText.text.toString(),
                false
            )
            TodoTable.insertTodo(db, newTodo)
            editText.setText("")

            refreshTodoList()

        }
    }
    class TodoAdaptor(val todos: ArrayList<Todo>,
    val context: Context) : BaseAdapter() {
        override fun getCount(): Int {
            return  todos.size
        }

        override fun getItem(position: Int): Any {
            return todos.get(position)
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val view = View.inflate(
                context,
                android.R.layout.simple_list_item_1,
                parent
            )
            view.findViewById<TextView>(android.R.id.text1).setText(todos.get(position).task)

            return view
        }

    }
}