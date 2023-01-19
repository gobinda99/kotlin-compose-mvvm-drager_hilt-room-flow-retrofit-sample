package com.gobinda.sample.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import timber.log.Timber

@Composable
fun ActionButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = label)
        /*Text(stringResource(id = label))*/
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PasswordField(
    label: String,
    value: String,
    isError: Boolean = false,
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Done,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        /*label = { Text(stringResource(id = label)) },*/
        label = { Text(label) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = onDone),
        isError = isError,
        supportingText = {
            if (isError) {
                errorMessage?.let {
                    Text(text = errorMessage)
                }
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun InputField(
    label: String,
    value: String,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        /*label = { Text(stringResource(id = label)) },*/
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = onDone),
        isError = isError,
        supportingText = {
            if (isError) {
                errorMessage?.let {
                    Text(text = errorMessage)
                }
            }
        }
    )
}

@Composable
fun Spacer10() {
    Spacer(modifier = Modifier.heightIn(10.dp))
}

@Composable
fun ClickableLabelText(
    modifier: Modifier,
    label: String,
    textDecoration: TextDecoration? = null,
    onClick: () -> Unit
) {
    ClickableText(
        text = AnnotatedString(label),
        onClick = { onClick.invoke() },
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            textDecoration = textDecoration,
        )
    )
}

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun Loading() {
    Dialog(
        onDismissRequest = {  },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
       ProgressBar()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}



@Preview
@Composable
fun ShowProgress(){
    ProgressBar()
}