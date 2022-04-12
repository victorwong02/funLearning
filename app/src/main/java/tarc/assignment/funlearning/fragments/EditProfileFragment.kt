package tarc.assignment.funlearning.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tarc.assignment.funlearning.*
import tarc.assignment.funlearning.databinding.FragmentEditProfileBinding


class EditProfileFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val db = Firebase.firestore

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!


    override fun onStart() {
        super.onStart()
        loadAvatar()
    }

    override fun onResume() {
        super.onResume()
        loadAvatar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.profilePic.setOnClickListener{
            val fragmentManager =  parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragmenteditavatar = EditAvatarFragment()
            fragmentTransaction.replace(R.id.nav_fragment, fragmenteditavatar)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

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

        val user = db.collection("user").document(uid)

        if (newName == "") {
            Toast.makeText(context, "Username not Updated", Toast.LENGTH_SHORT).show()
        }
        else{
            user.update("username",newName)
            Toast.makeText(context, "Username Updated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadAvatar() {
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser!!.uid

        db.collection("user").document(uid).get()
            .addOnSuccessListener { document ->
                val picname = document.getString("profile_pic").toString()

                when(picname){
                    "avatar1" -> binding.profilePic.setImageDrawable(resources.getDrawable(R.drawable.avatar1))
                    "avatar2" -> binding.profilePic.setImageDrawable(resources.getDrawable(R.drawable.avatar2))
                    "avatar3" -> binding.profilePic.setImageDrawable(resources.getDrawable(R.drawable.avatar3))
                    else -> binding.profilePic.setImageDrawable(resources.getDrawable(R.drawable.profile_pic))
                }

            }
    }

}
