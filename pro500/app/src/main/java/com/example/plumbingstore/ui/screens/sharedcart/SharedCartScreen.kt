package com.example.plumbingstore.ui.screens.sharedcart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.plumbingstore.data.model.SharedCart
import com.example.plumbingstore.data.model.SharedCartStatus

@Composable
fun SharedCartScreen(
    navController: NavController,
    viewModel: SharedCartViewModel = hiltViewModel()
) {
    val sharedCarts by viewModel.sharedCarts.collectAsState()
    var showCreateDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { showCreateDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Создать")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Создать совместную покупку")
        }

        if (sharedCarts.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("У вас нет совместных покупок")
            }
        } else {
            LazyColumn {
                items(sharedCarts) { cart ->
                    SharedCartItem(
                        sharedCart = cart,
                        onDelete = { /* TODO: Удаление корзины */ }
                    )
                }
            }
        }
    }

    if (showCreateDialog) {
        CreateSharedCartDialog(
            onDismiss = { showCreateDialog = false },
            onCreate = { name ->
                viewModel.createSharedCart(name)
                showCreateDialog = false
            }
        )
    }
}

@Composable
fun SharedCartItem(
    sharedCart: SharedCart,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = sharedCart.name,
                    style = MaterialTheme.typography.h6
                )
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Удалить")
                }
            }

            Text(
                text = "Участники: ${sharedCart.participants.size}",
                style = MaterialTheme.typography.body1
            )

            Text(
                text = "Общая сумма: ${sharedCart.totalAmount} ₽",
                style = MaterialTheme.typography.body1
            )

            Text(
                text = "Статус: ${sharedCart.status.name}",
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun CreateSharedCartDialog(
    onDismiss: () -> Unit,
    onCreate: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Создать совместную покупку") },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Название") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = { onCreate(name) },
                enabled = name.isNotBlank()
            ) {
                Text("Создать")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
} 