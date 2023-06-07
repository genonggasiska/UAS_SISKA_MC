package com.example.wisata_lembah_baliem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.wisata_lembah_baliem.Fragment.ProfileFragment
import com.example.wisata_lembah_baliem.databinding.ActivityChangeEmailBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class ChangeEmailActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangeEmailBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChangeEmailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        binding.cvChangeEmailInputPass.visibility = View.VISIBLE
        binding.changeEmailCv.visibility = View.GONE

        binding.btnNext.setOnClickListener {
            var pass = binding.edtChangeEmailPass.text.toString()

            if (pass.isEmpty()){
                binding.edtChangeEmailPass.error = "Password Harus terisi"
                binding.edtChangeEmailPass.requestFocus()
                return@setOnClickListener
            }

            // cek password
            user.let {
                val userCredential = EmailAuthProvider.getCredential(it?.email!!,pass)
                it.reauthenticate(userCredential).addOnCompleteListener { task ->
                    when {
                        task.isSuccessful -> {
                            binding.cvChangeEmailInputPass.visibility = View.GONE
                            binding.changeEmailCv.visibility = View.VISIBLE
                        }
                        task.exception is FirebaseAuthInvalidCredentialsException -> {
                            binding.edtChangeEmailPass.error= "Password Salah"
                            binding.cvChangeEmailInputPass.requestFocus()
                        }
                        else -> {
                            Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.btnChangeEmail.setOnClickListener newEmail@{
            var newEmail = binding.edtChangeEmail.text.toString()

            if (newEmail.isEmpty()){
                binding.edtChangeEmail.error = "Email Harus Terisi"
                binding.edtChangeEmail.requestFocus()
                return@newEmail
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()){
                binding.edtChangeEmail.error = "Email Tidak Valid"
                binding.edtChangeEmail.requestFocus()
                return@newEmail
            }

            user?.let {
                user.updateEmail(newEmail).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Email BerhasilDirubah", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, ProfileFragment::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}