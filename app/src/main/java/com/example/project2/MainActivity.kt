package com.example.project2

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var adapter: WishlistAdapter

    private val wishlistItems = mutableListOf<WishlistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)

        // Initialize the RecyclerView
        adapter = WishlistAdapter(wishlistItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            showAddItemDialog()
        }
    }

    private fun showAddItemDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.editName)
        val priceEditText = dialogView.findViewById<EditText>(R.id.editPrice)
        val urlEditText = dialogView.findViewById<EditText>(R.id.editURL)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Item")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEditText.text.toString()
                val price = priceEditText.text.toString()
                val url = urlEditText.text.toString()

                if (name.isNotEmpty() && price.isNotEmpty() && url.isNotEmpty()) {
                    val newItem = WishlistItem(name, price, url)
                    wishlistItems.add(newItem)
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }
}
