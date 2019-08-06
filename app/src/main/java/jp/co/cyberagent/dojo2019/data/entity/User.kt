package jp.co.cyberagent.dojo2019.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String?,
    val githubAccount: String,
    val twitterAccount: String?
)