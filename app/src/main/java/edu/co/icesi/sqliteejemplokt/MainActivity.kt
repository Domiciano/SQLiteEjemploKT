package edu.co.icesi.sqliteejemplokt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.co.icesi.sqliteejemplokt.databinding.ActivityMainBinding
import edu.co.icesi.sqliteejemplokt.db.AppDB
import edu.co.icesi.sqliteejemplokt.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var db:AppDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDB.getInstance(applicationContext)

        binding.loginBtn.setOnClickListener {
            val id = binding.loginIDET.text.toString()
            val name = binding.loginNameET.text.toString()

            val user = User(id, name)

            db.userDAO().insert(user)

            val users = db.userDAO().getAll()
            for(u in users){
                Log.e(">>>", u.name)
            }

            val intent = Intent(this, SurveyActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)


        }

        binding.loginNameET.setOnFocusChangeListener { view, focus ->
            if(focus){
                val id = binding.loginIDET.text.toString()
                val user = db.userDAO().getById(id)

                user?.let{
                    binding.loginNameET.setText(user.name)
                }


            }
        }

    }
}