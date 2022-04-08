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

class EditProfileFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.saveInfo.setOnClickListener{
            editProfile()

            val fragmentManager =  parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragmentprofile = ProfileFragment()
            fragmentTransaction.replace(R.id.nav_fragment, fragmentprofile)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }

        binding.changePswd.setOnClickListener{
            startActivity(Intent(context, ForgetPasswordActivity::class.java))
        }

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun editProfile() {

        val newName = binding.editName.text.toString()
        val newEmail = binding.editEmail.text.toString()

        if (newName.trim() == null) {
            if(newEmail.trim() == null) {
                Toast.makeText(context, "Nothing Has Been Updated", Toast.LENGTH_SHORT).show()
            }
            else {

                Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            if(newEmail.trim() == null) {

                Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
            }
            else {

                Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
