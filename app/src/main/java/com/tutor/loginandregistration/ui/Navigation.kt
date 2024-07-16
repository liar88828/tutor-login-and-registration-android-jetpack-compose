package com.tutor.loginandregistration.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tutor.loginandregistration.ui.login.LoginScreen
import com.tutor.loginandregistration.ui.signup.PolicyScreen
import com.tutor.loginandregistration.ui.signup.PrivacyScreen
import com.tutor.loginandregistration.ui.signup.RegisterScreen

sealed class Route {
	data class LoginScreen(val name: String = "Login") : Route()
	data class RegisterScreen(val name: String = "Register") : Route()
	data class PrivacyScreen(val name: String = "Privacy") : Route()
	data class PolicyScreen(val name: String = "Policy") : Route()
	data class HomeScreen(val name: String = "Home") : Route()

}

@Composable
fun NavigationScreen(
	modifier: Modifier = Modifier,
	navHostController: NavHostController,
) {
	NavHost(
		navController = navHostController,
		startDestination = Route.LoginScreen().name,
	) {
		composable(route = Route.LoginScreen().name) {
			LoginScreen(
				onLogin = { navHostController.navigate(Route.HomeScreen().name) },
				onRegister = { navHostController.navigate(Route.RegisterScreen().name) }
			)
		}
		composable(route = Route.RegisterScreen().name) {
			RegisterScreen(
				onRegister = { navHostController.navigate(Route.HomeScreen().name) },
				onPolicy = { navHostController.navigate(Route.PolicyScreen().name) },
				onLogin = { navHostController.navigate(Route.LoginScreen().name) },
				onPrivacy = { navHostController.navigate(Route.PrivacyScreen().name) }
			)
		}
		composable(route = Route.PrivacyScreen().name) {
			PrivacyScreen { navHostController.navigateUp() }
		}
		composable(route = Route.PolicyScreen().name) {
			PolicyScreen { navHostController.navigateUp() }
		}
		composable(route = Route.HomeScreen().name) {
			HomeScreen(
			)
		}
	}

}