package tarc.assignment.funlearning.data

import tarc.assignment.funlearning.model.LangType
import tarc.assignment.funlearning.R

class Datasource {

    fun loadAffirmations(): List<LangType> {
        return listOf<LangType>(
            LangType(R.string.html_chap1),
            LangType(R.string.html_chap2),
            LangType(R.string.html_chap3)
        )
    }

}