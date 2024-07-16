package com.tutor.loginandregistration.ui.signup

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutor.loginandregistration.ui.component.HeaderText
import com.tutor.loginandregistration.ui.component.LoginTextField
import com.tutor.loginandregistration.ui.login.defaultPadding

@Composable
fun RegisterScreen(
	modifier: Modifier = Modifier,
	onRegister: () -> Unit,
	onLogin: () -> Unit,
	onPolicy: () -> Unit,
	onPrivacy: () -> Unit,
) {
	val firstName = rememberSaveable { mutableStateOf("") }
	val lastName = rememberSaveable { mutableStateOf("") }
	val email = rememberSaveable { mutableStateOf("") }
	val password = rememberSaveable { mutableStateOf("") }
	val confPass = rememberSaveable { mutableStateOf("") }
	val checkBox = rememberSaveable { mutableStateOf(false) }
	val context = LocalContext.current
	var isPasswordValid by remember { mutableStateOf<Boolean>(false) }
	val isFieldNotEmpty = firstName.value.isNotEmpty()
			&& lastName.value.isNotEmpty()
			&& email.value.isNotEmpty()
			&& password.value.isNotEmpty()
			&& confPass.value.isNotEmpty()
			&& checkBox.value

	Column(
		modifier = modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
			.padding(defaultPadding),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		HeaderText(
			text = "Register",
			modifier = modifier
				.padding(vertical = defaultPadding)
				.align(Alignment.Start)
		)


		LoginTextField(
			value = firstName.value,
			labelText = "FirstName",
			leadingIcon = Icons.Default.Person,
			modifier = modifier.fillMaxWidth(),
			onValueChange = { firstName.value = it },
		)


		LoginTextField(
			value = lastName.value,
			labelText = "LastName",
			leadingIcon = Icons.Outlined.Person,
			modifier = modifier.fillMaxWidth(),
			onValueChange = { lastName.value = it },
		)


		LoginTextField(
			value = email.value,
			labelText = "Email Address",
			leadingIcon = Icons.Default.Email,
			modifier = modifier.fillMaxWidth(),
			onValueChange = { email.value = it },
		)

		LoginTextField(
			value = password.value,
			labelText = "Password",
			leadingIcon = Icons.Default.Lock,
			modifier = modifier.fillMaxWidth(),
			onValueChange = { password.value = it },
			keyboardType = KeyboardType.Password,
			visualTransformation = PasswordVisualTransformation()
		)
		LoginTextField(
			value = confPass.value,
			labelText = "Password",
			leadingIcon = Icons.Outlined.Lock,
			modifier = modifier.fillMaxWidth(),
			onValueChange = { confPass.value = it },
			keyboardType = KeyboardType.Password,
			visualTransformation = PasswordVisualTransformation()
		)

		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center
		) {
			Row(
				modifier = modifier,
				verticalAlignment = Alignment.CenterVertically,
//				horizontalArrangement = Arrangement.
			) {
				val privacyText = "Privacy"
				val policyText = "Policy"
				val annotatedString = buildAnnotatedString {
					withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
						append("I Agree with")
					}
					append(" ")
					withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
						pushStringAnnotation(tag = privacyText, privacyText)
						append(privacyText)
					}

					withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
						append(" And ")
					}

					withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
						pushStringAnnotation(tag = policyText, policyText)
						append(policyText)
					}
				}


				Checkbox(
					checked = checkBox.value,
					onCheckedChange = { checkBox.value = it },
				)
				ClickableText(text = annotatedString) { offset ->
					annotatedString.getStringAnnotations(offset, offset).forEach {
						when (it.tag) {
							privacyText -> {
								Toast.makeText(context, "Privacy Policy", Toast.LENGTH_SHORT)
									.show()
								onPrivacy()
							}

							policyText -> {
								Toast.makeText(
									context,
									"Policy Text Clicked",
									Toast.LENGTH_SHORT
								)
									.show()
								onPolicy()

							}
						}
					}
				}
//				Text(text = "I Agree to the ")
//				Text(text = "Terms ")
//				Text(text = "and ")
//				Text(text = "Conditions ")
			}
		}

		Spacer(Modifier.height(defaultPadding + 8.dp))
		AnimatedVisibility(visible = isPasswordValid) {
			Text(text = "Password is not Match", color = MaterialTheme.colorScheme.error)
		}

		Button(
			enabled = isFieldNotEmpty,
			modifier = modifier.fillMaxWidth(),
			onClick = {
				isPasswordValid = password.value != confPass.value
				if (!isPasswordValid) {
					onRegister()
				}
			}
		) {
			Text(text = "Register")
		}
		Row(
			modifier = modifier,
			verticalAlignment = Alignment.CenterVertically,
//			horizontalArrangement = Arrangement.Center
		) {
			Text(text = "Already have an account?")
			TextButton(
				onClick = onLogin
			) {
				Text(text = "Login")
			}
		}
	}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RegisterScreenPrev() {
	RegisterScreen(onRegister = {}, onLogin = {}, onPolicy = {}, onPrivacy = {})
}