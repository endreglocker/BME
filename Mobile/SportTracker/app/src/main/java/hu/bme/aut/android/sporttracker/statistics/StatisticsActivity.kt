package hu.bme.aut.android.sporttracker.statistics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import hu.bme.aut.android.sporttracker.R
import hu.bme.aut.android.sporttracker.database.UserDataBase
import hu.bme.aut.android.sporttracker.database.join_table.UserSport
import hu.bme.aut.android.sporttracker.databinding.StatisticsActivityBinding
import kotlin.concurrent.thread

class StatisticsActivity : AppCompatActivity() {
    private lateinit var binding: StatisticsActivityBinding
    private lateinit var data: UserSport
    private lateinit var dataBase: UserDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBase = UserDataBase.getDatabase(applicationContext)
        binding = StatisticsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = (intent.getSerializableExtra("UserSport") as UserSport)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        val detailsPagerAdapter = StatisticsPagerAdapter(this)
        detailsPagerAdapter.data = data
        detailsPagerAdapter.currentUser = data.user
        detailsPagerAdapter.sports = data.sport

        thread {
            val userSport = dataBase.userDao().getAllSport()
            runOnUiThread {
                detailsPagerAdapter.updateSport(userSport)
            }
        }

        binding.mainViewPager.adapter = detailsPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.mainViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.circle)
                1 -> getString(R.string.bar)
                else -> ""
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}