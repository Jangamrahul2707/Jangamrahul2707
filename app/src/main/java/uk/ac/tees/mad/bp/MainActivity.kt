package uk.ac.tees.mad.bp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.mad.bp.navigation.CentralNavigation
import uk.ac.tees.mad.bp.authentication.viewmodel.AuthViewmodel
import uk.ac.tees.mad.bp.mainapp.viewmodel.MainViewmodel
import uk.ac.tees.mad.bp.ui.theme.BePreparedTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authViewmodel by viewModels<AuthViewmodel>()
    private val mainViewmodel by viewModels<MainViewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            BePreparedTheme {
                CentralNavigation(
                    mainViewmodel,
                    authViewmodel,
                    navController
                )
            }
        }
    }
}
