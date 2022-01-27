package com.example.gitrepos.models


import com.google.gson.annotations.SerializedName

data class Permissions(
    @SerializedName("admin")
    val admin: Boolean,
    @SerializedName("maintain")
    val maintain: Boolean,
    @SerializedName("pull")
    val pull: Boolean,
    @SerializedName("push")
    val push: Boolean,
    @SerializedName("triage")
    val triage: Boolean
)