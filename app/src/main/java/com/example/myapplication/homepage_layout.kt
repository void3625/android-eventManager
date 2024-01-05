package com.example.myapplication

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

class homepage_layout : AppCompatActivity() {
    private lateinit var spinner1: Spinner
    private lateinit var listView: ListView
    private lateinit var filteredItem: ArrayList<Item>
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage_layout)

        spinner1 = findViewById(R.id.spinner1)
        listView = findViewById(R.id.listview)

        filteredItem = ArrayList()

        drawerLayout = findViewById(R.id.myDrawerLayout)

        val claArray = resources.getStringArray(R.array.cla_array)
        val array = resources.obtainTypedArray(R.array.image_list)
        val textArray = resources.getStringArray(R.array.text_array) // 添加 text_array

        val bt3 = findViewById<Button>(R.id.button3)

        listView.adapter = MyAdapter(this, R.layout.act, filteredItem)

        spinner1.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, claArray)

        spinner1.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                val selectedCategory = parentView.getItemAtPosition(position).toString()
                filterItems(selectedCategory, array, textArray)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // 不執行任何動作
            }
        })

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = listView.getItemAtPosition(position) as Item
            val intent = Intent(this@homepage_layout, Event::class.java)
            intent.putExtra("photo", selectedItem.photo)
            intent.putExtra("con", selectedItem.con)
            intent.putExtra("detail", textArray[position]) // 添加详细描述
            startActivity(intent)
        }
        bt3.setOnClickListener{
            val intent = Intent( this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    // 修改 getCategoryByIndex 方法
    private fun getCategoryByIndex(index: Int, conArray: Array<String>): String {
        return if (index < conArray.size) {
            conArray[index]
        } else {
            "未知分類"
        }
    }

    // 修改 filterItems 方法
    private fun filterItems(selectedCategory: String, array: TypedArray, textArray: Array<String>) {
        filteredItem.clear()

        val conArray = resources.getStringArray(R.array.con_array)

        for (i in 0 until array.length()) {
            val category = getCategoryByIndex(i, conArray)

            if (selectedCategory == "全部" ||
                (selectedCategory == "社團活動" && i < 2) ||
                (selectedCategory == "系上活動" && i in 2 until 4) ||
                (selectedCategory == "通識課程" && i in 4 until 6) ||
                (selectedCategory == "校內活動" && i in 6 until 8) ||
                (selectedCategory == "說明會" && i in 8 until 10)) {

                val photo = array.getResourceId(i, 0)
                val name = if (i < conArray.size) conArray[i] else "沒有此活動"

                filteredItem.add(Item(photo, name))
            }
        }

        (listView.adapter as MyAdapter).notifyDataSetChanged()
    }

    data class Item(
        val photo: Int,
        val con: String,
    )
}
