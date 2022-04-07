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

class LessonHTMLChp1 : AppCompatActivity() {

    var videoView: VideoView? = null
    var mediaController: MediaController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lesson_html_chp1)

        videoView = findViewById<View>(R.id.intro_HTML_vid) as VideoView?

        if (mediaController == null){
            mediaController = MediaController(this)
            //set anchor view for videoView
            mediaController!!.setAnchorView(this.videoView)
        }

        videoView!!.setMediaController(mediaController)

        //path of video
        videoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.intro_to_html))

        videoView!!.requestFocus()

        videoView!!.start()

    }

    fun takeExe1(view: View) {

    }
}