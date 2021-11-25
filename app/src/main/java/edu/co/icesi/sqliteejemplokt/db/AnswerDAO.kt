package edu.co.icesi.sqliteejemplokt.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.co.icesi.sqliteejemplokt.model.Answer

@Dao
interface AnswerDAO {
    //Aqui van las consultas

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(answer:Answer)

    @Query("SELECT * FROM answers WHERE userid=:id AND isUploaded = 0")
    fun getAllNotUploaded(id:String):List<Answer>

    @Query("UPDATE answers SET isUploaded = 1 WHERE userid = :id")
    fun updateAllAnswers(id:String)
}