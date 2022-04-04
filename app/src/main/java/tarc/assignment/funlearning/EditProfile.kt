package tarc.assignment.funlearning

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Toast
import tarc.assignment.funlearning.databinding.FragmentEditProfileBinding
import tarc.assignment.funlearning.databinding.FragmentProfileBinding

class EditProfile : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var binding1: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.saveInfo.setOnClickListener{ editProfile() }

        binding.changePswd.setOnClickListener{
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }
    }

    private fun editProfile() {
        val newName = binding.editName.text.toString()
        val newEmail = binding.editEmail.text.toString()

        if (newName.trim() == null) {
            if(newEmail.trim() == null) {
                Toast.makeText(this, "Nothing Has Been Updated", Toast.LENGTH_SHORT).show()
            }
            else {
                binding1.userEmail.text = getString(R.string.user_email,newEmail)
                Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            if(newEmail.trim() == null) {
                binding1.userName.text = getString(R.string.user_name,newName)
                Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
            }
            else {
                binding1.userEmail.text = getString(R.string.user_email,newEmail)
                binding1.userName.text = getString(R.string.user_name,newName)
                Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
