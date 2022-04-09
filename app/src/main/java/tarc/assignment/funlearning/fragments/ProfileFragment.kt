package tarc.assignment.funlearning.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tarc.assignment.funlearning.LoginActivity
import tarc.assignment.funlearning.MainActivity
import tarc.assignment.funlearning.R
import tarc.assignment.funlearning.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val db = Firebase.firestore

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onStart() {
        super.onStart()
        loadInfo()
    }

    override fun onResume() {
        super.onResume()
        loadInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        firebaseAuth = FirebaseAuth.getInstance()


        binding.logoutButton.setOnClickListener {
            logoutDialog(it)
        }

        binding.editProfile.setOnClickListener {
            val fragmentManager =  parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragmenteditprofile = EditProfileFragment()
            fragmentTransaction.replace(R.id.nav_fragment, fragmenteditprofile)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        //get history record
        binding.htmlNotes.text = getString(R.string.html_lastnotes)
        binding.htmlExercise.text = getString(R.string.html_lastexercise)
        binding.cNotes.text = getString(R.string.c_lastnotes)
        binding.cExercise.text = getString(R.string.c_lastexercise)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun logoutDialog(view: View){

        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Logout Confirmation")
        builder.setMessage("Confirm to Logout?")
        builder.setPositiveButton("Logout",DialogInterface.OnClickListener{dialog, which ->
            firebaseAuth.signOut()
            startActivity(Intent( context, LoginActivity::class.java))
            dialog.cancel()
        })
        builder.setNegativeButton("Cancel",DialogInterface.OnClickListener{dialog, which ->
            dialog.cancel()
        })

        val alert : AlertDialog = builder.create()
        alert.show()

        alert.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.parseColor("#8BC34A"))
        alert.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#8BC34A"))
    }

    fun loadInfo(){
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser!!.uid

        db.collection("user").document(uid).get()
            .addOnSuccessListener { document ->
                val name = document.getString("username").toString()
                val email = document.getString("email").toString()

                binding.userName.text = getString(R.string.user_name, name)
                binding.userEmail.text = getString(R.string.user_email, email)

            }
    }

}