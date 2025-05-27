package com.rcs.practica.utils

import androidx.compose.foundation.lazy.LazyListState



fun LazyListState.isItemVisible(index: Int): Boolean {
    return layoutInfo.visibleItemsInfo.any { it.index == index }
}

