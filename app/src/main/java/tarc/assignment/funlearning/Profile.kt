package tarc.assignment.funlearning


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
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
        val view = binding.root

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.logoutButton.setOnClickListener {
            logoutDialog(it)
        }

        binding.editProfile.setOnClickListener {

        }

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

}