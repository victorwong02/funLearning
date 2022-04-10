package tarc.assignment.funlearning

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class LessonHTMLChp1 : Fragment() {

//    var videoView: VideoView? = null
//    var mediaController: MediaController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)




        return v
    }

    // To do the functionality in this fragment, etc. make a toast

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        videoView = findViewById<View>(R.id.intro_HTML_vid) as VideoView?
//
//        if (mediaController == null){
//            mediaController = MediaController(this)
//            //set anchor view for videoView
//            mediaController!!.setAnchorView(this.videoView)
//        }
//
//        videoView!!.setMediaController(mediaController)
//
//        //path of video
//        videoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.intro_to_html))
//
//        videoView!!.requestFocus()
//
//        videoView!!.start()
    }
}