/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            MyTheme {
                ShowDogList(context = context)
            }
        }
    }
}

@Composable
fun DogList(dogs: List<Dog>, onDogClick: (Dog) -> Unit = {}) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dogs) { dog ->
            DogItem(
                dog,
                Modifier
                    .clickable { onDogClick(dog) }
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    Card(modifier) {
        Row {
            Image(
                painterResource(dog.photo),
                "Picture of dog: ${dog.name}",
                Modifier.size(120.dp),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.padding(8.dp)) {
                Text(dog.name, style = MaterialTheme.typography.h4)
                Text("Breed: ${dog.breed}", style = MaterialTheme.typography.body2)
                Text("Address: ${dog.address}", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
fun ShowDogList(context: Context) {

    val dogList = listOf(
        Dog(
            "Cookie", "Golden Retriever", "2",
            "Boy", "Washington", R.drawable.dog_4
        ),
        Dog(
            "Coco", "Husky", "1",
            "Girl", "Los Angeles", R.drawable.dog_7
        ),
        Dog(
            "Happy", "Corgi", "3",
            "Girl", "Alaska", R.drawable.dog_6
        ),
        Dog(
            "Barbara", "Labrador", "1",
            "Girl", "Philadelphia", R.drawable.dog_5
        ),
        Dog(
            "Honey", "Akita", "4",
            "Boy", "Washington", R.drawable.dog_3
        ),
        Dog(
            "Anna", "Teddy", "5",
            "Girl", "New York", R.drawable.dog_2
        ),
        Dog(
            "Katrina", "Samoyed", "2",
            "Boy", "Chicago", R.drawable.dog_1
        ),
        Dog(
            "Emily", "Chihuahua", "1",
            "Girl", "Los Angeles", R.drawable.dog_0
        )
    )

    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Column {
            TopAppBar(
                title = {
                    Text("Puppy Adoption")
                }
            )
            DogList(dogList, onDogClick = {
                val intent = Intent(context, DogDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putString("name", it.name)
                bundle.putString("breed", it.breed)
                bundle.putString("age", it.age)
                bundle.putString("gender", it.gender)
                bundle.putString("address", it.address)
                bundle.putInt("photo", it.photo)
//                bundle.putParcelable("dog", it)
                intent.putExtras(bundle)
                startActivity(context, intent, null)
            })
        }
        
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun ShowDogListLight() {
    val context = LocalContext.current
    MyTheme {
        ShowDogList(context)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun ShowDogListDark() {
    val context = LocalContext.current
    MyTheme(darkTheme = true) {
        ShowDogList(context)
    }
}
