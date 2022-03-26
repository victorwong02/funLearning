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

class LoginActivity : AppCompatActivity() {

    //view binding
    private  lateinit var binding:ActivityLoginBinding

    //Action Bar
    private lateinit var  actionBar: ActionBar

    //ProgressDialogue
    private lateinit var progressDialog:ProgressDialog

    //Firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure action bar
        actionBar = supportActionBar!!
        actionBar.title ="Login"

        //configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In..")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //click, open register
        binding.noAccount.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        //click, start login
        binding.loginBtn.setOnClickListener {
            //validation check
            validateData()
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
        //show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //success
                progressDialog.dismiss()
                //get user info
                val firebaseUser =  firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"LoggedIn as $email", Toast.LENGTH_SHORT).show()

                //open home screen
                startActivity(Intent(this,MainActivity::class.java))
                TODO("Change this main activity to following activity")
                finish()
            }
            .addOnFailureListener { e->
                //failed
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun checkUser() {
        // if user is logged in, go to main screen
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null)
        {
            //logged in
            startActivity(Intent(this,MainActivity::class.java))
            TODO("Change this main activity to following activity")
            finish()
        }
    }
}