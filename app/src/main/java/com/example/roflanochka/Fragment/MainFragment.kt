package com.example.roflanochka.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roflanochka.Model.UserModel
import com.example.roflanochka.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.recycler_view_item.*

class MainFragment : Fragment() {

    private lateinit var userAdapter: UserAdapter

    var listOfUsers: ArrayList<UserModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOfUsers.add(UserModel(name = "Roman", score = 1, avatar = "https://assets.jpegmini.com/user/images/slider_puffin_jpegmini.jpg"))
        listOfUsers.add(UserModel(name = "Ochka", score = 0, avatar = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1601&q=80"))

        userAdapter = UserAdapter(this.context!!, listOfUsers)

        userList.adapter = userAdapter
        userList.layoutManager = LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)

        addNewUserButton.setOnClickListener {
            listOfUsers.add(UserModel(name = "New User: ${listOfUsers.size - 2}", score = 0, avatar = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1601&q=80"))

            userAdapter.notifyDataSetChanged()
        }
    }

    class UserAdapter(var context: Context,
                      val listOfUsers: ArrayList<UserModel>): RecyclerView.Adapter<UserAdapter.UserItem>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItem {
            val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false)

            return UserItem(view)
        }

        override fun getItemCount(): Int {
            return listOfUsers.size
        }

        override fun onBindViewHolder(holder: UserItem, position: Int) {
            val user = listOfUsers[position]

            Glide.with(context).load(user.avatar).into(holder.avatar);

            holder.name.text = user.name
            holder.score.text = "Score: ${user.score}"
        }

        class UserItem(view: View): RecyclerView.ViewHolder(view) {

            var avatar: ImageView
            var name: TextView
            var score: TextView

            init {
                avatar = view.findViewById(R.id.avatar)
                name = view.findViewById(R.id.name)
                score = view.findViewById(R.id.score)
            }

        }

    }

}