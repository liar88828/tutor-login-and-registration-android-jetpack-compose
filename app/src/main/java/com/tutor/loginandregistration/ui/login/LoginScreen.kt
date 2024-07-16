package com.tutor.loginandregistration.ui.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutor.loginandregistration.R
import com.tutor.loginandregistration.ui.component.HeaderText
import com.tutor.loginandregistration.ui.component.LoginTextField
import com.tutor.loginandregistration.ui.theme.LoginAndRegistrationTheme

val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen(
	modifier: Modifier = Modifier,
//	navController: NavController
	onLogin: () -> Unit,
	onRegister: () -> Unit
) {
	val username = rememberSaveable { mutableStateOf("") }
	val password = rememberSaveable { mutableStateOf("") }
	val checkBox = rememberSaveable { mutableStateOf(false) }
	val context = LocalContext.current
	val isFieldsEmpty = username.value.isNotEmpty() && password.value.isNotEmpty()


	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(defaultPadding),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		HeaderText(
			text = "Login",
			modifier = modifier
				.padding(vertical = defaultPadding)
				.align(Alignment.Start)
		)

		LoginTextField(
			value = username.value,
			labelText = "Username",
			leadingIcon = Icons.Default.Person,
			modifier = modifier.fillMaxWidth(),
			onValueChange = { username.value = it },
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

		Spacer(modifier = modifier.height(itemSpacing))
		Row(
			modifier = modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
		) {
			Row(verticalAlignment = Alignment.CenterVertically) {
				Checkbox(
					checked = checkBox.value,
					onCheckedChange = { checkBox.value = !checkBox.value })
				Text(text = "Remember me", fontSize = 20.sp)
			}
			TextButton(onClick = {}) {
				Text(
					text = "Forgot Password?", fontSize = 16.sp,
//					color = Color.Red
				)
			}
		}

		Spacer(modifier = modifier.height(itemSpacing))
		Button(
			modifier = modifier.fillMaxWidth(),
//			colors = ButtonDefaults.buttonColors(Color.Red),
			onClick = onLogin,
			enabled = isFieldsEmpty
		) {
			Text(
				text = "Login",
				fontSize = 20.sp,
			)
		}
		AlternativeLoginOptions(
			onIconClick = { index ->
				when (index) {
					0 -> {
						Toast.makeText(context, "Login with Instagram", Toast.LENGTH_SHORT).show()
					}

					1 -> {
						Toast.makeText(context, "Login with Instagram", Toast.LENGTH_SHORT).show()

					}

					2 -> {
						Toast.makeText(context, "Login with Instagram", Toast.LENGTH_SHORT).show()

					}
				}
			},
			onSignUpClick = onRegister,
			modifier = modifier
				.fillMaxSize()
				.wrapContentSize(align = Alignment.BottomCenter)
		)
	}
}

@Composable
fun AlternativeLoginOptions(
	modifier: Modifier = Modifier,
	onIconClick: (index: Int) -> Unit,
	onSignUpClick: () -> Unit,
) {
	val iconList = listOf(
		R.drawable.icon_instagram,
		R.drawable.icon_google,
		R.drawable.icon_github
	)

	Column(
		modifier = modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(text = "Or login with")
		Spacer(modifier = Modifier.height(itemSpacing))

		Row(horizontalArrangement = Arrangement.SpaceEvenly) {
			iconList.forEachIndexed { index, icon ->
				Icon(
					painter = painterResource(icon),
					contentDescription = "alternative Login",
					modifier = Modifier
						.size(32.dp)
						.clickable {
							onIconClick(index)
						}
						.clip(CircleShape)
				)
				Spacer(modifier = Modifier.width(defaultPadding))
			}
		}
		Spacer(modifier = Modifier.height(itemSpacing))
		Row(
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text("Dont have an account?", fontSize = 16.sp)
			Spacer(modifier = Modifier.height(defaultPadding))
			TextButton(onClick = onSignUpClick) {
				Text(text = "Sign Up", fontSize = 16.sp/* color = Color.Red*/)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPrev() {
	LoginAndRegistrationTheme {
		LoginScreen(
			onLogin = {}, onRegister = {}
		)
	}
}