package com.example.shadidemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.shadidemo.R
import com.example.shadidemo.adapter.UserAdapter
import com.example.shadidemo.database.entity.UserEntity
import com.example.shadidemo.utils.loadProgressBar
import com.example.shadidemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.user_fragment.*

class UserFragment : Fragment(), UserAdapter.RecyclerViewClickListener {

    private lateinit var viewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    companion object {
        val STATUS_DECLINED: Int = 1
        val STATUS_ACCEPTED: Int = 2
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpAdapter()
        viewModel = ViewModelProviders.of(this@UserFragment).get(UserViewModel::class.java)
        viewModel.apply {
            isLoading().observe(viewLifecycleOwner, Observer { isLoading -> loadProgressBar(isLoading, progressBar, userRecyclerView) })
            displayUsers()?.observe(viewLifecycleOwner, Observer {
                userAdapter.updateUserList(it)
            })
        }
    }

    private fun setUpAdapter() {
        userRecyclerView.apply {
            userAdapter = UserAdapter(arrayListOf(), this@UserFragment)
            adapter = userAdapter
        }
    }

    override fun onRecyclerViewItemClick(view: View, userEntity: UserEntity) {
        when (view.id) {
            R.id.declineIcon  -> {
                viewModel.updateStatus(STATUS_DECLINED, userEntity)
            }
            R.id.acceptIcon -> {
                viewModel.updateStatus(STATUS_ACCEPTED, userEntity)
            }
        }
    }
}