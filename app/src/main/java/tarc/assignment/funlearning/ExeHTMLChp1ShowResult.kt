package tarc.assignment.funlearning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

/**
 * A simple [Fragment] subclass.
 * Use the [ExeHTMLChp1ShowResult.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExeHTMLChp1ShowResult : Fragment() {
    companion object {

        fun newInstance(): ExeHTMLChp1ShowResult {
            return ExeHTMLChp1ShowResult()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.exercise_html_chp1_showresult, container, false)
    }
}

