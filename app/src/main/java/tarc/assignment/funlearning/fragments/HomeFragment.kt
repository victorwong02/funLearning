package tarc.assignment.funlearning.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tarc.assignment.funlearning.R
import tarc.assignment.funlearning.LangType

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    // To do the functionality in this fragment, etc. make a toast

    val langList = ArrayList<LangType>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
//        setRecyclerView()

    }

//    private fun setRecyclerView() {
//        val langAdapter = langTypeAdapter(langList)
//        recyclerView.adapter = langAdapter
//        recylcerView.setHasFixedSize(true)
//    }

    private fun initData() {
        langList.add(
            LangType(
            "Chapter 1 : Introduction to HTML",
            "This chapter will show some of the tags that is important for HTML."
        )
        )
        langList.add(
            LangType(
            "Chapter 2 : HTML Attributes",
            "HTML attributes provide additional information about HTML elements."
        )
        )
    }

}