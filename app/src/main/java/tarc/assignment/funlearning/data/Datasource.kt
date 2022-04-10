package tarc.assignment.funlearning.data

import tarc.assignment.funlearning.R
import tarc.assignment.funlearning.model.ChpModel

class Datasource {
    fun loadChapters(): List<ChpModel> {
        return listOf<ChpModel>(
            ChpModel(R.string.html_chap1, R.string.html_chap1_des),
            ChpModel(R.string.html_chap2, R.string.html_chap2_des),
            ChpModel(R.string.html_chap3, R.string.html_chap3_des)
        )
    }
}