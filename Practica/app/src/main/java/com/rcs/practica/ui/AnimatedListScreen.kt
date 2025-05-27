package com.rcs.practica.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import com.rcs.practica.ui.components.AnimationScrollitem

@Composable
fun AnimatedListScreen() {
    val items = remember { (1..20).map { "Hello I am card $it" } }
    val listState = rememberLazyListState()

    val cardHeightDp = 120.dp // altura estimada de cada tarjeta
    val cardHeightPx = with(LocalDensity.current) { cardHeightDp.toPx() }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(items) { index, item ->
            val layoutInfo = listState.layoutInfo
            val itemInfo = layoutInfo.visibleItemsInfo.find { it.index == index }

            // Cálculo del scale basado en su posición Y
            val scale = if (itemInfo != null) {
                val distanceFromTop = itemInfo.offset.toFloat()
                val visibleAreaHeight = layoutInfo.viewportEndOffset.toFloat()

// Reducir escala sólo si el ítem está cerca de salir por arriba
                when {
                    distanceFromTop < 0 -> 0f // totalmente fuera
                    distanceFromTop < cardHeightPx -> {
                        // escalar entre 0f y 1f
                        (distanceFromTop / cardHeightPx).coerceIn(0f, 1f)
                    }
                    else -> 1f // completamente visible
                }
            } else 1f

            AnimationScrollitem(
                title = item,
                subtitle = "I am subtitle of ${item.takeLast(1)}",
                scale = scale
            )
        }
    }
}
/*funca
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.rcs.practica.model.generateSampleItems

import com.rcs.practica.ui.components.AnimatedScrollItem
import com.rcs.practica.utils.isItemVisible


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedListScreen() {
    val items = remember { generateSampleItems() }
    val listState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("Lista Animada") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
            modifier = Modifier.height(64.dp)
        )

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(items, key = { _, item -> item.id }) { index, item ->
                val isVisible = listState.isItemVisible(index)
                AnimatedScrollItem(item = item, visible = isVisible)
            }
        }
    }
}
*//*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rcs.practica.model.generateSampleItems
import com.rcs.practica.ui.components.AnimatedScrollItem
import com.rcs.practica.utils.isItemVisible

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedListScreen() {
    val items = remember { generateSampleItems() }
    val listState = rememberLazyListState()
    val visibleItems by remember {
        derivedStateOf {
            val visible = listState.layoutInfo.visibleItemsInfo.map { it.index }
            visible
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista Animada") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                modifier = Modifier
                    .statusBarsPadding()
                    .height(56.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp), // Espacio adicional
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(items, key = { _, item -> item.id }) { index, item ->
                    AnimatedScrollItem(
                        item = item,
                        visible = index in visibleItems
                    )
                }
            }
        }
    }
}*/