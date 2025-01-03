package uk.ac.tees.mad.bp.ui.mainapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import uk.ac.tees.mad.bp.mainapp.model.KitItem
import uk.ac.tees.mad.bp.mainapp.viewmodel.MainViewmodel
import uk.ac.tees.mad.bp.ui.theme.poppinsFam


@Composable
fun AddNewKit(
    mainViewmodel: MainViewmodel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    var title by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }
    var newItem by remember { mutableStateOf("") }
    val item: MutableList<String> = remember { mutableListOf() }

    if (showAddDialog){
        AlertDialog(
            onDismissRequest = {
                showAddDialog = false
                newItem = ""
            },
            title = {
                Text(
                    text = "Add new Item to the kit!!",
                    fontSize = 16.sp,
                    fontFamily = poppinsFam
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = newItem,
                        onValueChange = {
                            newItem = it
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            item += newItem
                            showAddDialog = false
                            newItem = ""
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Add it!",
                            fontFamily = poppinsFam,
                            fontSize = 13.sp
                        )
                    }
                }
            },
            confirmButton = {},
            dismissButton = {}
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Spacer(modifier.weight(2f))
        Text(
            text = "Add New Kit",
            fontSize = 30.sp,
            fontFamily = poppinsFam
        )

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            }
        )

        LazyColumn {
            items(item){
                Text(
                    modifier = modifier
                        .fillMaxWidth(0.8f)
                        .border(width = 2.dp, shape = RoundedCornerShape(15.dp), color = Color.Black)
                        .padding(10.dp),
                    text = it,
                    fontSize = 15.sp,
                    fontFamily = poppinsFam
                )
            }
        }

        Button(
            onClick = {
                showAddDialog = true
            }
        ) {
            Text(
                text = "Add New Item!!",
                fontSize = 16.sp,
                fontFamily = poppinsFam
            )
        }

        Button(
            onClick = {
                val newKit = KitItem(title, item)
                mainViewmodel.addNewKit(newKit)
                navController.popBackStack()
            }
        ) {
            Text(
                text = "Add Kit!!",
                fontSize = 16.sp,
                fontFamily = poppinsFam
            )
        }

        Spacer(modifier.weight(10f))
    }
}