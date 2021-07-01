package com.example.firebasetest2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var notes = arrayListOf<String>()
    companion object {
        val nodeName = "firebaseTest2"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbRef = FirebaseDatabase.getInstance().getReference()

        saveBtn.setOnClickListener {
            val txt = txtView.text.toString()
            if (txt.isEmpty()) {
                return@setOnClickListener
            }
            dbRef.child(MainActivity.nodeName).push().setValue(txt)
            /*
            *   to save a custom object in firebase
            *   Obj b = new Obj(, , , )
            *   dbRef.child(MainActivity.nodeName).push().setValue(b)
            *
            *   NOTE +>> the properties of Obj class need to be of PRIMITIVE TYPE

             */

        }

        val arrayAdaptor = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            notes
        )
        showItms.adapter = arrayAdaptor

        dbRef.child(MainActivity.nodeName).addChildEventListener(object: ChildEventListener {

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // called when new data get inserted into `MainActivity.nodeName` node

                val data = snapshot.getValue(String::class.java)
                data?.let {
                    notes.add(it)
                    arrayAdaptor.notifyDataSetChanged()
                }
                /*
                *    for fetching object for db
                *   val data: Obj = snapshot.getValue(Obj::class.java)
                 */

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // called when existing data node is modified
                Log.i(MainActivity.nodeName, "data changed ${previousChildName}")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // called when sub node data is removed
                val deletedValue = snapshot.getValue(String::class.java)
                deletedValue.let {
                    notes.remove(it)
                    arrayAdaptor.notifyDataSetChanged()
                }
//                Log.i(MainActivity.nodeName, snapshot.getValue(String::class.java)!!)
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // called when position of sub node is changes
                Log.i(MainActivity.nodeName, "location updated ${previousChildName}")
            }

            override fun onCancelled(error: DatabaseError) {
                // called when read operation is failed
                Log.i(MainActivity.nodeName, "db Error occurs")
            }

        })


        // It return the entire database whenever a new child added
//        dbRef.child(MainActivity.nodeName).addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
    }
}