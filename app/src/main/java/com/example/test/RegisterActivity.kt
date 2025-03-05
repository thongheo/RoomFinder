package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val edtRegisterEmail = findViewById<EditText>(R.id.edtRegisterEmail)
        val edtRegisterPassword = findViewById<EditText>(R.id.edtRegisterPassword)
        val edtRegisterPasswordAgain = findViewById<EditText>(R.id.edtRegisterPasswordAgain)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        btnRegister.setOnClickListener() {
            val email = edtRegisterEmail.text.toString()
            val password = edtRegisterPassword.text.toString()
            val password2 = edtRegisterPasswordAgain.text.toString()
            if(password == password2) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        else {
                            Toast.makeText(this, "Lỗi: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
            }

        }

        tvLogin.setOnClickListener() {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}