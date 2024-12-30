package uk.ac.tees.mad.bp.ui.mainapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import uk.ac.tees.mad.bp.authentication.viewmodel.AuthViewmodel

@Composable
fun HomeScreen(
    authViewmodel: AuthViewmodel,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Button(
            onClick = {
                navController.navigate("profile_screen")
            }
        ) {
            Text(
                text = "To Profile Screen"
            )
        }
    }
}