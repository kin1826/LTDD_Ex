package com.example.lesson4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lesson4.ui.theme.LESSON4Theme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LESSON4Theme {
//                BirthdayCard()
//                Roll()
//                Roll3Dice()
                DiceApp()
            }
        }
    }
}

@Composable
fun DiceApp() {
    var screen by remember { mutableStateOf("menu") }

    when (screen) {
        "menu" -> MenuScreen(
            onOneDice = { screen = "one" },
            onThreeDice = { screen = "three" }
        )

        "one" -> Roll(
            onBack = { screen = "menu" }
        )

        "three" -> Roll3Dice(
            onBack = { screen = "menu" }
        )
    }
}

@Composable
fun MenuScreen(
    onOneDice: () -> Unit,
    onThreeDice: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Dice Game",
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = onOneDice) {
            Text("Chơi 1 xúc xắc")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onThreeDice) {
            Text("Chơi 3 xúc xắc")
        }
    }
}

//Code Roll
@Composable
fun Roll(onBack: () -> Unit) {

    var diceValue by remember { mutableStateOf(1) }

    val history = remember { mutableStateListOf<Int>() }

    var showHistory by remember { mutableStateOf(false) }

    val image = when (diceValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Điểm: $diceValue",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(image),
            contentDescription = "Dice",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            diceValue = (1..6).random()

            history.add(0, diceValue)
        }) {
            Text("Roll")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            showHistory = true
        }) {
            Text("Lịch sử")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onBack) {
            Text("Trở về")
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Popup History
        if (showHistory) {
            AlertDialog(
                onDismissRequest = {
                    showHistory = false
                },

                title = {
                    Text("Roll History")
                },

                text = {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 300.dp)
                            .fillMaxWidth()
                    ) {

                        items(history) { roll ->
                            Text("🎲 $roll")
                        }


//                        items(history.take(10)) { roll ->
//                            Text("🎲 $roll")
//                        }

                    }
                },

                confirmButton = {
                    Button(onClick = {
                        showHistory = false
                    }) {
                        Text("Close")
                    }
                }
            )
        }
    }
}

@Composable
fun Roll3Dice(onBack: () -> Unit) {

    var dice1 by remember { mutableStateOf(1) }
    var dice2 by remember { mutableStateOf(1) }
    var dice3 by remember { mutableStateOf(1) }

    val history3 = remember { mutableStateListOf<Triple<Int, Int, Int>>() }

    var showHistory by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    fun diceImage(value: Int): Int {
        return when (value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    fun getDiceImage(value: Int): Int {
        return when (value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val total = dice1 + dice2 + dice3

        Text(
            text = "${compare(total)} $total",
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Image(
                painter = painterResource(getDiceImage(dice1)),
                contentDescription = "Dice1",
                modifier = Modifier.size(100.dp)
            )

            Image(
                painter = painterResource(getDiceImage(dice2)),
                contentDescription = "Dice2",
                modifier = Modifier.size(100.dp)
            )

            Image(
                painter = painterResource(getDiceImage(dice3)),
                contentDescription = "Dice3",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                scope.launch {
                    repeat(12) {
                        dice1 = (1..6).random()
                        dice2 = (1..6).random()
                        dice3 = (1..6).random()

                        delay(60)
                    }

                    dice1 = (1..6).random()
                    dice2 = (1..6).random()
                    dice3 = (1..6).random()
                }



                history3.add(0, Triple(dice1, dice2, dice3))
            }
        ) {
            Text("Roll")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {showHistory = true}) {
            Text("Lịch sử")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onBack) {
            Text("Trở về")
        }

        if (showHistory) {
            AlertDialog(
                onDismissRequest = {
                    showHistory = false
                },

                title = {
                    Text("Roll History")
                },

                text = {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 300.dp)
                            .fillMaxWidth()
                    ) {
                        items(history3) { roll ->
                            val sum = roll.first + roll.second + roll.third
                            Text("🎲 ${roll.first} ${roll.second} ${roll.third}" + " = ${sum} - ${compare(sum)}")
                        }
                    }
                },

                confirmButton = {
                    Button(onClick = {
                        showHistory = false
                    }) {
                        Text("Close")
                    }
                }
            )
        }
    }
}

fun compare(total: Int): String {
    return if (total == 3 || total == 18) {
        "TAM HOA"
    } else if (total <= 10) {
        "Xỉu"
    } else {
        "Tài"
    }
}


//Code Birthday Card
@Composable
fun BirthdayCard() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Hình ảnh
        Image(
            painter = painterResource(R.drawable.birthday),
            contentDescription = "Birthday Image",
            modifier = Modifier
                .size(200.dp)
                .padding(10.dp)
        )

        Text(
            text = "Trần Văn Bằng",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Mời mọi người đến tham dự buổi sinh nhật của mình.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(250.dp)
        )
        Text(
            text = "---------------",
            fontSize = 14.sp
        )
        Text(
            text = "18h - 18/11/2026",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        ContactRow("📞", "0334082946")
        ContactRow("🌐", "@kinkin1811")
        ContactRow("✉️", "bangtv.24it@vku.udn.vn")

    }
}

@Composable
fun ContactRow(icon: String, text: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = icon,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = text,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LESSON4Theme {
        BirthdayCard()
    }
}