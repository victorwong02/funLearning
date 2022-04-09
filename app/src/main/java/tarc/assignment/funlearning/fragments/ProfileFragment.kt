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
        loadInfo()
        loadHistory()
    }

    override fun onResume() {
        super.onResume()
        loadInfo()
        loadHistory()
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

        alert.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.parseColor("#83677B"))
        alert.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#83677B"))
    }

    private fun loadInfo(){
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser!!.uid

        db.collection("user").document(uid).get()
            .addOnSuccessListener { document ->
                val name = document.getString("username").toString()
                val email = firebaseUser!!.email

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
                val cnotes = document.getString("c_notes").toString()
                val cexercise  = document.getString("c_exercises").toString()

                val htmlnotes = document.getString("html_notes").toString()
                val htmlexercise  = document.getString("html_exercises").toString()

                if(cnotes == "null"){
                    binding.cNotes.text = getString(R.string.c_lastnotes, notStarted)
                }
                else{
                    binding.cNotes.text = getString(R.string.c_lastnotes, cnotes)
                }

                if(cexercise == "null"){
                    binding.cExercise.text = getString(R.string.c_lastexercise, notStarted)
                }
                else{
                    binding.cExercise.text = getString(R.string.c_lastexercise, cexercise)
                }

                if(htmlnotes == "null"){
                    binding.htmlNotes.text = getString(R.string.html_lastnotes, notStarted)
                }
                else{
                    binding.htmlNotes.text = getString(R.string.html_lastnotes, htmlnotes)
                }

                if(htmlexercise == "null"){
                    binding.htmlExercise.text  = getString(R.string.html_lastexercise, notStarted)
                }
                else{
                    binding.htmlExercise.text  = getString(R.string.html_lastexercise, htmlexercise)
                }

            }
    }

}