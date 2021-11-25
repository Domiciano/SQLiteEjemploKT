package edu.co.icesi.sqliteejemplokt.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
data class Answer(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val natID: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "age")
    val age: Int,
    @ColumnInfo(name = "strata")
    val strata: Int,
    @ColumnInfo(name = "mostUsedSocialNet")
    val mostUsedSocialNet: String,
    @ColumnInfo(name = "mostConsumedDrink")
    val mostConsumedDrink: String,
    @ColumnInfo(name = "exerciseLevel")
    val exerciseLevel: Int,
    @ColumnInfo(name = "isUploaded")
    val isUploaded: Boolean,

    //Foreing Key
    @ColumnInfo(name = "userid")
    val userID: String
)