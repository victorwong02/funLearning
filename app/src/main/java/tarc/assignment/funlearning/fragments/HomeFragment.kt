package tarc.assignment.funlearning.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tarc.assignment.funlearning.R
import tarc.assignment.funlearning.LangType
import tarc.assignment.funlearning.LangTypeAdapter

class HomeFragment : Fragment() {

    private lateinit var adapter: LangTypeAdapter
    val langList: List<LangType> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        val view = inflater.inflate(R.layout.cardview_row, container, false)

//        initRecyclerView(view)
        return view
    }
    // To do the functionality in this fragment, etc. make a toast

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

//    private fun initRecyclerView(view: View){
//        val recyclerView = view.findViewById<RecyclerView>(R.id.list_language)
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        adapter = LangTypeAdapter(languageList, this)
//        recyclerView.adapter =
//
//    }


}
