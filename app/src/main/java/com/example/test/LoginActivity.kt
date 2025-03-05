package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        btnLogin.setOnClickListener() {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password) // Phương thức của Firebase Auth - Giúp xác thực người dùng bằng email và password
                .addOnCompleteListener() { task ->  //kiểm tra quá trình đăng nhập thành công hay thất bại - task là đối tượng chứa thông tin của kết quả đăng nhập
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Lỗi: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        tvRegister.setOnClickListener() {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}