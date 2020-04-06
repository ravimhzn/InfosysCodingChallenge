package com.ravimhzn.infosyscodingapplication.ui.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

data class CountryInfo(
    val rows: List<Row?>? = null,
    val title: String? = null
)

@Entity(tableName = "country_details")
data class Row(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @NotNull
    @ColumnInfo(name = "description")
    val description: String? = null,

    @NotNull
    @ColumnInfo(name = "imageHref")
    val imageHref: String? = null,

    @NotNull
    @ColumnInfo(name = "title")
    val title: String? = null
)