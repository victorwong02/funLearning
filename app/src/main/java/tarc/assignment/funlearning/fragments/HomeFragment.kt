package tarc.assignment.funlearning.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tarc.assignment.funlearning.ChpAdapter
import tarc.assignment.funlearning.LessonHTMLChp1
import tarc.assignment.funlearning.R
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
            override fun onClick() {
                changeFragment()
            }
        })

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }

    fun changeFragment(){
        val fragmentManager =  parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val lessonHTMLChp1 = LessonHTMLChp1()
        fragmentTransaction.replace(R.id.nav_fragment, lessonHTMLChp1)
//        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}

interface OnItemClickListener{
    fun onClick()
}
