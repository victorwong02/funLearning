package tarc.assignment.funlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tarc.assignment.funlearning.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    //view binding
    private  lateinit var binding:ActivityLoginBinding

    //init database
    private val db = Firebase.firestore

    //Firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        // if user is logged in, direct go home
        checkUser()

        //click, open register
        binding.noAccount.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        //click, start login
        binding.loginBtn.setOnClickListener {
            //validation check
            validateData()
        }

        //click, forget password
        binding.forgetPassword.setOnClickListener {
            //
            startActivity(Intent(this, ForgetPasswordActivity::class.java))

        }
    }

    private fun validateData() {
        //get data
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEt.error= "Invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            //no password enter
            binding.passwordEt.error ="Please enter password"
        }
        else{
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                val uid = firebaseUser!!.uid

                db.collection("user").document(uid).get()
                    .addOnSuccessListener { document ->
                        val name = document.getString("username").toString()
                        //get user info


                        //open home screen
                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, "Welcome, $name", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                    }

            }
    }


    private fun checkUser() {
        // if user is logged in, go to main screen
        //get current user
        if (firebaseAuth.currentUser != null) {
            //logged in
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}