package tarc.assignment.funlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import tarc.assignment.funlearning.databinding.ActivityForgetPasswordBinding
import tarc.assignment.funlearning.databinding.ActivityLoginBinding

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetPasswordBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.submitBtn.setOnClickListener {
            validateEmail()
        }
    }

    private fun validateEmail() {
        //get data
        email = binding.emailEt.text.toString().trim()

        //validatation
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //invalid email format
            binding.emailEt.error = "Invalid email format"
        } else {
            //valid data, create account
            firebaseAuth.sendPasswordResetEmail(email)
            Toast.makeText(this,"Please Check Your Email",Toast.LENGTH_LONG).show()
        }
    }
}