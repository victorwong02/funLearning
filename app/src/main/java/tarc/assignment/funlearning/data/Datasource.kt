package tarc.assignment.funlearning.data

import tarc.assignment.funlearning.R
import tarc.assignment.funlearning.model.ChpData

class Datasource {
    fun loadChapters(): List<ChpData> {
        return listOf<ChpData>(
            ChpData(R.string.html_chap1, R.string.html_chap1_des),
            ChpData(R.string.html_chap2, R.string.html_chap2_des),
            ChpData(R.string.html_chap3, R.string.html_chap3_des)
        )
    }
}