package com.example.contentprovoderwithloader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contentprovoderwithloader.ui.theme.ContactManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Setting the theme for the entire application
            ContactManagerTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Calling the main screen composable function
                    ContactManagerScreen()
                }
            }
        }
    }
}

@Composable
fun ContactManagerScreen() {
    var contactName by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var contacts by remember { mutableStateOf(emptyList<Contact>()) }


    Column(modifier = Modifier.padding(16.dp)) {
        // Input fields for contact name and number
        TextField(
            value = contactName,
            onValueChange = { contactName = it },
            label = { Text("Contact Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = contactNumber,
            onValueChange = { contactNumber = it },
            label = { Text("Contact Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Row to contain buttons for fetching contacts and adding a new contact
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Button to fetch contacts only if the list is empty
            Button(
                onClick = {
                    if (contacts.isEmpty()) {
                        contacts = loadContacts()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Fetch Contacts")
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Button to add a new contact and clear input fields
            Button(
                onClick = {
                    contacts += Contact(contactName, contactNumber)
                    // Clearing input fields after adding contact
                    contactName = ""
                    contactNumber = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Add Contact")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // LazyColumn to display the list of contacts
        LazyColumn {
            items(contacts) { contact ->
                ContactItem(contact)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // About section displaying student information
        Text("Student Name: Praful Rana")
        Text("Student ID: 301360320")
    }
}

@Composable
fun ContactItem(contact: Contact) {
    // Row to display individual contact information
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${contact.name}: ${contact.number}",
            modifier = Modifier.padding(8.dp)
        )
    }
}

data class Contact(val name: String, val number: String)

fun loadContacts(): List<Contact> {
    // Dummy implementation to simulate fetching contacts
    return listOf(

    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ContactManagerTheme {
        ContactManagerScreen()
    }
}
