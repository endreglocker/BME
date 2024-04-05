package hu.bme.aut.android.sporttracker.user

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import hu.bme.aut.android.sporttracker.R
import hu.bme.aut.android.sporttracker.database.join_table.UserSport
import hu.bme.aut.android.sporttracker.databinding.ActivityDialogNewUserBinding
import hu.bme.aut.android.sporttracker.sport.Sport


class SportTrackerAddUserDialogFragment : AppCompatDialogFragment() {

    private lateinit var binding: ActivityDialogNewUserBinding
    private lateinit var listener: AddUserDialogListener

    interface AddUserDialogListener {
        fun onUserAdded(user: UserSport)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = ActivityDialogNewUserBinding.inflate(LayoutInflater.from(context))

        listener = context as? AddUserDialogListener
            ?: throw RuntimeException("Activity must implement the AddUserDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_user)
            .setView(binding.root)
            .setPositiveButton(R.string.ok) { _, _ ->
                val sports: MutableList<Sport> = ArrayList()

                val userSex = when (binding.radioGroup.checkedRadioButtonId) {
                    R.id.radioButtonMale -> 0
                    R.id.radioButtonFemale -> 1
                    R.id.radioButtonOther -> 2
                    else -> 2
                }

                val data = UserSport(
                    User(
                        name = binding.AddingNewUserName.text.toString(),
                        mass = binding.AddingWeight.text.toString().toInt(),
                        height = binding.AddingHeight.text.toString().toInt(),
                        age = binding.AddingAge.text.toString().toInt(),
                        sex = userSex
                    ), sports
                )
                listener.onUserAdded(data)
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }
}