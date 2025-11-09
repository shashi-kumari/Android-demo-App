package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                UnitConverterApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun UnitConverterApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Greeting(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    FAVORITES("Favorites", Icons.Default.Favorite),
    PROFILE("Profile", Icons.Default.AccountBox),
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var inputValue by rememberSaveable { mutableStateOf("0") }
    var inputUnit by rememberSaveable { mutableStateOf("Centimeters") }
    var outputUnit by rememberSaveable { mutableStateOf("Meters") }
    var iExpanded by rememberSaveable { mutableStateOf(false) }
    var oExpanded by rememberSaveable { mutableStateOf(false) }
    var conversionFactor by rememberSaveable { mutableStateOf(0.01) }
    val context = LocalContext.current

    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Column(
        modifier = Modifier.fillMaxSize(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter", style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.padding(10.0.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                if (it.toDoubleOrNull() != null) {
                    inputValue = it
                }
                else{
                    Toast.makeText(context, "Invalid input", Toast.LENGTH_SHORT).show()
                }
            },
            label = { Text("Input") }
        )
        Spacer(modifier = Modifier.padding(10.0.dp))
        Row {

            /*Button({Toast.makeText(context, "Converting...", Toast.LENGTH_SHORT).show()}) {
                Text("Convert")
            }
            Button({ Toast.makeText(context, "Clearing input...", Toast.LENGTH_SHORT).show()}) {
                Text("Clear")
            }*/
            Box {
                Button(onClick = {
                    iExpanded = !iExpanded
                    Toast.makeText(context, "Converting...", Toast.LENGTH_SHORT).show()
                }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                        DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Centimeters"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Meters") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Meters"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Feet") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Feet"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Inches") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Inches"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Yards") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Yards"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Miles") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Miles"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Kilometers") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Kilometers"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Millimeters"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Micrometers") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Micrometers"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Nanometers") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Nanometers"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Picometers") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Picometers"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Point") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Point"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Points") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Points"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Picas") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Picas"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Pentimeters") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Pentimeters"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Angstroms") }, onClick = {
                            iExpanded = false;
                            inputUnit = "Angstroms"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                    }
                }
            }
            Spacer(modifier = Modifier.width(10.0.dp))

            Box {
                Button(onClick = {
                    oExpanded = !oExpanded
                    Toast.makeText(context, "Converting...", Toast.LENGTH_SHORT).show()
                }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false}) {
                        DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Centimeters"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Meters") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Meters"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Feet") }, onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Inches") }, onClick = {
                            oExpanded = false
                            outputUnit = "Inches"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Yards") }, onClick = {
                            oExpanded = false
                            outputUnit = "Yards"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Miles") }, onClick = {
                            oExpanded = false
                            outputUnit = "Miles"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Kilometers") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Kilometers"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Millimeters"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Micrometers") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Micrometers"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Nanometers") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Nanometers"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Picometers") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Picometers"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Point") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Point"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Points") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Points"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Picas") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Picas"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Pentimeters") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Pentimeters"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                        DropdownMenuItem(text = { Text("Angstroms") }, onClick = {
                            oExpanded = false;
                            outputUnit = "Angstroms"
                            conversionFactor = getConversionFactor(inputUnit, outputUnit)
                        })
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(10.0.dp))

        Text(
            "Result: " + (inputValue.toDoubleOrNull()?.let { it * conversionFactor  }?.toString() +" "+ outputUnit
                ?: "Invalid input"), style = MaterialTheme.typography.headlineMedium
        )
    }
}

private fun ColumnScope.getConversionFactor(inputUnit: String, outputUnit: String): Double {
    val toMeters = mapOf(
        "Meters" to 1.0,
        "Centimeters" to 0.01,
        "Millimeters" to 0.001,
        "Kilometers" to 1000.0,
        "Miles" to 1609.344,
        "Yards" to 0.9144,
        "Feet" to 0.3048,
        "Inches" to 0.0254,
        "Micrometers" to 1e-6,
        "Nanometers" to 1e-9,
        "Picometers" to 1e-12,
        "Angstroms" to 1e-10,
        "Point" to 0.0003527777777777778,
        "Points" to 0.0003527777777777778,
        "Picas" to 0.004233333333333333,
        "Pentimeters" to 0.1
    )
    val inFactor = toMeters[inputUnit] ?: return 1.0
    val outFactor = toMeters[outputUnit] ?: return 1.0
    return inFactor / outFactor
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        Greeting("Android")
    }
}