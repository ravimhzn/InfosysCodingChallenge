package com.ravimhzn.infosyscodingapplication.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ravimhzn.infosyscodingapplication.ui.model.Row

@Database(entities = [Row::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME: String = "country_db"
    }

    abstract fun getCountryDetailsFromDbDao(): CountryInfoDao
}