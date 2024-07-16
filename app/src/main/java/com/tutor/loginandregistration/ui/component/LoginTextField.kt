package com.tutor.loginandregistration.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginTextField(
	modifier: Modifier = Modifier,
	value: String,
	labelText: String,
	onValueChange: (String) -> Unit,
	leadingIcon: ImageVector? = null,
	keyboardType: KeyboardType = KeyboardType.Text,
	visualTransformation: VisualTransformation = VisualTransformation.None
) {
	OutlinedTextField(
		modifier = modifier,
		value = value,
		onValueChange = onValueChange,
		label = { Text(text = labelText) },
		leadingIcon = {
			if (leadingIcon != null) Icon(
				imageVector = leadingIcon, contentDescription = null
			)
		},
		keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
		visualTransformation = visualTransformation,
		shape = RoundedCornerShape(30)
	)
}

@Preview(showBackground = true)
@Composable
private fun LoginTextFieldPrev() {
	LoginTextField(
		value = "",
		onValueChange = {},
		labelText = "Password",
	)
}