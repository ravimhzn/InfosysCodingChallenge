package com.ravimhzn.infosyscodingapplication.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ravimhzn.infosyscodingapplication.ui.model.Row

@Dao
interface CountryInfoDao {

    @Insert
    fun insertIntoDbCountryDetails(vararg users: Row?)

    @get:Query("SELECT * FROM country_details")
    val getCountryDetailsFromDb: List<Row>

    @Query("DELETE FROM country_details")
    fun deleteAll()
}