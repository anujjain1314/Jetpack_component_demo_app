package com.anujjain.getMegaAssignment.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_repo")
data class TrendingRepoDataModel (
    val author : String,
    val name : String,
    val avatar : String,
    @PrimaryKey
    val url : String,
    val description : String,
    val language : String,
    val languageColor : String,
    val stars : Int,
    val forks : Int,
    val currentPeriodStars : Int,
    // to store State of the item
    var expanded : Boolean = false
){
    fun getDesc() : String = "$description ($url)"
}

data class BuiltBy (

    val href : String,
    val avatar : String,
    val username : String
)