package wade.owen.watt.mood_diary.ui.page.calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import wade.owen.watt.mood_diary.R
import wade.owen.watt.mood_diary.domain.model.Diary
import wade.owen.watt.mood_diary.ui.component.HorizontalSpaceItemDecoration
import wade.owen.watt.mood_diary.ui.page.calendar.recycle_view.DiaryListAdapter
import wade.owen.watt.mood_diary.ui.page.calendar.recycle_view.MonthListAdapter
import wade.owen.watt.mood_diary.ui.page.calendar.recycle_view.YearListAdapter
import java.time.Month

@AndroidEntryPoint
class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val monthData = Month.values().toList()
        val monthListAdapter = MonthListAdapter(
            requireContext(),
            0,
            monthData,
            onItemClick = { position: Int ->
                Log.d("Calendar-List", "onMonthClick: $position")
            },
        )

        val yearData =
            listOf("2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015")
        val yearListAdapter = YearListAdapter(
            requireContext(),
            0,
            yearData,
            onItemClick = { position: Int ->
                Log.d("Calendar-List", "onMonthClick: $position")
            },
        )

        val listDiaryFakeData = mutableListOf(
            Diary(id= 1, title = "Sender", mood = 1, liked = true, dateTime = "2025-02-22T17:09:54+12:00"),
            Diary(id= 2, title = "Sender", mood = 3, liked = false, dateTime = "2025-02-22T17:09:54+12:00"),
            Diary(id= 3, title = "Sender", mood = 1, liked = true, dateTime = "2025-02-22T17:09:54+12:00"),
            Diary(id= 4, title = "Sender", mood = 5, liked = true, dateTime = "2025-02-22T17:09:54+12:00"),
            Diary(id= 5, title = "Sender", mood = 4, liked = false, dateTime = "2025-02-22T17:09:54+12:00"),
            Diary(id= 6, title = "Sender", mood = 2, liked = true, dateTime = "2025-02-22T17:09:54+12:00"),
        )
        val diaryListAdapter = DiaryListAdapter(
            requireContext(),
            listDiaryFakeData,
            onItemClick = { position: Int ->
                Log.d("Diary-List", "onDiaryClick: $position")
            },
            onLikeClick = { position: Int ->
                Log.d("Diary-List", "onLikeClick: $position")
            }
        )

        // Month choice
        val listMonthRecyclerView: RecyclerView = view.findViewById(R.id.month_list)

        listMonthRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(16))

        listMonthRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listMonthRecyclerView.adapter = monthListAdapter

        // Year choice
        val listYearRecyclerView: RecyclerView = view.findViewById(R.id.year_list)

        listYearRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(16))

        listYearRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listYearRecyclerView.adapter = yearListAdapter

        // Diary list
        val listDiaryRecyclerView: RecyclerView = view.findViewById(R.id.diary_list)

        listDiaryRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listDiaryRecyclerView.adapter = diaryListAdapter
    }
}