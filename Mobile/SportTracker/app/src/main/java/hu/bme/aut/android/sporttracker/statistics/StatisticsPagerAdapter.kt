package hu.bme.aut.android.sporttracker.statistics

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import hu.bme.aut.android.sporttracker.database.join_table.UserSport
import hu.bme.aut.android.sporttracker.sport.Sport
import hu.bme.aut.android.sporttracker.user.User

class StatisticsPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    lateinit var data: UserSport
    var sports: MutableList<Sport> = ArrayList()
    lateinit var currentUser: User

    companion object {
        private const val NUM_PAGES: Int = 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StatisticsPieChartFragment(sports)
            1 -> StatisticsBarChartFragment(sports)
            else -> StatisticsPieChartFragment(sports)
        }
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

    override fun getItemCount(): Int = NUM_PAGES
}