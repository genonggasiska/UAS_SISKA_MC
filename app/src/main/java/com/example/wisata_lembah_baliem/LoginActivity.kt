package com.example.wisata_lembah_baliem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.wisata_lembah_baliem.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // ke halaman register
        binding.tvLoginReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // forget pass
        binding.forgotPW.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        // saat login diklik
        binding.btnLogin.setOnClickListener {
            val email = binding.editEmailLogin.text.toString()
            val password = binding.editPasswordLogin.text.toString()

//            validasi email
            if (email.isEmpty()) {
                binding.editEmailLogin.error = "Email harus diisi"
                binding.editEmailLogin.requestFocus()
                return@setOnClickListener
            }
//            validasi email tdk sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.editEmailLogin.error = "Email tidak valid"
                binding.editEmailLogin.requestFocus()
                return@setOnClickListener
            }
//            validasi password
            if (password.isEmpty()) {
                binding.editPasswordLogin.error = "Password harus diisi"
                binding.editPasswordLogin.requestFocus()
                return@setOnClickListener
            }
//            validasi panjang password
            if (password.length < 6) {
                binding.editPasswordLogin.error = "Password Minimal 6 Karakter"
                binding.editPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email,password)

        }


    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Welcome, $email", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this,"${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}