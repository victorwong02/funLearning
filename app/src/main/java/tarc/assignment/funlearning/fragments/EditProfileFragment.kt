package tarc.assignment.funlearning.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import tarc.assignment.funlearning.ForgetPasswordActivity
import tarc.assignment.funlearning.R
import tarc.assignment.funlearning.databinding.FragmentEditProfileBinding
import tarc.assignment.funlearning.databinding.FragmentProfileBinding

class EditProfileFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private var _binding1: FragmentProfileBinding? = null
    private val binding1 get() = _binding1!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = binding.root

        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        _binding1 = FragmentProfileBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.saveInfo.setOnClickListener{ editProfile() }

        binding.changePswd.setOnClickListener{
            startActivity(Intent(context, ForgetPasswordActivity::class.java))
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _binding1 = null
    }

    private fun editProfile() {
        val newName = binding.editName.text.toString()
        val newEmail = binding.editEmail.text.toString()

        if (newName.trim() == null) {
            if(newEmail.trim() == null) {
                Toast.makeText(context, "Nothing Has Been Updated", Toast.LENGTH_SHORT).show()
            }
            else {
                binding1.userEmail.text = getString(R.string.user_email,newEmail)
                Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            if(newEmail.trim() == null) {
                binding1.userName.text = getString(R.string.user_name,newName)
                Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
            }
            else {
                binding1.userEmail.text = getString(R.string.user_email,newEmail)
                binding1.userName.text = getString(R.string.user_name,newName)
                Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
