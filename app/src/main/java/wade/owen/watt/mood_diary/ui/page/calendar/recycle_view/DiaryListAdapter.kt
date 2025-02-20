package wade.owen.watt.mood_diary.ui.page.calendar.recycle_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wade.owen.watt.mood_diary.R
import wade.owen.watt.mood_diary.domain.model.Diary

class DiaryListAdapter(
    private val context: Context,
    private val diaryList: List<Diary>,
    private val onItemClick: (Int) -> Unit,
): RecyclerView.Adapter<DiaryListAdapter.DiaryViewHolder>() {
    inner class DiaryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(diary: Diary, position: Int) {
            itemView.setOnClickListener {
                onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_diary, parent, false)
        return DiaryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(diaryList[position], position)
    }

}