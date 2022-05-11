package com.example.myapplication

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityListaBinding

class lista : AppCompatActivity() {

    lateinit var layout:ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        layout= ActivityListaBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(layout.root)

        val recycler=layout.lista
        recycler.layoutManager=LinearLayoutManager(this)

        //array
        val list:ArrayList<dataClass>
        list= ArrayList()



        try {

            val db=openOrCreateDatabase("dbUsuarios", MODE_PRIVATE,null)

            val cursor=db.rawQuery("select nombres from usuarios",null)

            if(cursor.moveToFirst()){

                while(cursor != null){

                    list.add(dataClass(cursor.getString(0)))

                    cursor.moveToNext()
                }

            }else{
                Log.i("result","nada que mostrar")
            }


        }catch (e:Exception){
            Log.e("result",e.stackTraceToString())
        }

        //adapter
        val myAdapter=adapter(list)
        recycler.adapter=myAdapter

    }
}