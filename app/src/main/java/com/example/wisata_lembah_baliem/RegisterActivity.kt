package com.example.wisata_lembah_baliem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.wisata_lembah_baliem.databinding.ActivityLoginBinding
import com.example.wisata_lembah_baliem.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // ke halaman login
        binding.tvRegLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Toombol Registrasi diklik
        binding.btnReg.setOnClickListener {
            val email = binding.edtEmailReg.text.toString()
            val password = binding.edtPassReg.text.toString()

//            validasi email
            if (email.isEmpty()) {
                binding.edtEmailReg.error = "Email harus diisi"
                binding.edtEmailReg.requestFocus()
                return@setOnClickListener
            }
//            validasi email tdk sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmailReg.error = "Email tidak valid"
                binding.edtEmailReg.requestFocus()
                return@setOnClickListener
            }
//            validasi password
            if (password.isEmpty()) {
                binding.edtPassReg.error = "Password harus diisi"
                binding.edtPassReg.requestFocus()
                return@setOnClickListener
            }
//            validasi panjang password
            if (password.length < 6) {
                binding.edtPassReg.error = "Password Minimal 6 karakter"
                binding.edtPassReg.requestFocus()
                return@setOnClickListener
            }
            
            RegisterFirebase(email, password)

        }
    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ActivityLoginBinding::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}