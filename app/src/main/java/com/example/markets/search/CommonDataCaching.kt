package com.example.markets.search

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@Entity(tableName = "CommonDataCashing", indices = [Index(value = ["searched_item"], unique = true)])
data class CommonDataCaching(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "searched_item")
    val searched_item: String,
    @ColumnInfo(name = "category_id")
    val category_id: String?,
    @ColumnInfo(name = "subcategory")
    val subcategory: String?,
    @ColumnInfo(name = "root")
    val root: String?
) : Parcelable