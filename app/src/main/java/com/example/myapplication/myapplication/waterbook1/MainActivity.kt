package com.example.myapplication.myapplication.waterbook1

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.myapplication.waterbook1.commons.Constant.PASSWORD_PATTERN
import com.example.myapplication.myapplication.waterbook1.databinding.ActivityMainBinding
import com.example.myapplication.myapplication.waterbook1.presentation.PincodeFragment
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: com.example.myapplication.myapplication.waterbook1.databinding.ActivityMainBinding
    lateinit var Email : TextInputLayout
    lateinit var Password : TextInputLayout
    lateinit var emailInput : String
    lateinit var  passwordString : String
    var fragment : PincodeFragment = PincodeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = com.example.myapplication.myapplication.waterbook1.databinding.ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Email = binding.email
        Password = binding.password
        binding.button.setOnClickListener {
            if (validateEmail() && validatePassword()){
                supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
                binding.button.visibility = View.GONE
                binding.email.visibility = View.GONE
                binding.password.visibility = View.GONE
            } else{
                Toast.makeText(applicationContext,"Incorrect credentials",Toast.LENGTH_LONG)
            }
        }

    }

    private fun validatePassword(): Boolean {

        passwordString = Password.editText?.text.toString()

        if (passwordString.isEmpty()) {
            Password.error = "Field Cannot be Empty"
            return false
        }
        else if (!PASSWORD_PATTERN.matcher(passwordString).matches()) {
            Password.error = "Password is too weak"
            return false
        } else {
            Password.error = null
            return true
        }

    }

    private fun validateEmail(): Boolean {
        emailInput = Email.editText?.text.toString()

        if (emailInput.isEmpty()){
            Email.error = "Field Cannot be Empty"
            return false
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Email.error = "Enter a valid Email Address"
            return false
        } else {
            Email.error = null
            return true
        }
    }
}