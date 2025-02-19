package wade.owen.watt.mood_diary.ui.page.calendar.recycle_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import wade.owen.watt.mood_diary.R
import java.time.Year

class YearListAdapter(
    private val context: Context,
    private val yearList: List<String>,
    private val listener: OnYearClickListener,
) : RecyclerView.Adapter<YearListAdapter.YearViewHolder>() {

    inner class YearViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val yearName: TextView = itemView.findViewById(R.id.title_item)

        fun bind(year: String) {
            yearName.text = year

            itemView.setOnClickListener {
                listener.onYearClick(year)
            }
        }
    }

    interface OnYearClickListener {
        fun onYearClick(year: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_chip, parent, false)
        return YearViewHolder(view)
    }

    override fun getItemCount(): Int {
        return yearList.size
    }

    override fun onBindViewHolder(holder: YearViewHolder, position: Int) {
        holder.bind(yearList[position])
    }
}