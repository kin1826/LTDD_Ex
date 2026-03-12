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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LESSON4Theme {
//                BirthdayCard()
//                Roll()
                Roll3Dice()
            }
        }
    }
}

//Code Roll
@Composable
fun Roll() {

    var diceValue by remember { mutableStateOf(1) }

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
        }) {
            Text("Roll")
        }
    }
}

@Composable
fun Roll3Dice() {

    var dice1 by remember { mutableStateOf(1) }
    var dice2 by remember { mutableStateOf(1) }
    var dice3 by remember { mutableStateOf(1) }

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

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val total = dice1 + dice2 + dice3

        Text(
            text = "Tổng điểm: $total",
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Image(
                painter = painterResource(diceImage(dice1)),
                contentDescription = "Dice1",
                modifier = Modifier.size(100.dp)
            )

            Image(
                painter = painterResource(diceImage(dice2)),
                contentDescription = "Dice2",
                modifier = Modifier.size(100.dp)
            )

            Image(
                painter = painterResource(diceImage(dice3)),
                contentDescription = "Dice3",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                dice1 = (1..6).random()
                dice2 = (1..6).random()
                dice3 = (1..6).random()
            }
        ) {
            Text("Roll")
        }
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