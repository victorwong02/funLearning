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
import tarc.assignment.funlearning.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityRegisterBinding

    //Firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    private var username = ""

    //init database
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()


        //click, start register
        binding.registerBtn.setOnClickListener {
            validateData()
        }

        //click, open login
        binding.gotAccount.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validateData() {
        //get data
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        username = binding.usernameEt.text.toString().trim()

        //validatation
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //invalid email format
            binding.emailEt.error = "Invalid email format"
        } else if (TextUtils.isEmpty(password)) {
            //no password enter
            binding.passwordEt.error = "Please enter password"
        } else if (password.length < 6) {
            //too short
            binding.passwordEt.error = "Password must at least 6 characters"
        } else {
            //valid data, create account
            firebaseSignUp()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun firebaseSignUp() {

        //create account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //success
                val firebaseUser = firebaseAuth.currentUser
                val uid = firebaseUser!!.uid

                val field = hashMapOf(
                    "username" to username,
                    "UID" to uid,
                    "html_notes" to null,
                    "html_exercises" to null,
                    "c_notes" to null,
                    "c_exercises" to null,
                )

                db.collection("user").document(uid).set(field)

                Toast.makeText(this, "Welcome $username!", Toast.LENGTH_SHORT).show()
            }
    }
}
