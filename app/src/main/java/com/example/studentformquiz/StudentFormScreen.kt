package com.example.studentformquiz

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentformquiz.ui.theme.BlueSlate
import com.example.studentformquiz.ui.theme.HunterGreen
import com.example.studentformquiz.ui.theme.YellowGreen
import java.util.Calendar

@Composable
fun StudentFormScreen() {
    val context = LocalContext.current

    var nameState by remember { mutableStateOf("") }
    var surnameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var isAgreed by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDay = String.format("%02d", selectedDay)
            val formattedMonth = String.format("%02d", selectedMonth + 1)
            dateState = "$formattedDay/$formattedMonth/$selectedYear"
        },
        year,
        month,
        day
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE2EBE5)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BlueSlate)
                        .padding(vertical = 24.dp, horizontal = 20.dp)
                ) {
                    Column {
                        Text(
                            text = "Student Form",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Fill in your details",
                            color = Color(0xFFE2F3D9),
                            fontSize = 14.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    FormFieldLabel("სახელი")
                    FormTextField(
                        value = nameState,
                        onValueChange = { nameState = it },
                        placeholder = "შეიყვანეთ სახელი"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    FormFieldLabel("გვარი")
                    FormTextField(
                        value = surnameState,
                        onValueChange = { surnameState = it },
                        placeholder = "შეიყვანეთ გვარი"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    FormFieldLabel("დაბადების თარიღი")
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                datePickerDialog.show()
                            }
                    ) {
                        FormTextField(
                            value = dateState,
                            onValueChange = {},
                            placeholder = "DD/MM/YYYY",
                            readOnly = true,
                            enabled = false,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Calendar Icon",
                                    tint = BlueSlate
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    FormFieldLabel("იმეილი")
                    FormTextField(
                        value = emailState,
                        onValueChange = { emailState = it },
                        placeholder = "your.email@example.com"
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    FormFieldLabel("თქვენი ფავორიტი მიმართულება")
                    val directions = listOf("Android", "iOS", "Web")
                    directions.forEach { direction ->
                        val isSelected = selectedOption == direction
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .clickable { selectedOption = direction }
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { selectedOption = direction },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = BlueSlate,
                                    unselectedColor = Color(0xFF94A3B8)
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = direction,
                                color = HunterGreen,
                                fontSize = 15.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isAgreed = !isAgreed }
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = "ვეთანხმები წესებს და პირობებს",
                            color = HunterGreen,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        Switch(
                            checked = isAgreed,
                            onCheckedChange = { isAgreed = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = YellowGreen,
                                uncheckedThumbColor = Color(0xFF64748B),
                                uncheckedTrackColor = Color(0xFFE2E8F0)
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val isFormFilled = nameState.isNotBlank() &&
                        surnameState.isNotBlank() &&
                        emailState.isNotBlank() &&
                        dateState.isNotBlank()

                val isDirectionChosen = selectedOption.isNotEmpty()

                if (isFormFilled && isDirectionChosen && isAgreed) {
                    Toast.makeText(context, "მონაცემები გაიგზავნა!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "შეავსეთ ყველა ველი!", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = BlueSlate),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = "გაგზავნა (Submit)",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
