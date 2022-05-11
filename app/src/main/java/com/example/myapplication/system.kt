package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.preference.PreferenceManager
import com.example.myapplication.databinding.ActivitySystemBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class system : AppCompatActivity() {

    lateinit var views:ActivitySystemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        views= ActivitySystemBinding.inflate(layoutInflater)

        setContentView(views.root)

        val db=openOrCreateDatabase("dbUsuarios", MODE_PRIVATE,null)
        var q="create table if not exists usuarios(nombres varchar)"

        db.execSQL(q)
        val txtUser=views.tilNombre.editText
        views.button.setOnClickListener {

            if(txtUser?.text.toString().isEmpty()){

                MaterialAlertDialogBuilder(this)
                    .setMessage("Debe ingresar los datos de un Usuario")
                    .setPositiveButton("OK"){d,w->
                        views.tilNombre.editText!!.requestFocus()
                    }.show()

            }else{

                var qInsert="insert into usuarios values('${txtUser?.text.toString()}')"

                db.execSQL(qInsert)

                txtUser!!.text.clear()
                txtUser?.requestFocus()

            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflador=menuInflater

        inflador.inflate(R.menu.main,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

       return when(item.itemId){

            R.id.close->{
                val preferencesManager= PreferenceManager.getDefaultSharedPreferences(this)

                val admin=preferencesManager.edit()
                admin.remove("status")
                admin.apply()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
                return true
            }

           R.id.list->{
               startActivity(Intent(this,lista::class.java))
               return true
           }

           else->        super.onOptionsItemSelected(item)
       }


    }

}