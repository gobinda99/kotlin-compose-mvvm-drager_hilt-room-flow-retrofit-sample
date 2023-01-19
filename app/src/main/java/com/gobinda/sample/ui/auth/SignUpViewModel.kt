package com.gobinda.sample.ui.auth

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gobinda.sample.R
import com.gobinda.sample.utils.TripleState
import com.gobinda.sample.data.User
import com.gobinda.sample.data.source.UserRepository
import com.gobinda.sample.utils.isValidEmail
import com.gobinda.sample.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SignUpState(
    val name: TripleState<String> = TripleState(""),
    val email: TripleState<String> = TripleState(""),
    val pass: TripleState<String> = TripleState(""),
    val cPass: TripleState<String> = TripleState(""),
    val loading: Boolean = false,
    val success: Boolean = false,
    val message : String? = null
)

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: UserRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _uiState = MutableStateFlow(SignUpState())

    val uiState: StateFlow<SignUpState>
        get() = _uiState

    fun validate() {
        viewModelScope.launch {
            var newState = _uiState.value
            with(_uiState.value) {
                if (name.value.isBlank()) {
                    newState = copy(name = name.copy(isError = true, message = R.string.name_empty))
                }

                if (email.value.isBlank()) {
                    newState =
                        newState.copy(email = email.copy(isError = true, message = R.string.email_empty))

                } else  if (!email.value.isValidEmail) {
                    newState =
                        newState.copy(email = email.copy(isError = true, message = R.string.email_incorrect))

                }
                if (pass.value.isBlank()) {
                    newState =
                        newState.copy(pass = pass.copy(isError = true, message = R.string.password_empty))
                } else if (!pass.value.isValidPassword) {
                    newState =
                        newState.copy(pass = pass.copy(isError = true, message = R.string.password_should))
                }

                if (cPass.value.isBlank()) {
                    newState =
                        newState.copy(cPass = cPass.copy(isError = true, message = R.string.confirm_password_empty))
                } else if (pass.value != cPass.value) {
                    newState = newState.copy(
                        cPass = cPass.copy(
                            isError = true, message = R.string.password_not_matched
                        )
                    )
                }
            }

            _uiState.value = newState

            if (!(newState.name.isError || newState.email.isError || newState.pass.isError || newState.cPass.isError)) {
                newState = newState.copy(loading = true)
                _uiState.value = newState

                val handler = CoroutineExceptionHandler { _, exception ->
                    newState = newState.copy(loading = false, success = false, message = "Email all ready exist!")
                    _uiState.value = newState
                }

                viewModelScope.launch(handler) {
                    delay(3000)

                    repository.insertUser(with(_uiState.value){ User(email.value, name = name.value, pass.value)}).also {
                            newState = newState.copy(loading = false, success = true)
                            _uiState.value = newState
                    }

                }
            }

        }

    }

    fun setName(_name: String) {
        with(_uiState.value) {
            _uiState.value = copy(name = name.copy(_name, isError = false), message = null)
        }
    }

    fun setEmail(_email: String) {
        with(_uiState.value) {
            _uiState.value = copy(email = email.copy(_email, isError = false), message = null)
        }
    }

    fun setPass(_pass: String) {
        with(_uiState.value) {
            _uiState.value = copy(pass = pass.copy(_pass, isError = false), message = null)
        }
    }

    fun setConfirmPass(_cPass: String) {
        with(_uiState.value) {
            _uiState.value = copy(cPass = cPass.copy(_cPass, isError = false), message = null)
        }
    }

}