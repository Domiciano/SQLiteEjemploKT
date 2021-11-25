package edu.co.icesi.sqliteejemplokt.db

import androidx.room.*
import edu.co.icesi.sqliteejemplokt.model.User
import edu.co.icesi.sqliteejemplokt.model.UserWithAnswers

@Dao
interface UserDAO {
    //Aqui van las consultas

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE id=:id")
    fun getById(id:String): User?

    @Transaction
    @Query("SELECT users.* FROM users INNER JOIN answers WHERE users.id = :id AND answers.isUploaded = 0")
    fun getUserWithAnswersNotSynced(id:String):UserWithAnswers?

}