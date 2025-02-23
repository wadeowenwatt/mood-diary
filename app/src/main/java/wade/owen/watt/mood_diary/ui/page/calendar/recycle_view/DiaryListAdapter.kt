package wade.owen.watt.mood_diary.ui.page.calendar.recycle_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import wade.owen.watt.mood_diary.R
import wade.owen.watt.mood_diary.domain.model.Diary
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class DiaryListAdapter(
    private val context: Context,
    private val diaryList: MutableList<Diary>,
    private val onItemClick: (Int) -> Unit,
    private val onLikeClick: (Int) -> Unit,
) : RecyclerView.Adapter<DiaryListAdapter.DiaryViewHolder>() {

    inner class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title_diary)
        private val dateString = itemView.findViewById<TextView>(R.id.date)
        private val timeString = itemView.findViewById<TextView>(R.id.time)
        private val imageMood = itemView.findViewById<ImageView>(R.id.mood_image)
        private val imageHeart = itemView.findViewById<ImageView>(R.id.ic_heart)

        fun bind(diary: Diary, position: Int) {
            val zonedDateTime =
                ZonedDateTime.parse(diary.dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            val dateTime: Date = Date.from(zonedDateTime.toInstant())

            val date = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(dateTime)
            val time = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(dateTime)

            title.text = diary.title
            dateString.text = date
            timeString.text = time

            when (diary.mood) {
                1 -> imageMood.setImageResource(R.drawable.ic_mood_1)
                2 -> imageMood.setImageResource(R.drawable.ic_mood_2)
                3 -> imageMood.setImageResource(R.drawable.ic_mood_3)
                4 -> imageMood.setImageResource(R.drawable.ic_mood_4)
                5 -> imageMood.setImageResource(R.drawable.ic_mood_5)
            }

            if (diary.liked == true) {
                imageHeart.setImageResource(R.drawable.ic_heart_selected)
            } else {
                imageHeart.setImageResource(R.drawable.ic_heart_unselected)
            }

            itemView.setOnClickListener {
                onItemClick(position)
            }
            imageHeart.setOnClickListener {
                diaryList[position] = diaryList[position].copy(liked = !diaryList[position].liked!!)
                notifyItemChanged(position)
                onLikeClick(position)
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