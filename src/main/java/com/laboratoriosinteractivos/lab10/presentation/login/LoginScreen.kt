package com.laboratoriosinteractivos.lab10.presentation.login


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laboratoriosinteractivos.lab10.R
import com.laboratoriosinteractivos.lab10.ui.theme.RickAndMortyTheme

@Composable
fun LoginRoute(
    onLoginClick: () -> Unit,
) {
    LoginScreen(
        onLoginClick = onLoginClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun LoginScreen(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.rickandmorty), contentDescription = "Logo")
            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Iniciar sesión")
            }
        }
        Text(
            text = "Fabian Prado Dluzniewski - #23427",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    RickAndMortyTheme {
        Surface {
            LoginScreen(
                onLoginClick = { /*TODO*/ },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}