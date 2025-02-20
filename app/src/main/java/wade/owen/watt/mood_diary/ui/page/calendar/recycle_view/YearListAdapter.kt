package wade.owen.watt.mood_diary.ui.page.calendar.recycle_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import wade.owen.watt.mood_diary.R

class YearListAdapter(
    private val context: Context,
    private var selectedPosition: Int = 0,
    private val yearList: List<String>,
    private val onItemClick: (Int) -> Unit,
) : RecyclerView.Adapter<YearListAdapter.YearViewHolder>() {

    inner class YearViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val yearName: TextView = itemView.findViewById(R.id.title_item)
        private val chipItem: FrameLayout = itemView.findViewById(R.id.bg_item_chip)

        fun bind(year: String, position: Int) {
            yearName.text = year

            if (position == selectedPosition) {
                chipItem.setBackgroundResource(R.drawable.bg_item_month_selected)
                yearName.setTextColor(context.getColor(R.color.highlight_color))
            } else {
                chipItem.setBackgroundResource(R.drawable.bg_item_month_unselected)
                yearName.setTextColor(context.getColor(R.color.bg_color))
            }

            itemView.setOnClickListener {
                val previousPos = selectedPosition
                if (previousPos != position) {
                    selectedPosition = position
                    notifyItemChanged(previousPos)
                    notifyItemChanged(selectedPosition)
                    onItemClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_chip, parent, false)
        return YearViewHolder(view)
    }

    override fun getItemCount(): Int {
        return yearList.size
    }

    override fun onBindViewHolder(holder: YearViewHolder, position: Int) {
        holder.bind(yearList[position], position)
    }
}