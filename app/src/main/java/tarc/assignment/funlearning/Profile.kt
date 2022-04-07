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

    private lateinit var firebaseAuth: FirebaseAuth

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent( context, LoginActivity::class.java))
        }

//         binding.editProfile.setOnClickListener {
//
//         }

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}