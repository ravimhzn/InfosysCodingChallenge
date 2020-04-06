package com.ravimhzn.infosyscodingapplication.ui.model

data class CountryInfo(
    val rows: List<Row?>? = null,
    val title: String? = null
) {
    /**
     * We could have used this same class but it's a violation of Single responsibility principle.
     * It's easier to read code as well like this
     */
    fun toCountryInfoDataModel(): CountryInfoDataModel {
        return CountryInfoDataModel(
            rows = rows,
            title = title
        )
    }
}