package edu.co.icesi.sqliteejemplokt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.sqliteejemplokt.databinding.ActivitySurveyBinding
import edu.co.icesi.sqliteejemplokt.db.AppDB
import edu.co.icesi.sqliteejemplokt.model.Answer

class SurveyActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySurveyBinding
    private lateinit var db:AppDB
    private lateinit var id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDB.getInstance(applicationContext)
        id = intent.extras?.getString("id")!!



        binding.submitBtn.setOnClickListener {
            submit()
        }

        binding.syncBtn.setOnClickListener {
            sync()
        }

        updateUI()

    }

    fun sync(){
        val userWithAnswers = db.userDAO().getUserWithAnswersNotSynced(id)

        userWithAnswers?.let {
            val user = userWithAnswers.user
            val answers = userWithAnswers.answers

            val batch = Firebase.firestore.batch()
            val userref = Firebase.firestore.collection("pollster").document(user.id)
            batch.set(userref, user)

            for(a in answers){
                val dataref = Firebase.firestore.collection("data").document(a.natID)
                batch.set(dataref, a)
            }

            batch.commit().addOnSuccessListener {
                //Tenemos que actualizar la bandera isUploaded
                Toast.makeText(this, "Los datos se sincronizaron con exito", Toast.LENGTH_LONG).show()
                db.answerDAO().updateAllAnswers(id)
                updateUI()
            }


        }

    }

    fun updateUI(){
        val answers = db.answerDAO().getAllNotUploaded(id)
        binding.syncBtn.text = "SYNC (${answers.size})"
    }

    fun submit(){

        val socialRB:RadioButton = findViewById(binding.socialNetRG.checkedRadioButtonId)
        val drinkRB:RadioButton = findViewById(binding.drinkRG.checkedRadioButtonId)

        val answer = Answer(
            binding.natIDET.text.toString(),
            binding.nameET.text.toString(),
            binding.ageET.text.toString().toInt(),
            binding.strataET.text.toString().toInt(),
            socialRB.text.toString(),
            drinkRB.text.toString(),
            binding.exerciseET.text.toString().toInt(),
            false,
            id
        )

        db.answerDAO().insert(answer)
        updateUI()
    }
}