package wade.owen.watt.mood_diary.ui.page.calendar.recycle_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import wade.owen.watt.mood_diary.R
import java.time.Month

class MonthListAdapter(
    private val context: Context,
    private val monthList: List<Month>,
    private val listener: OnMonthClickListener
) : RecyclerView.Adapter<MonthListAdapter.MonthViewHolder>() {

    inner class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val monthName: TextView = itemView.findViewById(R.id.month_name)

        fun bind(month: Month) {
            monthName.text = month.name
            itemView.setOnClickListener {
                listener.onMonthClick(month)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_month, parent, false)
        return MonthViewHolder(view)
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.bind(monthList[position])
    }

    override fun getItemCount(): Int = monthList.size

    interface OnMonthClickListener {
        fun onMonthClick(month: Month)
    }
}