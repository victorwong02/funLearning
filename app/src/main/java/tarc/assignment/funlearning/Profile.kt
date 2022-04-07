package tarc.assignment.funlearning

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import tarc.assignment.funlearning.databinding.FragmentProfileBinding

class Profile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()


        binding.logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        //check whether is logged in
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            //get user info
            val email = firebaseUser.email
        }
        else {
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

}