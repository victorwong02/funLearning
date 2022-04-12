package tarc.assignment.funlearning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ExeHTMLChp1ShowResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exercise_html_chp1_showresult, container, false)

        val correctQues = view.findViewById<TextView>(R.id.numOfCorrectQ)
        val percentage = view.findViewById<TextView>(R.id.percentage)
        //fetch data
        val args = this.arguments
        val num = args?.get("num")
        val percent = args?.get("percent")
        correctQues.text = num.toString()
        percentage.text = percent.toString()

        var prog = percent.toString().toInt()
        val progressBar = view.findViewById<ProgressBar>(R.id.HTML_Chap1_Exe_ShowResult_Percentage)
        progressBar.progress = prog

        val nextExeBtn = view.findViewById<Button>(R.id.next_exe2_button)
        nextExeBtn.setOnClickListener {
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

        val nextChapBtn = view.findViewById<Button>(R.id.next_chap2_button)
        nextChapBtn.setOnClickListener {
            val fragmentManager =  parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val lessonHTMLChp2 = LessonHTMLChp2Fragment()
            fragmentTransaction.replace(R.id.nav_fragment, lessonHTMLChp2)
            fragmentTransaction.setReorderingAllowed(true)
            fragmentTransaction.commit()
        }

        return view
    }

    // To do the functionality in this fragment, etc. make a toast
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
