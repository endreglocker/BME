package hu.bme.aut.android.sporttracker.sport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.sporttracker.database.UserDataBase
import hu.bme.aut.android.sporttracker.database.join_table.UserSport
import hu.bme.aut.android.sporttracker.databinding.SportActivityBinding
import hu.bme.aut.android.sporttracker.statistics.StatisticsActivity
import java.io.Serializable
import kotlin.concurrent.thread

class SportActivity : AppCompatActivity(), SportHistoryAdapter.OnHistorySelectedListener,
    SportAddHistoryDialogFragment.AddSportDialogListener {

    private lateinit var binding: SportActivityBinding
    private lateinit var adapter: SportHistoryAdapter
    private lateinit var database: UserDataBase
    private lateinit var data: UserSport
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = UserDataBase.getDatabase(applicationContext)
        binding = SportActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFab2()
        initRecyclerView()
        loadItems()

        binding.btnStatistics.setOnClickListener {
            openStatisticsActivity()
        }
    }

    private fun initFab2() {
        binding.sportFab.setOnClickListener {
            SportAddHistoryDialogFragment().show(
                supportFragmentManager,
                SportAddHistoryDialogFragment::class.java.simpleName
            )
        }
    }

    private fun initRecyclerView() {
        binding.sportRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SportHistoryAdapter(this)
        binding.sportRecyclerView.adapter = adapter

        data = (intent.getSerializableExtra("UserSport") as UserSport)

        adapter.currentUser = data.user
        adapter.sports = data.sport
    }

    private fun openStatisticsActivity() {
        val showDetailsIntent = Intent()
        showDetailsIntent.setClass(this@SportActivity, StatisticsActivity::class.java)

        showDetailsIntent.putExtra("UserSport", data as Serializable)
        startActivity(showDetailsIntent)
    }

    override fun onSportAdded(sport: Sport?) {
        thread {
            sport!!.userid = adapter.currentUser.id
            val insertId = database.userDao().insertSport(sport)
            sport.id = insertId
            runOnUiThread {
                adapter.addSport(sport)
            }
        }
    }

    override fun onSportDeleted(sport: Sport) {
        thread {
            database.userDao().deleteSport(sport)
            runOnUiThread {
                adapter.removeSport(sport)
            }
        }
    }

    private fun loadItems() {
        thread {
            val userSport = database.userDao().getAllSport()
            runOnUiThread {
                adapter.updateSport(userSport)
            }
        }
    }
}