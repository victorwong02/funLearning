package tarc.assignment.funlearning.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tarc.assignment.funlearning.*
import tarc.assignment.funlearning.data.Datasource

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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

        // Initialize data.
        val myDataset = Datasource().loadChapters()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ChpAdapter(this, myDataset, object:OnItemClickListener{
            override fun onClick(position: Int, lessonOrExe: String) {
                changeFragment(position, lessonOrExe)
            }
        })

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }

    fun changeFragment(position: Int, lessonOrExe: String){
        val fragmentManager =  parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if(lessonOrExe == "lesson"){
            when (position) {
                0 -> {
                    val lessonHTMLChp1 = LessonHTMLChp1Fragment()
                    fragmentTransaction.replace(R.id.nav_fragment, lessonHTMLChp1)
                    fragmentTransaction.commit()
                }
                1 -> {
                    val lessonHTMLChp2 = LessonHTMLChp2Fragment()
                    fragmentTransaction.replace(R.id.nav_fragment, lessonHTMLChp2)
                    fragmentTransaction.commit()
                }
                else -> {
                    Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                }
            }
        }else {
            when (position) {
                0 -> {
                    val exeHTMLChp1 = ExeHTMLChp1Fragment()
                    fragmentTransaction.replace(R.id.nav_fragment, exeHTMLChp1)
                    fragmentTransaction.commit()
                }
                1 -> {
                    Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

interface OnItemClickListener{
    fun onClick(position: Int, lessonOrExe: String)
}
