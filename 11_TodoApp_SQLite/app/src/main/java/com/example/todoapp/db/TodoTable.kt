package com.example.todoapp.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.todoapp.Todo

object TodoTable {
//    https://developer.android.com/training/data-storage/sqlite#WriteDbRow
//    Resource for SQLite in Android

    val TABLE_NAME = "todos"
    object Columns {
        val ID = "id"
        val TASK  = "task"
        val DONE = "done"
    }

    val CMD_CREATE_DB = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME
        (
        ${Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${Columns.TASK} TEXT,
        ${Columns.DONE} BOOLEAN
        )
    """.trimIndent()

    fun insertTodo(db: SQLiteDatabase, todo: Todo) {
        val row = ContentValues()
        row.put(Columns.TASK, todo.task)
        row.put(Columns.DONE, todo.done)

        db.insert(TABLE_NAME, null, row)
    }


    fun getAllTodos(db: SQLiteDatabase): ArrayList<Todo> {
       val todos = ArrayList<Todo>()
        val cursor = db.query(
            TABLE_NAME,   // The table to query
            arrayOf(Columns.ID, Columns.TASK, Columns.DONE),             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        while(cursor.moveToNext()) {
            val todo = Todo(
                cursor.getString(1),
                cursor.getInt(2) == 1
            )
            todos.add(todo)
        }
        return todos
    }

}