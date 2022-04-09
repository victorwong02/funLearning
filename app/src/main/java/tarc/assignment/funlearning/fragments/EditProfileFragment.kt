package tarc.assignment.funlearning.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tarc.assignment.funlearning.ForgetPasswordActivity
import tarc.assignment.funlearning.R
import tarc.assignment.funlearning.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val db = Firebase.firestore

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
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser!!.uid

        val newName = binding.editName.text.toString().trim()
        val newEmail = binding.editEmail.text.toString().trim()

        val user = db.collection("user").document(uid)

        if (newName == null && newEmail == null) {
            Toast.makeText(context, "Nothing Has Been Updated", Toast.LENGTH_SHORT).show()
        }
        else{
            if (newName == null){
                //update email
                user.update("email",newEmail)
            }
            else if(newEmail == null){
                //update name
                user.update("username",newName)
            }
            else{
                //update name and email
                user.update("username",newName)
                user.update("email",newEmail)
            }
            Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
        }
    }


}

