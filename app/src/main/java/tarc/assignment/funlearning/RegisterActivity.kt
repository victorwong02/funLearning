package tarc.assignment.funlearning

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import tarc.assignment.funlearning.databinding.ActivityLoginBinding
import tarc.assignment.funlearning.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    //view binding
    private  lateinit var binding: ActivityRegisterBinding


    //ProgressDialogue
    private lateinit var progressDialog: ProgressDialog

    //Firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

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
    }

    private fun validateData() {
        //get data
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        //validatation
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.emailEt.error = "Invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            //no password enter
            binding.passwordEt.error ="Please enter password"
        }
        else if (password.length <6){
            //too short
            binding.passwordEt.error = "Password must at least 6 characters"
        }
        else{
            //valid data, create account
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        //progressDialog.show()

        //create account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //success
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Welcome !",Toast.LENGTH_SHORT).show()
             //   startActivity(Intent(this,MainActivity::class.java))
                TODO("Change this main activity to following activity")
                finish()
            }
            .addOnFailureListener {
                //fail
                progressDialog.dismiss()
                Toast.makeText(this, "FAILED to register an account",Toast.LENGTH_SHORT).show()
            }
    }

/*    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // go back previous activity
        return super.onSupportNavigateUp()
    }*/
}


/*
Apply following code to do logout :P

1. Inside onCreate function

 //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener {
           firebaseAuth.signOut()
           checkUser()
        }

2. Create another function

  private fun checkUser() {
       //check whether is logged in
       val firebaseUser = firebaseAuth.currentUser
       if (firebaseUser != null){
            //get user info
            val email = firebaseUser.email
       }
       else {
            startActivity(Intent( this, LoginActivity::class.java))
            finish()
       }
  }

 */