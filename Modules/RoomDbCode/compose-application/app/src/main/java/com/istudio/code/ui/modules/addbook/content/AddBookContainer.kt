package com.istudio.code.ui.modules.addbook.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.istudio.code.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookContainer(modifier: Modifier = Modifier, onBackPress: () -> Unit) {

    // Context
    val cxt = LocalContext.current
    // Coroutine to handle the animation
    val coroutineScope = rememberCoroutineScope()

    // Scroll behaviour
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = cxt.getString(R.string.str_add_book))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            // Close
                            onBackPress.invoke()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = cxt.getString(R.string.str_navigate_back)
                        )
                    }
                },
                scrollBehavior = scrollBehaviour
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                // Current Screen Content
                var titleState by remember { mutableStateOf("") }
                var descriptionState by remember { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = titleState,
                    onValueChange = { updatedText -> titleState = updatedText },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = cxt.getString(R.string.title_enter_book_title)) },
                    placeholder = { Text(text = cxt.getString(R.string.str_title)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = descriptionState,
                    onValueChange = { updatedText -> descriptionState = updatedText },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = cxt.getString(R.string.title_enter_book_description)) },
                    placeholder = { Text(text = cxt.getString(R.string.str_description)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /** Your action **/ }
                ) {
                    Text(text = cxt.getString(R.string.str_add))
                }

            }

        }

    }
}