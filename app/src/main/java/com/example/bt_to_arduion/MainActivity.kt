package com.example.bt_to_arduion

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            launch(Dispatchers.IO) {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

                val service = retrofit.create(JsonPlaceholderService::class.java)

                try {
                    val todos = service.getTodos()
                    showData(todos,this@MainActivity)
                    for (todo in todos) {
                        Log.v("list", "Title: ${todo.title}, Completed: ${todo.url}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
    suspend fun showData(retrofit: List<Todo>,context:Context) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = CustomAdapter(retrofit,this@MainActivity)
    }

    fun onItemClick(position: Int) {
        // Handle the click event for the item at position
        Toast.makeText(this, "Item clicked at position $position", Toast.LENGTH_SHORT).show()
    }
}

data class Todo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

interface JsonPlaceholderService {
    @GET("/photos")
    suspend fun getTodos(): List<Todo>
}

