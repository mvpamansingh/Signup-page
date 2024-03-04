package com.example.authentication.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authentication.R


@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(20.dp))
        Row (modifier= Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),horizontalArrangement = Arrangement.Center){

            Text(text = "About Us", fontSize = 32.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold)

        }
        Image(
            painter = painterResource(id = R.drawable.duckk), // Replace with your logo
            contentDescription = "App Logo",
            modifier = Modifier
                .padding(top = 16.dp, bottom = 24.dp)
                .size(100.dp) // Adjust size as needed
        )

        // Card with 2x2 Partition
        Card(modifier = Modifier.fillMaxWidth(), elevation = 7.dp) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    AboutItem("Version", "1.0.0")
                    AboutItem("Build", "235")
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    AboutItem("Release Date", "2023-12-14")
                    AboutItem("Support", "")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Scrollable Description Card
        Card(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
            elevation = 7.dp) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()) // Enable scrolling
            ) {
                Text(
                    text = " Twenty-five private sector specialists will soon be joining key posts in the Centre as part of the Modi government's ambitious plan to infuse such talents to further improve the ease of governance, officials said on Friday.\n" +
                            "\n" +
                            "They said the Appointments Committee of the Cabinet (ACC) headed by Prime Minister Narendra Modi has approved the appointment of three joint secretaries and 22 directors/deputy secretaries in different central government departments..Under the lateral entry scheme, which was launched in 2018, recruitments are made at the level of joint secretary, director and deputy secretary. The officers at these levels play an important role in policy-making.\n" +
                            "\n" +
                            "The officers who come through the lateral entry process become part and parcel of the government system.\n" +
                            "\n" +
                            "The Personnel Ministry had in June 2018 invited applications against 10 joint secretary-rank posts through the lateral entry mode for the first time. The recruitment for these posts was done by the Union Public Service Commission (UPSC).",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}



@Composable
fun AboutItem(label: String, value: String) {
    val overlineStyle = TextStyle(
        fontSize = 12.sp, // Adjust font size as needed
        fontWeight = FontWeight.Light,
        textDecoration = TextDecoration.Underline
    )
    Column {
        Text(text = label, style = overlineStyle)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}

