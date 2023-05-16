package com.rcpemau.tiendaonline.screen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import java.util.Locale.US
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.Date
import java.util.Locale

@Composable
fun BodyContent() {

    var tabIndice by remember { mutableStateOf(0) }
    val pestañas = listOf("Login","Registarse")
    Column(modifier = Modifier.fillMaxWidth()) {

        TabRow(selectedTabIndex = tabIndice) {
            pestañas.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndice == index,
                    onClick = { tabIndice = index}
                )
            }
        }
        when (tabIndice) {
             0 -> Login()
             1 -> SignUp()
        }

    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp() {

    FormText(texto = "Dirección email:")
    EnterFormText("email")

    FormText(texto = "Nombre:")
    EnterFormText("nombre")

    FormText(texto = "Apellidos:")
    EnterFormText("apellidos")

    FormText(texto = "Contraseña:")
    EnterFormText("contraseña")

    FormText(texto = "Fecha de nacimiento:")
    Calender()

    Button(onClick = { /*TODO*/ }) {

        Text(text = "Aceptar")
    }

}

@Composable
fun Login () {

    FormText(texto = "Dirección email:")
    EnterFormText("email")

    FormText(texto = "Contraseña:")
    EnterFormText("contraseña")


    Button(onClick = { /*TODO*/ }) {

        Text(text = "Aceptar")
    }

}

@Composable
fun Calender() {

    var datePicked by remember { mutableStateOf("") }

    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = java.util.Calendar.getInstance()
    year = calendar.get(java.util.Calendar.YEAR)
    month = calendar.get(java.util.Calendar.MONTH)
    day = calendar.get(java.util.Calendar.DAY_OF_MONTH)
    calendar.time = Date()


    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            datePicked = "$dayOfMonth/$month/$year"
        }, year, month, day
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
            .padding(top = 10.dp)
            .border(0.5.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
            .clickable {
                datePickerDialog.show()
            }
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            val (lable, iconView) = createRefs()

            Text(
                text = datePicked ?: "Date Picker",
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(lable) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(iconView.start)
                        width = Dimension.fillToConstraints
                    }
            )

            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp, 20.dp)
                    .constrainAs(iconView) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                tint = MaterialTheme.colorScheme.onSurface
            )

        }
    }

}

@Composable
fun FormText(texto: String) {

    Text(texto)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterFormText(valor: String) {

    var email by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var contrasenia by remember { mutableStateOf("") }

    when (valor) {

        "email" -> {
            OutlinedTextField(
                value = email ,
                onValueChange = { email = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )}
        "nombre" -> {
            OutlinedTextField(
            value = nombre ,
            onValueChange = { nombre = it },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )}
        "apellidos" -> {
            OutlinedTextField(
                value = apellidos ,
                onValueChange = { apellidos = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )}
        "contraseña" -> {
            OutlinedTextField(
                value = contrasenia ,
                onValueChange = { contrasenia = it }
            )}
    }

}

@Preview(showBackground = true)
@Composable
fun BodyContentPreview () {

    BodyContent()

}