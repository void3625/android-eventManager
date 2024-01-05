package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Event : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        // 接收從上一個活動傳遞的資料
        val photo = intent.getIntExtra("photo", R.drawable.image0)
        val con = intent.getStringExtra("con") ?: "預設標題"
        val detail = intent.getStringExtra("detail") ?: "預設描述"

        // 將資料設定到相應的視圖中
        val activityImageView = findViewById<ImageView>(R.id.activityImageView)
        activityImageView.setImageResource(photo)

        val activityTitle = findViewById<TextView>(R.id.activityTitle)
        activityTitle.text = con

        val activityDescriptionTextView = findViewById<TextView>(R.id.activityDescriptionTextView)
        activityDescriptionTextView.text = detail

        val signupButton = findViewById<Button>(R.id.signupButton)
        signupButton.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("確定要報名嗎?")
            .setPositiveButton("是") { dialog, which ->
                // 這裡處理報名成功的邏輯
                // 例如顯示報名成功訊息或者保存報名信息
                showToast("報名成功")
            }
            .setNegativeButton("否") { dialog, which ->
                // 這裡可以處理任何返回活動頁面的邏輯
                dialog.dismiss()
            }
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
