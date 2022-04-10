package tarc.assignment.funlearning.fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import tarc.assignment.funlearning.ForgetPasswordActivity
import tarc.assignment.funlearning.R
import tarc.assignment.funlearning.databinding.FragmentEditProfileBinding
import java.io.File
import java.util.jar.Manifest

class EditProfileFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val db = Firebase.firestore

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

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

        binding.editPic.setOnClickListener{

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
            Toast.makeText(context, "Nothing Has Been Updated", Toast.LENGTH_SHORT).show()
        }
        else{
            user.update("username",newName)
            Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadPic() {
        //pick an image
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        

    }


}

