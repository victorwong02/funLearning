package tarc.assignment.funlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    checkUser()

    }
    private fun checkUser() {
        // if user is logged in, go to main screen
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null)
        {
            //logged in
            startActivity(Intent(this,LoginActivity::class.java))
            TODO("Change this main activity to following activity")
            finish()
        }
    }
}