package uk.ac.tees.mad.bp.ui.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import uk.ac.tees.mad.bp.R
import uk.ac.tees.mad.bp.authentication.viewmodel.AuthViewmodel
import uk.ac.tees.mad.bp.ui.theme.ubuntuFamily


@Composable
fun SplashScreen(
    authViewmodel: AuthViewmodel,
    navController: NavHostController
){

    val isLoggedIn by authViewmodel.isLoggedIn.collectAsState()

    LaunchedEffect(Unit) {
        delay(3000L)
        if (isLoggedIn){
            navController.navigate("home_graph"){
                popUpTo(navController.graph.startDestinationId){
                    inclusive=true
                }
            }
        }else{
            navController.navigate("auth_graph"){
                popUpTo(navController.graph.startDestinationId){
                    inclusive=true
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0.2f to Color(0xFFF7A6D0),
                    0.4f to Color(0xFF94bbe9),
                    1.0f to Color(0xFFeeaeca)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(5.dp)
        ){
            Column(
                modifier = Modifier.padding(7.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier
                        .height(200.dp)
                        .aspectRatio(1f, matchHeightConstraintsFirst = true)
                        .padding(1.dp)
                        .clip(CircleShape),
                    painter = painterResource(R.drawable.beprepared),
                    contentDescription = "App Logo"
                )
                Text(
                    text = "Be Prepared",
                    fontSize = 25.sp,
                    fontFamily = ubuntuFamily
                )
            }
        }
    }
}
