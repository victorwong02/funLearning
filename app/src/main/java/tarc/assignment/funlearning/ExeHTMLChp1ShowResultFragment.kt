package tarc.assignment.funlearning

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import tarc.assignment.funlearning.databinding.ActivityMainBinding
import tarc.assignment.funlearning.databinding.FragmentEditProfileBinding
import tarc.assignment.funlearning.databinding.FragmentProfileBinding

class ExeHTMLChp1ShowResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exercise_html_chp1_showresult, container, false)

        return view
    }

    // To do the functionality in this fragment, etc. make a toast
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
