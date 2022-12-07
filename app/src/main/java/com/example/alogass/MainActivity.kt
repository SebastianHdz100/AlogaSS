package com.example.alogass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       FirebaseApp.initializeApp(this)
        firebaseAuth=Firebase.auth
        val btnIngresar: Button=findViewById(R.id.btnLogin)
        val txtemail: TextView=findViewById(R.id.edtEmail)
        val txtPass: TextView=findViewById(R.id.edtPass)

        btnIngresar.setOnClickListener(){
            signIn(txtemail.text.toString(),txtPass.text.toString())
        }
    }


    private fun signIn(email:String,password: String){
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                 val user=firebaseAuth.currentUser
                    Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    //aqui vamos a ir a la segunda activity
                }
                else{
                    Toast.makeText(baseContext,"Error de email y/o contraseña",Toast.LENGTH_SHORT).show()
                }
            }
    }
}