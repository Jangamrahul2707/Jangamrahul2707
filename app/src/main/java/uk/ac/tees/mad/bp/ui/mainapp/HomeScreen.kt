package uk.ac.tees.mad.bp.ui.mainapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.tees.mad.bp.authentication.viewmodel.AuthViewmodel
import uk.ac.tees.mad.bp.mainapp.model.KitItem
import uk.ac.tees.mad.bp.mainapp.viewmodel.MainViewmodel
import uk.ac.tees.mad.bp.ui.theme.BePreparedTheme
import uk.ac.tees.mad.bp.ui.theme.metamorphousFamily
import uk.ac.tees.mad.bp.ui.theme.poppinsFam

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainViewmodel: MainViewmodel,
    authViewmodel: AuthViewmodel,
    navController: NavHostController,
    modifier: Modifier = Modifier
){

    val allKit by mainViewmodel.allKits.collectAsState()

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Be Prepared",
                        fontSize = 25.sp,
                        fontFamily = metamorphousFamily
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            authViewmodel.fetchCurrentUser()
                            navController.navigate("profile_screen")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Profile Screen"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    navController.navigate("add_new_kit")
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add new Habit"
                )
            }
        }
    ){innerpadding->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            LazyColumn {
                items(allKit){kitItem->
                    KitItem(kitItem, navController)
                }
            }
        }
    }
}

@Composable
fun KitItem(
    kitItem: KitItem,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            navController.navigate("kit_item_details")
        }
    ) {
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ){
            Text(
                modifier = modifier
                    .fillMaxWidth(),
                text = kitItem.title,
                fontSize = 18.sp,
                fontFamily = poppinsFam,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            kitItem.item.forEach {item->
                Text(
                    text = item,
                    fontFamily = poppinsFam,
                    fontSize = 15.sp
                )
            }

        }
    }
}