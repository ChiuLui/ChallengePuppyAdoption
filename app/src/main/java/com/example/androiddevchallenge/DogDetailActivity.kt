package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

class DogDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val dog = Dog(
                    "Happy", "Corgi", "3",
                    "Girl", "Alaska", R.drawable.dog_6
                )
                ShowDetail(dog)
            }
        }
    }
}

@Composable
fun DogDetail(dog: Dog) {

    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(dog.photo),
                contentDescription = null, // decorative
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Name: ${dog.name}",
                style = typography.h4,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text("Breed: ${dog.breed}",
                style = typography.h6)

            Spacer(modifier = Modifier.height(5.dp))

            Text("Age: ${dog.age}",
                style = typography.h6)

            Spacer(modifier = Modifier.height(5.dp))
            Text("Gender: ${dog.gender}",
                style = typography.h6)

            Spacer(modifier = Modifier.height(5.dp))
            Text("Address: ${dog.address}",
                style = typography.h6)
        }
    }
}

@Composable
fun ShowDetail(dog: Dog) {
    Surface(color = MaterialTheme.colors.background) {
        DogDetail(dog)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun ShowDetailLight() {
    val dog = Dog(
        "Happy", "Corgi", "3",
        "Girl", "Alaska", R.drawable.dog_6
    )
    MyTheme {
        ShowDetail(dog)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun ShowDetailDark() {
    val dog = Dog(
        "Happy", "Corgi", "3",
        "Girl", "Alaska", R.drawable.dog_6
    )
    MyTheme(darkTheme = true) {
        ShowDetail(dog)
    }
}