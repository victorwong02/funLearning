package tarc.assignment.funlearning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LangTypeAdapter(val langList: List<LangType>): RecyclerView.Adapter<LangTypeAdapter.LangViewHolder>(){

    class LangViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var langNameText: TextView = itemView.findViewById(R.id.lang_name)
        var langDesText: TextView = itemView.findViewById(R.id.lang_des)
        var linearLayout: TextView = itemView.findViewById(R.id.linear_Layout)
        var expandableLayout: TextView = itemView.findViewById(R.id.expandable_layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LangViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_row, parent, false)

        return LangViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return langList.size
    }

    override fun onBindViewHolder(holder: LangViewHolder, position: Int) {

        val currentItem = langList[position]
        holder.langNameText.text = currentItem.langName
        holder.langDesText.text = currentItem.langDescription

//        holder.itemView.setOnClickListener {
//            clickListener.onItemClick(langList.get((position)))
//        }

        val isExpandable : Boolean = langList[position].visibility
        holder.expandableLayout.visibility = if(isExpandable) View.VISIBLE else View.GONE

        //set OnClickListener to whole linear layout
        holder.linearLayout.setOnClickListener{
//            currentItem.visibility = !currentItem.visibility
//            notifyItemChanged(position)


            val versions = langList[position]
            versions.visibility = !versions.visibility
            notifyItemChanged(position)
        }

    }

}