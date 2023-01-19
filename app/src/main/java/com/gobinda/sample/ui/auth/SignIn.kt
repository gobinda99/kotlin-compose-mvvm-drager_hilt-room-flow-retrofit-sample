package com.gobinda.sample.ui.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gobinda.sample.utils.TripleState
import com.gobinda.sample.ui.*
import com.gobinda.sample.utils.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    vm: SignInViewModel = hiltViewModel(), onNavigateSignUp: () -> Unit, onNavigateHome: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState)}) {
        LaunchedEffect(uiState){
            uiState.message?.let {
                    snackBarHostState.showSnackbar(it)
            }
        }
        uiState.apply {
            if (success) {
                onNavigateHome()
            } else {
                SignInContent(
                    onNavigateSignUp,
                    email = email,
                    pass = pass,
                    loading = loading,
                    onValidate = {
                        focusManager.clearFocus()
                        vm.validate()
                    }) { _email, _pass ->
                    _email?.let { vm.setEmail(it) }
                    _pass?.let { vm.setPass(it) }
                }
            }
        }
    }

   /* uiState.apply {
        if (success) {
            onNavigateHome()
        } else {
            SignInContent(
                onNavigateSignUp,
                email = email,
                pass = pass,
                loading = loading,
                onValidate = {
                    focusManager.clearFocus()
                    vm.validate()
                }) { _email, _pass ->
                _email?.let { vm.setEmail(it) }
                _pass?.let { vm.setPass(it) }
            }
        }
    }*/
}

@Composable
fun SignInContent(
    onNavigateSignUp: () -> Unit,
    email: TripleState<String>,
    pass: TripleState<String>,
    loading: Boolean = false,
    onValidate: () -> Unit,
    onValueChange: (
        email: String?,
        pass: String?,
    ) -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (com1, com2, com3) = createRefs()
        createVerticalChain(com1, com2, com3, chainStyle = ChainStyle.Packed)

        Column(
            modifier = Modifier.constrainAs(com1) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(com2.top)
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "SignIn",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.heightIn(30.dp))

            InputField(
                label = "Email",
                value = email.value,
                isError = email.isError,
                errorMessage = stringResource(id = email.message),
                keyboardType = KeyboardType.Email
            ) {
                onValueChange(it, null)
            }
            Spacer10()
            PasswordField(label = "Password",
                value = pass.value,
                isError = pass.isError,
                errorMessage = stringResource(pass.message),
                onDone = {
                    onValidate()
                }) {
                onValueChange(null, it)
            }
            Spacer10()

        }

        ClickableText(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(com2) {
                top.linkTo(com1.bottom)
                end.linkTo(com1.end)
                bottom.linkTo(com3.top)
            }, text = AnnotatedString("Forgot password?"), onClick = { }, style = TextStyle(
            textAlign = TextAlign.End, fontSize = 14.sp, fontFamily = FontFamily.Default
        )
        )

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.constrainAs(com3) {
                top.linkTo(com2.bottom)
                start.linkTo(com1.start)
                end.linkTo(com1.end)
                bottom.linkTo(parent.bottom)
            }) {
            Spacer(modifier = Modifier.heightIn(30.dp))
            Box(modifier = Modifier.padding(60.dp, 0.dp, 60.dp, 0.dp)) {
                ActionButton(label = "Submit") {
                    onValidate()
                }
            }
            Spacer(modifier = Modifier.heightIn(30.dp))
            ClickableLabelText(
                modifier = Modifier,
                label = "Sign up here",
                textDecoration = TextDecoration.Underline
            ) {
                onNavigateSignUp()
            }
        }
    }

    if (loading) {
        Loading()
    }
}


@Preview
@Composable
fun SignInViewPreview() {
    Surface() {
        SignInContent(onNavigateSignUp = { },
            email = TripleState(""),
            pass = TripleState(""),
            onValidate = { },
            onValueChange = { _, _ -> })
    }

}