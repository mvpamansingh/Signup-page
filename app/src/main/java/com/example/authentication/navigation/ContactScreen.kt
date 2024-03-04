package com.example.authentication.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.authentication.R


@Composable
fun ContactScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Profile Picture in a Card
        Card(
            modifier = Modifier.padding(top = 16.dp),
            shape = CircleShape, // Make the card circular
            elevation = 8.dp
        ) {
            Image(
                painter = painterResource(id = R.drawable.duckk), // Replace with your image
                contentDescription = "My Profile Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
        }

        // Short Description
        Card(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium, // Material 3 rounded corners
            elevation = 4.dp
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text = "Aman Singh", fontSize = 28.sp, fontFamily = FontFamily.Monospace)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Android Developer | Passionate about UI | Coffee Enthusiast",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                    , fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Monospace
                )
                Text(text = "")
                
            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        // Row of Social Media Icons
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Replace placeholders with your actual social links
            mailto( R.drawable.linkdin, "fakeeee67@gmail.com")
            SocialMediaIcon(R.drawable.x, "https://x.com/AndroidJr_?t=KpAcrbJy4RnoGVX6S6ku-w&s=09")
            SocialMediaIcon(R.drawable.link, "https://mvpamansingh.github.io/my-Portfolio/")
           SocialMediaIcon(R.drawable.linkdin, "https://www.linkedin.com/in/mvpamansingh/")
        }
    }
}


@Composable
fun SocialMediaIcon(iconResourceId: Int, link: String) {
    val context = LocalContext.current

    IconButton(
        onClick = {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            ContextCompat.startActivity(context, browserIntent, null)
        },
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
    ) {
        Image(painter = painterResource(id = iconResourceId), contentScale = ContentScale.Crop, contentDescription = "")
    }
}

@Composable
fun mailto(iconResourceId: Int, link: String) {
    val context = LocalContext.current

    IconButton(
        onClick = {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$link") // "mailto:" scheme is key
            }
            ContextCompat.startActivity(context, emailIntent, null)
        },
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
    ) {
        Image(painter = painterResource(id = iconResourceId), contentScale = ContentScale.Crop, contentDescription = "")

    }
}

