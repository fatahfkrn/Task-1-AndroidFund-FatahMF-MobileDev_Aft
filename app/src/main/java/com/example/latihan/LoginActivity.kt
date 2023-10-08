package com.example.latihan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etUsename:EditText
    private lateinit var etPassword:EditText
    private lateinit var history:TextView

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data:Intent? = result.data
            val returnString:String? = data?.getStringExtra("history")
            history.text = returnString
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsename = findViewById(R.id.edt_username)
        etPassword = findViewById(R.id.edt_password)
        history = findViewById(R.id.tv_history)

        val bundle = intent.extras
        if (bundle != null){
            etUsename.setText(bundle.getString("Username"))
            etPassword.setText(bundle.getString("Password"))
        }

        val btnLogin: Button =  findViewById(R.id.btn_login)
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_login -> {
                val inten = Intent(this@LoginActivity, MainActivity::class.java)
                inten.putExtra("User", User(etUsename.text.toString(), etPassword.text.toString()))
                resultLauncher.launch(inten)
            }
        }
    }
}