package tarc.assignment.funlearning.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tarc.assignment.funlearning.*

class HTMLHomeFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // To do the functionality in this fragment, etc. make a toast
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        // Initialize data.
//        val myDataset = Datasource().loadHtml()
//
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
//        recyclerView.adapter = HTMLAdapter(this, myDataset, object: AdapterView.OnItemClickListener {
//            override fun onClick(position: Int, lessonOrExe: String) {
//                changeFragment(position, lessonOrExe)
//            }
//        })
//
//        // Use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true)
    }

    fun changeFragment(position: Int, lessonOrExe: String){
        firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser!!.uid
        val user = db.collection("user").document(uid)

        val fragmentManager =  parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if(lessonOrExe == "lesson"){
            when (position) {
                0 -> {
                    user.get().addOnSuccessListener { document->
                        val latestChap = document.getString("html_notes")
                        if(latestChap != null){
                            val lastChar = latestChap.last()
                            val lastInt = Integer.parseInt(lastChar.toString())

                            if(lastInt < 1){
                                user.update("html_notes", "Chapter 1")
                            }
                        }else{
                            user.update("html_notes", "Chapter 1")
                        }
                    }
                    val lessonHTMLChp1 = LessonHTMLChp1Fragment()
                    fragmentTransaction.replace(R.id.nav_fragment, lessonHTMLChp1)
                    fragmentTransaction.setReorderingAllowed(true)
                    fragmentTransaction.commit()
                }
                1 -> {
                    user.get().addOnSuccessListener { document->
                        val latestChap = document.getString("html_notes")
                        if(latestChap != null){
                            val lastChar = latestChap.last()
                            val lastInt = Integer.parseInt(lastChar.toString())

                            if(lastInt < 2){
                                user.update("html_notes", "Chapter 2")
                            }
                        }else{
                            user.update("html_notes", "Chapter 2")
                        }
                    }
                    val lessonHTMLChp2 = LessonHTMLChp2Fragment()
                    fragmentTransaction.replace(R.id.nav_fragment, lessonHTMLChp2)
                    fragmentTransaction.setReorderingAllowed(true)
                    fragmentTransaction.commit()
                }
                else -> {
                    Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                }
            }
        }else {
            when (position) {
                0 -> {
                    user.get().addOnSuccessListener { document->
                        val latestChap = document.getString("html_exercises")
                        if(latestChap != null){
                            val lastChar = latestChap.last()
                            val lastInt = Integer.parseInt(lastChar.toString())

                            if(lastInt < 1){
                                user.update("html_exercises", "Exercise 1")
                            }
                        }else{
                            user.update("html_exercises", "Exercise 1")
                        }
                    }
                    val exeHTMLChp1 = ExeHTMLChp1Fragment()
                    fragmentTransaction.replace(R.id.nav_fragment, exeHTMLChp1)
                    fragmentTransaction.setReorderingAllowed(true)
                    fragmentTransaction.commit()
                }
                else -> {
                    Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

