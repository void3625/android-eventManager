package com.example.myapplication


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class SecActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)


        val ed_name = findViewById<EditText>(R.id.ed_name)
        val ed_phone = findViewById<EditText>(R.id.ed_phone)
        val btn_send = findViewById<Button>(R.id.btn_send)


        btn_send.setOnClickListener {
            when {
                ed_name.length() <1 ->
                    Toast.makeText(this,"請輸入姓名", Toast.LENGTH_SHORT).show()
                ed_phone.length() <1 ->
                    Toast.makeText(this,"請輸入電話", Toast.LENGTH_SHORT).show()
                else -> {
                    val b = Bundle()
                    b.putString("name", ed_name.text.toString())
                    b.putString("phone", ed_phone.text.toString())
                    setResult(Activity.RESULT_OK, Intent().putExtras(b))
                    finish()
                }
            }
        }
    }
}
