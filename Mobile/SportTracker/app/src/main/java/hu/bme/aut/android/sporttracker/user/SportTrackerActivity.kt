/**
 * Források:
 * https://viauac00.github.io/laborok/laborok/
 * https://www.geeksforgeeks.org/android-create-barchart-with-kotlin/
 * https://github.com/PhilJay/MPAndroidChart
 * https://developer.android.com/training/data-storage/room/accessing-data
 * https://developer.android.com/training/data-storage/room/relationships
 * https://medium.com/@clyeung0714/using-mpandroidchart-for-android-application-barchart-540a55b4b9ef
 * https://www.geeksforgeeks.org/how-to-create-group-barchart-in-android/
 */

package hu.bme.aut.android.sporttracker.user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.sporttracker.database.UserDataBase
import hu.bme.aut.android.sporttracker.database.join_table.UserSport
import hu.bme.aut.android.sporttracker.databinding.ActivitySportTrackerBinding
import hu.bme.aut.android.sporttracker.sport.SportActivity
import java.io.Serializable
import kotlin.concurrent.thread

class SportTrackerActivity : AppCompatActivity(), SportTrackerUserAdapter.OnUserSelectedListener,
    SportTrackerAddUserDialogFragment.AddUserDialogListener {

    private lateinit var binding: ActivitySportTrackerBinding
    private lateinit var adapter: SportTrackerUserAdapter
    private lateinit var database: UserDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        database = UserDataBase.getDatabase(applicationContext)
        binding = ActivitySportTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFab()
        initRecyclerView()
    }

    private fun initFab() {
        binding.fab.setOnClickListener {
            SportTrackerAddUserDialogFragment().show(
                supportFragmentManager,
                SportTrackerAddUserDialogFragment::class.java.simpleName
            )
        }
    }

    private fun initRecyclerView() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SportTrackerUserAdapter(this)
        binding.mainRecyclerView.adapter = adapter
        loadItems()
    }

    override fun onUserSelected(user: UserSport?) {
        val showDetailsIntent = Intent()
        showDetailsIntent.setClass(this@SportTrackerActivity, SportActivity::class.java)
        showDetailsIntent.putExtra("UserSport", user as Serializable)
        startActivity(showDetailsIntent)

    }

    override fun onUserAdded(user: UserSport) {
        thread {
            val insertId = database.userDao().insertUser(user.user)
            user.user.id = insertId
            runOnUiThread {
                adapter.addUser(user)
            }
        }
    }

    private fun loadItems() {
        thread {
            val userSport = database.userDao().getAllUserSport()
            runOnUiThread {
                adapter.updateUser(userSport)
            }
        }
    }

    override fun onUserDeleted(user: UserSport) {
        thread {
            database.userDao().deleteUser(user.user)
            runOnUiThread {
                adapter.removeUser(user)
            }
        }
    }
}