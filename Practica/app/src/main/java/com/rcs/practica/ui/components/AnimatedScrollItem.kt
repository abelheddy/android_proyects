package com.rcs.practica.ui.components
/*funca
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rcs.practica.model.ListItem


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedScrollItem(item: ListItem, visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + scaleIn(initialScale = 0.8f),
        exit = fadeOut() + scaleOut(targetScale = 0.8f)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(item.color))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = item.title, style = MaterialTheme.typography.titleLarge)
                Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}*/

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.rcs.practica.model.ListItem
import androidx.compose.ui.Alignment

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedScrollItem(
    item: ListItem,
    visible: Boolean
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(400)) +
                scaleIn(initialScale = 0.7f, animationSpec = tween(400)) +
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { if (item.id % 2 == 0) -screenWidth.value.toInt()/2 else screenWidth.value.toInt()/2 }
                ),
        exit = fadeOut(animationSpec = tween(500)) +
                scaleOut(
                    targetScale = 0f,
                    animationSpec = tween(500, easing = FastOutSlowInEasing)
                ) +
                shrinkHorizontally(
                    animationSpec = tween(500),
                    shrinkTowards = Alignment.CenterHorizontally
                ),
        modifier = Modifier.animateContentSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(item.color)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = item.title, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}