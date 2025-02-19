package wade.owen.watt.mood_diary.ui.page.calendar

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wade.owen.watt.mood_diary.R
import wade.owen.watt.mood_diary.ui.component.HorizontalSpaceItemDecoration
import wade.owen.watt.mood_diary.ui.page.calendar.recycle_view.MonthListAdapter
import wade.owen.watt.mood_diary.ui.page.calendar.recycle_view.YearListAdapter
import java.time.Month
import java.time.Year


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
        val monthListAdapter = MonthListAdapter(requireContext(), monthData, object : MonthListAdapter.OnMonthClickListener {
            override fun onMonthClick(month: Month) {
                Log.d("Calendar-List", "onMonthClick: $month")
            }
        })

        val yearData = listOf("2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015")
        val yearListAdapter = YearListAdapter(requireContext(), yearData, object : YearListAdapter.OnYearClickListener {
            override fun onYearClick(year: String) {
                Log.d("Calendar-List", "onMonthClick: $year")
            }
        })

        val listMonthRecyclerView: RecyclerView = view.findViewById(R.id.month_list)

        listMonthRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(16))

        listMonthRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listMonthRecyclerView.adapter = monthListAdapter

        val listYearRecyclerView: RecyclerView = view.findViewById(R.id.year_list)

        listYearRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(16))

        listYearRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listYearRecyclerView.adapter = yearListAdapter
    }
}