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
            rows = toRows(),
            appBarTitle = title
        )
    }

    private fun toRows() =
        this.rows?.filter { it?.let { it1 -> checkIfTitleNull(it1) }!! }


    /**
     * Filtering list. Checking if there are null
     */
    private fun checkIfTitleNull(it: Row): Boolean {
        return it?.title != null && it?.description != null && it?.imageHref != null
    }
}

data class Row(
    val description: String? = null,
    val imageHref: String? = null,
    val title: String? = null
)