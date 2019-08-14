package jp.co.cyberagent.dojo2019.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
class User (

    var name: String?,
    var githubAccount: String,
    var githubImage: String,
    var twitterAccount: String?
){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}