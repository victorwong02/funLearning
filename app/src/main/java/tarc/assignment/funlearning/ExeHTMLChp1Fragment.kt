package tarc.assignment.funlearning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tarc.assignment.funlearning.databinding.FragmentExerciseHtmlChp1Binding

class ExeHTMLChp1Fragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val db = Firebase.firestore

    private var _binding: FragmentExerciseHtmlChp1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        _binding = FragmentExerciseHtmlChp1Binding.inflate(inflater, container, false)
        val view = binding.root

        firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser!!.uid
        val user = db.collection("user").document(uid)

        val showResult = view.findViewById<Button>(R.id.HTML_Chap1_Exe_ShowResult)
        showResult.setOnClickListener {

            var numOfCorrectQues = 0

            val empty = checkEmpty()

            if(empty == 0){
                if(binding.HTMLChap1ExeQ1Opt1.isChecked){
                    numOfCorrectQues++
                }

                if(binding.HTMLChap1ExeQ2Input.text.toString() == "6"){
                    numOfCorrectQues++
                }

                if(binding.HTMLChap1ExeQ3Choose2.isChecked && binding.HTMLChap1ExeQ3Choose3.isChecked){
                    numOfCorrectQues++
                }

                if(binding.HTMLChap1ExeQ4Opt2.isChecked){
                    numOfCorrectQues++
                }

                if(binding.HTMLChap1ExeQ5Opt1.isChecked){
                    numOfCorrectQues++
                }

                val bundle = Bundle()
                bundle.putString("num", numOfCorrectQues.toString())
                var percentage = ((numOfCorrectQues.toDouble()/5.0)*100.0).toInt()
                bundle.putString("percent", percentage.toInt().toString())

                //save data to database
                user.get().addOnSuccessListener { document->
                    val latestChap = document.getString("html_exercises")
                    if(latestChap != null){
                        val lastChar = latestChap.last()
                        val lastInt = Integer.parseInt(lastChar.toString())

                        if(lastInt < 1){
                            user.update("html_exercises", "Chapter 1 ($percentage%)")
                        }
                    }else{
                        user.update("html_exercises", "Chapter 1 ($percentage%)")
                    }
                }

                //Switch Fragment to show result fragment
                val fragmentManager =  parentFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragmentshowResult = ExeHTMLChp1ShowResultFragment()
                fragmentshowResult.arguments = bundle
                fragmentTransaction.replace(R.id.nav_fragment, fragmentshowResult)
                fragmentTransaction.setReorderingAllowed(true)
                fragmentTransaction.commit()
            }


        }

        return view
    }

    // To do the functionality in this fragment, etc. make a toast
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun checkEmpty(): Int {
        var empty = 0

        //check q1
        if(!(binding.HTMLChap1ExeQ1Opt1.isChecked || binding.HTMLChap1ExeQ1Opt2.isChecked || binding.HTMLChap1ExeQ1Opt3.isChecked)){
            empty++
        }

        //check q2
        if(binding.HTMLChap1ExeQ2Input.text.toString() == ""){
            empty++
        }

        //check q3
        if(!(binding.HTMLChap1ExeQ3Choose1.isChecked || binding.HTMLChap1ExeQ3Choose2.isChecked || binding.HTMLChap1ExeQ3Choose3.isChecked || binding.HTMLChap1ExeQ3Choose4.isChecked)){
            empty++
        }

        //check q4
        if(!(binding.HTMLChap1ExeQ4Opt1.isChecked || binding.HTMLChap1ExeQ4Opt2.isChecked || binding.HTMLChap1ExeQ4Opt3.isChecked)){
            empty++
        }

        //check q5
        if(!(binding.HTMLChap1ExeQ5Opt1.isChecked || binding.HTMLChap1ExeQ5Opt2.isChecked || binding.HTMLChap1ExeQ5Opt3.isChecked)){
            empty++
        }

        if(empty != 0){
            Toast.makeText(context, "Please complete all the questions!", Toast.LENGTH_SHORT).show()
        }


        return empty
    }

}