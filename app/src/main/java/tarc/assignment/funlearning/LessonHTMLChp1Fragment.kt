package tarc.assignment.funlearning

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import tarc.assignment.funlearning.fragments.EditProfileFragment

class LessonHTMLChp1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lesson_html_chp1, container, false)

        val htmlExeHTMLChp1 = view.findViewById<Button>(R.id.take_html_exe1_button)
        htmlExeHTMLChp1.setOnClickListener {
            val fragmentManager =  parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragmentExeHTMLChp1 = ExeHTMLChp1Fragment()
            fragmentTransaction.replace(R.id.nav_fragment, fragmentExeHTMLChp1)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        val htmlLessonHTMLChp2 = view.findViewById<Button>(R.id.next_html_chap2_button)
        htmlLessonHTMLChp2.setOnClickListener {
            val fragmentManager =  parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragmentLessonHTMLChp2 = LessonHTMLChp2Fragment()
            fragmentTransaction.replace(R.id.nav_fragment, fragmentLessonHTMLChp2)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }

    // To do the functionality in this fragment, etc. make a toast
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoView = view.findViewById<VideoView>(R.id.intro_HTML_vid)

        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoView)

        val offlineUri = Uri.parse("android.resource://tarc.assignment.funlearning/${R.raw.intro_to_html}")

        videoView.setMediaController(mediaController)
        videoView.setVideoURI(offlineUri)
        videoView.requestFocus()

    }
}