package tarc.assignment.funlearning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LangTypeAdapter(private val langList: List<LangType>): RecyclerView.Adapter<LangTypeAdapter.LanguageType>(){

    class LanguageType(itemView: View): RecyclerView.ViewHolder(itemView) {

        var langNameText: TextView = itemView.findViewById(R.id.lang_name)
        var langDesText: TextView = itemView.findViewById(R.id.lang_des)
        var linearLayout: TextView = itemView.findViewById(R.id.linear_Layout)
        var expandableLayout: TextView = itemView.findViewById(R.id.expandable_layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageType {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_row, parent, false)

        return LanguageType(view)
    }

    override fun getItemCount(): Int {
        return langList.size
    }

    override fun onBindViewHolder(holder: LanguageType, position: Int) {

        val language: LangType = langList[position]
        holder.langNameText.text = language.langName
        holder.langDesText.text = language.langDescription

        val isExpandable : Boolean = langList[position].expandable
        holder.expandableLayout.visibility = if(isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener{
            val lang = langList[position]
            lang.expandable = !lang.expandable
            notifyItemChanged(position)
        }

    }

}