package com.rcs.practica.model



data class ListItem(
    val id: Int,
    val title: String,
    val description: String,
    val color: Long
)

fun generateSampleItems(): List<ListItem> {
    return List(20) { i ->
        ListItem(
            id = i,
            title = "Item $i",
            description = "Descripción del item $i",
            color = listOf(
                0xFFFFAB91, 0xFF80DEEA, 0xFFA5D6A7, 0xFFFFE082, 0xFFCE93D8
            ).random()
        )
    }
}