package edu.co.icesi.sqliteejemplokt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.co.icesi.sqliteejemplokt.model.Answer
import edu.co.icesi.sqliteejemplokt.model.User


@Database(
    entities = [User::class, Answer::class],
    version = 1
)
abstract class AppDB : RoomDatabase(){

    companion object{

        private var db: AppDB? = null

        fun getInstance(context:Context):AppDB{
            if(db == null){
                //creamos objeto db
                db = Room.databaseBuilder(context, AppDB::class.java, "appdbkotlin").allowMainThreadQueries().build()
            }
            return db!!
        }

    }

    //Declarar los DAO
    abstract fun userDAO(): UserDAO
    abstract fun answerDAO(): AnswerDAO

}