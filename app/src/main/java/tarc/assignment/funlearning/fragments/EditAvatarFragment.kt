package tarc.assignment.funlearning.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tarc.assignment.funlearning.R
import tarc.assignment.funlearning.databinding.FragmentEditAvatarBinding

class EditAvatarFragment : Fragment() {


    private lateinit var firebaseAuth: FirebaseAuth
    private val db = Firebase.firestore

    private var _binding: FragmentEditAvatarBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditAvatarBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()


        binding.selectBtn.setOnClickListener{

            val avatar = if(binding.avatar1.isChecked){
                "avatar1"
            } else if (binding.avatar2.isChecked){
                "avatar2"
            } else if(binding.avatar3.isChecked){
                "avatar3"
            } else{
                "profile_pic"
            }

            changeAvatar(avatar)

            val fragmentManager =  parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragmenteditprofile = EditProfileFragment()
            fragmentTransaction.replace(R.id.nav_fragment, fragmenteditprofile)
            fragmentTransaction.setReorderingAllowed(true)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }

        val view = binding.root
        return view
    }

    private fun changeAvatar(avatar: String) {
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser!!.uid

        val user = db.collection("user").document(uid)
        user.update("profile_pic",avatar)
        Toast.makeText(context,"Avatar Updated",Toast.LENGTH_SHORT).show()

    }

}