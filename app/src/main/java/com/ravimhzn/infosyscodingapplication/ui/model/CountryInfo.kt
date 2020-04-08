package com.ravimhzn.infosyscodingapplication.ui.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

data class CountryInfo(
    val rows: List<Row?>? = null,
    val title: String? = null
)

@Parcelize
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
): Parcelable