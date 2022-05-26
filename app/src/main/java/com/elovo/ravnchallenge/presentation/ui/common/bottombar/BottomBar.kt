package com.elovo.ravnchallenge.presentation.ui.common.bottombar

import com.elovo.ravnchallenge.R

sealed class BottomBar(
    val route: String,
    val title: Int,
    val iconResource: Int
) {
    object People : BottomBar(
        route = "people",
        title = R.string.bb_people_label,
        iconResource = R.drawable.ic_person
    )
    object Favorites : BottomBar(
        route = "favorites",
        title = R.string.bb_favorites_label,
        iconResource = R.drawable.ic_favorite
    )
}
