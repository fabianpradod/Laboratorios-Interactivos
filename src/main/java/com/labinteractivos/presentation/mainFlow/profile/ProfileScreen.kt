package com.laboratoriosinteractivos.lab10.presentation.mainFlow.profile


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laboratoriosinteractivos.lab10.ui.theme.RickAndMortyTheme
import com.laboratoriosinteractivos.lab10.R
import com.laboratoriosinteractivos.lab10.data.repository.LocalProfileRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun ProfileRoute(
    onLogOutClick: () -> Unit,
    localProfileRepository: LocalProfileRepository
) {
    ProfileScreen(
        onLogOutClick = onLogOutClick,
        localProfileRepository = localProfileRepository,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ProfileScreen(
    onLogOutClick: () -> Unit,
    localProfileRepository: LocalProfileRepository,
    modifier: Modifier = Modifier
) {
    var userName by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            localProfileRepository.userNameFlow.collect { savedName ->
                userName = savedName ?: "Fabian Prado"
            }
        }
    }

    Column(
        modifier = modifier
            .padding(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer, shape = CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fabian_foto),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Nombre:")
            Text(text = userName)
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onLogOutClick) {
            Text("Cerrar sesión")
        }
    }
}
//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//private fun PreviewProfileScreen() {
//    RickAndMortyTheme() {
//        Surface {
//            ProfileScreen(
//                onLogOutClick = { /*TODO*/ },
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}