package com.example.hca_stackexchange.model


import com.google.gson.annotations.SerializedName

data class StackExchangeX(
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("items")
    val items: List<ItemX>,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)