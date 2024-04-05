package hu.bme.aut.android.sporttracker.sport

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDialogFragment
import hu.bme.aut.android.sporttracker.R
import hu.bme.aut.android.sporttracker.databinding.SportDialogNewSportBinding

class SportAddHistoryDialogFragment : AppCompatDialogFragment() {
    private lateinit var binding: SportDialogNewSportBinding
    private lateinit var listener: AddSportDialogListener

    interface AddSportDialogListener {
        fun onSportAdded(sport: Sport?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = SportDialogNewSportBinding.inflate(LayoutInflater.from(context))

        binding.spCategory.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.sport_types)
        )

        listener = context as? AddSportDialogListener
            ?: throw RuntimeException("Activity must implement the AddSportDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val sportTime: Long = run {
            val datePicker = binding.datePickerActions
            val year = datePicker.year
            val month = datePicker.month
            val day = datePicker.dayOfMonth
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            return@run calendar.timeInMillis
        }

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_sport)
            .setView(binding.root)
            .setPositiveButton(R.string.ok) { _, _ ->
                val currentSport = Sport(
                    category = Sport.Type.getByOrdinal(binding.spCategory.selectedItemPosition)
                        ?: Sport.Type.RUN,
                    date = sportTime,
                    distance = binding.SportDistanceText.text.toString().toInt(),
                    calories = binding.SportCaloryText.text.toString().toInt()
                )
                listener.onSportAdded(currentSport)
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }
}