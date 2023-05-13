package com.example.markets.baseUtils.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.markets.search.CommonDataCaching

@Dao
interface RecentSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(commonDataCaching: CommonDataCaching)

    @Query("SELECT * From CommonDataCashing ORDER BY id DESC")
    fun getRecentSearchItem():LiveData<List<CommonDataCaching>>

    @Query("SELECT count(*) from CommonDataCashing")
    fun getCount() :LiveData<Int>

    @Delete
    suspend fun deleteLastRecord(commonDataCaching: CommonDataCaching)

    @Query("SELECT category_id from CommonDataCashing where searched_item == :searchedItem")
    fun getCategoryIdForSelectedSearchItem(searchedItem : String) :LiveData<String>

    @Query("delete from CommonDataCashing")
    suspend fun deleteRecentSearches()
}