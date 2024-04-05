package hu.bme.aut.android.sporttracker.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.sporttracker.R
import hu.bme.aut.android.sporttracker.database.join_table.UserSport
import hu.bme.aut.android.sporttracker.databinding.ActivityItemUserBinding


class SportTrackerUserAdapter(private val listener: OnUserSelectedListener) :
    RecyclerView.Adapter<SportTrackerUserAdapter.UserViewHolder>() {
    private val users: MutableList<UserSport> = ArrayList()

    interface OnUserSelectedListener {
        fun onUserSelected(user: UserSport?)
        fun onUserDeleted(user: UserSport)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = users[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = users.size

    fun addUser(newUser: UserSport) {
        users.add(newUser)
        notifyItemInserted(users.size - 1)
    }

    fun removeUser(removableUser: UserSport) {
        users.remove(removableUser)
        notifyDataSetChanged()
    }

    fun updateUser(user: List<UserSport>) {
        users.clear()
        users.addAll(user)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding = ActivityItemUserBinding.bind(itemView)
        private var item: UserSport? = null

        init {
            binding.root.setOnClickListener { listener.onUserSelected(item) }
        }

        fun bind(newUser: UserSport?) {
            item = newUser
            binding.userItemNameTextView.text = item!!.user.name
            binding.userItemAgeTextView.text = item!!.user.age.toString()
            binding.userItemWeightTextView.text = item!!.user.mass.toString()
            binding.userItemHeightTextView.text = item!!.user.height.toString()
            binding.userItemSexTextView.text = when (item!!.user.sex) {
                0 -> "M"
                1 -> "F"
                2 -> "O"
                else -> "O"
            }

            binding.userItemRemoveButton.setOnClickListener {
                listener.onUserDeleted(item!!)
            }
        }
    }
}