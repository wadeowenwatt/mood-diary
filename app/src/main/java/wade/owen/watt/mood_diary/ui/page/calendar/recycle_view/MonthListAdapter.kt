package wade.owen.watt.mood_diary.ui.page.calendar.recycle_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import wade.owen.watt.mood_diary.R
import java.time.Month
import java.util.Locale

class MonthListAdapter(
    private val context: Context,
    private var selectedPosition: Int = 0,
    private val monthList: List<Month>,
    private val onItemClick: (Int) -> Unit,
) : RecyclerView.Adapter<MonthListAdapter.MonthViewHolder>() {

    inner class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val monthName: TextView = itemView.findViewById(R.id.title_item)
        private val chipItem: FrameLayout = itemView.findViewById(R.id.bg_item_chip)

        fun bind(month: Month, position: Int) {
            monthName.text = month.name.lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

            if (position == selectedPosition) {
                chipItem.setBackgroundResource(R.drawable.bg_item_month_selected)
                monthName.setTextColor(context.getColor(R.color.highlight_color))
            } else {
                chipItem.setBackgroundResource(R.drawable.bg_item_month_unselected)
                monthName.setTextColor(context.getColor(R.color.bg_color))
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_chip, parent, false)
        return MonthViewHolder(view)
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.bind(monthList[position], position)

    }

    override fun getItemCount(): Int = monthList.size
}