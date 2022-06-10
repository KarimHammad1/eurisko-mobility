package com.example.euriskomob.screens

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.euriskomob.component.EmailInput
import com.example.euriskomob.component.PasswordInput
import com.example.euriskomob.navigation.AppScreens
import com.example.euriskomob.utils.Constants


@Composable
fun AppLoginScreen(navController: NavController){
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            UserForm(navController)

        }

    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    navController: NavController,
    loading: Boolean = false,
    onDone: (String, String) -> Unit = { email, pwd ->}
)
{
    val checkedState = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()

    }
    val modifier = Modifier
        .height(250.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())


    Column(modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {

        EmailInput(
            emailState = email, enabled = !loading,
            onAction = KeyboardActions {
                passwordFocusRequest.requestFocus()
            },
        )
        PasswordInput(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "Password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
            })
        val  checkedValue = remember { mutableStateOf(false) }
        Row(){
            Text(text = "Remember me", modifier = Modifier.align(Alignment.CenterVertically))
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                    checkedValue.value = it
                }
            )
        }

        SubmitButton(
            textId = "Login",
            loading = loading,
            validInputs = valid
        ){
            keyboardController?.hide()
            val userEmail = email.value.trim()
            val userPassword = password.value.trim()


            if ((userEmail == Constants.USER1_EMAIL) && (userPassword ==Constants.USER1_PASSWORD) &&(checkedValue.value)
                ||
                (userEmail == Constants.USER2_EMAIL) && (userPassword == Constants.USER2_PASSWORD)&&(checkedValue.value)){
                if(checkedValue.value){
                    val pref: SharedPreferences = context.getSharedPreferences("checkbox", MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = pref.edit()
                    editor.putString("remember", "true")
                    editor.apply()

                }else if(!checkedValue.value){
                    val pref: SharedPreferences = context.getSharedPreferences("checkbox", MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = pref.edit()
                    editor.putString("remember", "false")
                    editor.apply()

                }
                navController.navigate(AppScreens.MainScreen.name)
            }
                else{
                Toast.makeText(context,"incorrect email or password",Toast.LENGTH_LONG).show()
            }


        }


    }

}

@Composable
fun SubmitButton(textId: String,
                 loading: Boolean,
                 validInputs: Boolean,
                 onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        enabled = !loading && validInputs,
        shape = CircleShape
    ) {
        if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp))

    }

}
@Composable
fun CheckBox() {
    val context = LocalContext.current

}