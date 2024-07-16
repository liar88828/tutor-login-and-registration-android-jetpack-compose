package com.tutor.loginandregistration.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HeaderText(
	modifier: Modifier = Modifier,
	text: String,
) {
	Text(
		text = text,
		style = MaterialTheme.typography.displayMedium,
		fontWeight = FontWeight.Bold,
		modifier = modifier
	)

}