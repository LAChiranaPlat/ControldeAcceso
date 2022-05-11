package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import androidx.preference.PreferenceManager
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {

    lateinit var views:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.getString("data","")

        val preferencesManager= PreferenceManager.getDefaultSharedPreferences(this)

        if(preferencesManager.getBoolean("status",false)){
            startActivity(Intent(this,system::class.java))
            finish()
        }


        views= ActivityMainBinding.inflate(layoutInflater)
        setContentView(views.root)

        val nick=views.tiluser.editText
        val key=views.tilkey.editText

        views.button2.setOnClickListener {

            if(nick!!.text.isEmpty() || key!!.text.isEmpty())
            {
                nick.requestFocus()
                return@setOnClickListener
            }

            val u=preferencesManager.getString("Nick","nn")
            val k=preferencesManager.getString("Key","nn")

            if(nick!!.text.toString().equals(u) && key!!.text.toString().equals(k)){

                    val admin=preferencesManager.edit()
                    admin.putBoolean("status",true)
                    admin.apply()

                    startActivity(Intent(this,system::class.java))
                    finish()

            }else{
                Log.i("result","Credenciales no validas")

                nick.text.clear()
                key.text.clear()
                nick.requestFocus()

            }


        }

    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)

        outState.putString("data",views.tiluser.editText?.text.toString())

    }

}