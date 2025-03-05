package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        else {
            setContentView(R.layout.activity_main)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            auth = FirebaseAuth.getInstance()
            val btnLogout = findViewById<Button>(R.id.btnLogout)

            btnLogout.setOnClickListener {
                auth.signOut()  // Đăng xuất khỏi Firebase
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Đóng MainActivity để tránh quay lại khi nhấn Back
            }
        }
    }

}