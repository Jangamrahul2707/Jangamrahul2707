package uk.ac.tees.mad.bp.authentication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import uk.ac.tees.mad.bp.authentication.viewmodel.AuthViewmodel
import uk.ac.tees.mad.bp.ui.mainapp.HomeScreen
import uk.ac.tees.mad.bp.ui.authentication.LogInScreen
import uk.ac.tees.mad.bp.ui.authentication.SignUpScreen
import uk.ac.tees.mad.bp.ui.authentication.SplashScreen


@Composable
fun CentralNavigation(
    authViewmodel: AuthViewmodel,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = "splash_graph"
    ) {
        navigation(
            startDestination = "splash_screen",
            route = "splash_graph"
        ){
            composable("splash_screen") {
                SplashScreen(authViewmodel, navController)
            }
        }

        navigation(
            startDestination = "login_screen",
            route = "auth_graph"
        ){
            composable("login_screen") {
                LogInScreen(authViewmodel, navController)
            }

            composable("signup_screen") {
                SignUpScreen(authViewmodel, navController)
            }
        }

        navigation(
            startDestination = "home_screen",
            route = "home_graph"
        ) {
            composable("home_screen") {
                HomeScreen(authViewmodel, navController)
            }
        }
    }
}