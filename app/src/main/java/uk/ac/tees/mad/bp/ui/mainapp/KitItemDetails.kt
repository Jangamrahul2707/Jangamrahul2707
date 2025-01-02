package uk.ac.tees.mad.bp.ui.mainapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.bp.ui.theme.BePreparedTheme
import uk.ac.tees.mad.bp.ui.theme.poppinsFam


@Composable
fun KitItemDetails(
    modifier: Modifier = Modifier
) {

    var showAddDialog by remember { mutableStateOf(false) }
    var newItem by remember { mutableStateOf("") }

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
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Column (
                modifier = modifier
                    .padding(10.dp)
            ){
                Text(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = "Earthquake Kit",
                    fontSize = 25.sp,
                    fontFamily = poppinsFam,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Water Container",
                    fontFamily = poppinsFam,
                    fontSize = 17.sp
                )

                Text(
                    text = "Food",
                    fontFamily = poppinsFam,
                    fontSize = 17.sp
                )

                Text(
                    text = "Duct Tape",
                    fontFamily = poppinsFam,
                    fontSize = 17.sp
                )

                Text(
                    text = "FlashLight",
                    fontFamily = poppinsFam,
                    fontSize = 17.sp
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
    }
}


