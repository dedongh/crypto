package com.engineerskasa.crypto.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "cryptocurrency")
data class CryptoCurrency(
    @SerializedName("id")
    @PrimaryKey
    val id: String,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("symbol")
    val symbol: String = "",

    @SerializedName("rank")
    val rank: String? = null,

    @SerializedName("price_usd")
    val priceUsd: String = "",

    @SerializedName("price_btc")
    val priceBtc: String = "",

    @SerializedName("24h_volume_usd")
    val jsonMember24hVolumeUsd: String = "",

    @SerializedName("market_cap_usd")
    val marketCapUsd: String = "",

    @SerializedName("available_supply")
    val availableSupply: String = "",

    @SerializedName("total_supply")
    val totalSupply: String = "",

    @SerializedName("max_supply")
    val maxSupply: String = "",

    @SerializedName("percent_change_1h")
    val percentChange1h: String = "",

    @SerializedName("percent_change_24h")
    val percentChange24h: String = "",

    @SerializedName("percent_change_7d")
    val percentChange7d: String = "",

    @SerializedName("last_updated")
    val lastUpdated: Long = 0
): Serializable
