package com.anujjain.getMegaAssignment.trendingRepo.network





data class TrendingRepoResponseModel (
    val author : String,
    val name : String,
    val avatar : String,
    val url : String,
    val description : String,
    val language : String,
    val languageColor : String,
    val stars : Int,
    val forks : Int,
    val currentPeriodStars : Int,
    val builtBy : List<BuiltBy>
)

data class BuiltBy (

    val href : String,
    val avatar : String,
    val username : String
)

