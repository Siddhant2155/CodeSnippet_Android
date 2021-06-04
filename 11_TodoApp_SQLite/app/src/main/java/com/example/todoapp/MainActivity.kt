package com.example.todoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.db.MyDbHelpe
import com.example.todoapp.db.TodoTable

class MainActivity : AppCompatActivity() {

    var todos = ArrayList<Todo>()
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      mBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val listAdaptor = ArrayAdapter<Todo>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            todos
        )

     //   val listView = findViewById<ListView>(R.id.lstView)
       // val btn = findViewById<Button>(R.id.btn)
        //val editText = findViewById<EditText>(R.id.editText)
        mBinding.btn.setOnClickListener {

        }
//        listView.adapter = listAdaptor
        val todoAdaptor = TodoAdaptor( this,todos)
        mBinding.lstView.adapter = todoAdaptor

        val db = MyDbHelpe(this).writableDatabase

        fun refreshTodoList() {
            todos.clear()
            todos.addAll(TodoTable.getAllTodos(db))
            Log.d("TODOS", todos.toString())
            todoAdaptor.notifyDataSetChanged()
//            listAdaptor.notifyDataSetChanged()
        }
        refreshTodoList()

        mBinding.btn.setOnClickListener {
            val newTodo = Todo(
                mBinding.editText.text.toString(),
                false
            )
            TodoTable.insertTodo(db, newTodo)
            mBinding.editText.setText("")

            refreshTodoList()

        }
    }
    class TodoAdaptor(val context: Context,val todos: ArrayList<Todo>,
                       ) : BaseAdapter() {
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

            val view = LayoutInflater.from(context).inflate(
                android.R.layout.simple_list_item_1,
                parent,false
            )
            view.findViewById<TextView>(android.R.id.text1).setText(todos.get(position).task)

            return view
        }

    }
}