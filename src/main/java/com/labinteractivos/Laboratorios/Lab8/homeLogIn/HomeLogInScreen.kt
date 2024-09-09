package com.labinteractivos.laboratorios.Lab8.homeLogIn

import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.labinteractivos.ui.theme.LabInteractivosTheme
import com.labinteractivos.R


@Composable
fun HomeLogInRoute(
    onHomeLogInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    HomeLogInScreen(
        onHomeLogInClick = onHomeLogInClick,
        modifier = modifier
    )
}



@Composable
private fun HomeLogInScreen(
    onHomeLogInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(250.dp))
        Image (
            painter = painterResource(id = R.drawable.rickandmorty),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),

        )
        Button(
            onClick = onHomeLogInClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 40.dp)
                .fillMaxWidth()
                .height(40.dp)
        )  {
            Text("Entrar", color = MaterialTheme.colorScheme.onPrimary)
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Fabian Prado Dluzniewski - #23427",
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Preview
@Composable
private fun PreviewHomeLogInScreen() {
    LabInteractivosTheme {
        Surface {
            HomeLogInScreen(
                onHomeLogInClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}