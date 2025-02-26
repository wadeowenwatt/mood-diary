package wade.owen.watt.mood_diary.ui.page.diary_dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import wade.owen.watt.mood_diary.R
import wade.owen.watt.mood_diary.databinding.FragmentDiaryDialogBinding

class DiaryDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diary_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonCancel: Button = view.findViewById(R.id.buttonCancel)
        buttonCancel.setOnClickListener {
            dismiss()
        }
    }
}