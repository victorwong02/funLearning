package tarc.assignment.funlearning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import tarc.assignment.funlearning.fragments.HomeFragment
import tarc.assignment.funlearning.fragments.OnItemClickListener
import tarc.assignment.funlearning.model.ChpModel

class HTMLAdapter(private val context: HomeFragment, private val dataset: List<ChpModel>, var listener: OnItemClickListener): RecyclerView.Adapter<HTMLAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val htmlChap: TextView = view.findViewById(R.id.lang_name)
        val htmlChapDes: TextView = view.findViewById(R.id.lang_des)
        val linearLayout: LinearLayout = view.findViewById(R.id.linear_Layout)
        val expandableLayout: RelativeLayout = view.findViewById(R.id.expandable_layout)
        val takeLessonBtn: Button = view.findViewById(R.id.take_lesson_button)
        val takeExeBtn: Button = view.findViewById(R.id.take_exe_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //create new view holders for the RecyclerView (when there are no existing view holders that can be reused)
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.cardview_row, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //called by the layout manager in order to replace the contents of a list item view
        val item = dataset[position]
        holder.htmlChap.text = context.resources.getString(item.chapName)
        holder.htmlChapDes.text = context.resources.getString(item.chapDes)

        //expandable cardview
        val isExpandable : Boolean = dataset[position].visibility
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            item.visibility = !item.visibility
            notifyItemChanged(position)
        }

        holder.takeLessonBtn.setOnClickListener{
            listener.onClick(position,"lesson")
        }

        holder.takeExeBtn.setOnClickListener {
            listener.onClick(position,"exe")
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}