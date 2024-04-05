package hu.bme.aut.android.sporttracker.sport

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.sporttracker.R
import hu.bme.aut.android.sporttracker.database.join_table.UserSport
import hu.bme.aut.android.sporttracker.databinding.SportItemHistoryBinding
import hu.bme.aut.android.sporttracker.user.User
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class SportHistoryAdapter(private val listener: OnHistorySelectedListener) :
    RecyclerView.Adapter<SportHistoryAdapter.SportViewHolder>() {
    var sports: MutableList<Sport> = ArrayList()
    lateinit var currentUser: User

    interface OnHistorySelectedListener {
        fun onSportDeleted(sport: Sport)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        //TODO: CHANGE activity_item_user TO sport_item_history
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sport_item_history, parent, false)
        return SportViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        val item = sports[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = sports.size

    fun addSport(newSport: Sport) {
        sports.add(newSport)
        notifyItemInserted(sports.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeSport(removedSport: Sport) {
        sports.remove(removedSport)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateSport(updatedSport: List<Sport>) {
        sports.clear()

        updatedSport.forEachIndexed { index, _ ->
            if (currentUser.id == updatedSport[index].userid) {
                sports.add(updatedSport[index])
            }
        }
        notifyDataSetChanged()
    }

    inner class SportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding = SportItemHistoryBinding.bind(itemView)
        var sport: Sport? = null

        @SuppressLint("SimpleDateFormat")
        fun bind(newSport: Sport?) {
            sport = newSport
            binding.sportItemNameTextView.text = sport!!.category.toString()

            val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd")
            val date: String = dateFormat.format(Date(sport!!.date))
            binding.sportItemDateTextView.text = date

            binding.sportItemDistanceTextView.text = sport!!.distance.toString()
            binding.sportItemCalorieTextView.text = sport!!.calories.toString()
            binding.sportItemRemoveButton.setOnClickListener {
                listener.onSportDeleted(sport!!)
            }
        }
    }
}