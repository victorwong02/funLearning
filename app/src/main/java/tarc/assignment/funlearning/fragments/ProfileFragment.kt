package tarc.assignment.funlearning.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tarc.assignment.funlearning.LoginActivity
import tarc.assignment.funlearning.R
import tarc.assignment.funlearning.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val db = Firebase.firestore

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onStart() {
        super.onStart()
        loadAvatar()
        loadInfo()
        loadHistory()
    }

    override fun onResume() {
        super.onResume()
        loadAvatar()
        loadInfo()
        loadHistory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.logoutButton.setOnClickListener {
            logoutDialog()
        }

        binding.editProfile.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragmentEditProfile = EditProfileFragment()
            fragmentTransaction.replace(R.id.nav_fragment, fragmentEditProfile)
            fragmentTransaction.setReorderingAllowed(true)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun logoutDialog() {

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

        alert.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.parseColor("#83677B"))
        alert.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#83677B"))
    }

    private fun loadInfo(){
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser!!.uid

        db.collection("user").document(uid).get()
            .addOnSuccessListener { document ->
                val name = document.getString("username").toString()
                val email = firebaseUser.email

                binding.userName.text = getString(R.string.user_name, name)
                binding.userEmail.text = getString(R.string.user_email, email)
            }

    }

    private fun loadHistory(){
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser!!.uid

        val notStarted = "Not Started"

        db.collection("user").document(uid).get()
            .addOnSuccessListener { document ->
                val cNotes = document.getString("c_notes").toString()
                val cExercise  = document.getString("c_exercises").toString()

                val htmlNotes = document.getString("html_notes").toString()
                val htmlExercise  = document.getString("html_exercises").toString()

                if(cNotes == "null"){
                    binding.cNotes.text = getString(R.string.c_lastnotes, notStarted)
                }
                else{
                    binding.cNotes.text = getString(R.string.c_lastnotes, cNotes)
                }

                if(cExercise == "null"){
                    binding.cExercise.text = getString(R.string.c_lastexercise, notStarted)
                }
                else{
                    binding.cExercise.text = getString(R.string.c_lastexercise, cExercise)
                }

                if(htmlNotes == "null"){
                    binding.htmlNotes.text = getString(R.string.html_lastnotes, notStarted)
                }
                else{
                    binding.htmlNotes.text = getString(R.string.html_lastnotes, htmlNotes)
                }

                if(htmlExercise == "null"){
                    binding.htmlExercise.text  = getString(R.string.html_lastexercise, notStarted)
                }
                else{
                    binding.htmlExercise.text  = getString(R.string.html_lastexercise, htmlExercise)
                }

            }
    }


    private fun loadAvatar(){
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser!!.uid

        db.collection("user").document(uid).get()
            .addOnSuccessListener { document ->

                when(document.getString("profile_pic").toString()){
                    "avatar1" -> binding.profilepic.setImageDrawable(resources.getDrawable(R.drawable.avatar1))
                    "avatar2" -> binding.profilepic.setImageDrawable(resources.getDrawable(R.drawable.avatar2))
                    "avatar3" -> binding.profilepic.setImageDrawable(resources.getDrawable(R.drawable.avatar3))
                    else -> binding.profilepic.setImageDrawable(resources.getDrawable(R.drawable.profile_pic))
                }

            }
    }



}