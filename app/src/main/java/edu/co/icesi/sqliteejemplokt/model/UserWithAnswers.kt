package edu.co.icesi.sqliteejemplokt.model

import androidx.room.Embedded
import androidx.room.Relation


data class UserWithAnswers(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userid"
    )
    val answers: List<Answer>
)