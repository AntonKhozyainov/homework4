package ru.khozyainov.homework4

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import ru.khozyainov.homework4.UserDetailFragment.Companion.FRAGMENT_RESULT_EXTRA
import ru.khozyainov.homework4.UserDetailFragment.Companion.USER_EXTRA
import ru.khozyainov.homework4.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {

    private val usersListManager: UsersListManager
        get() = (activity?.application as App).userListManager

    private val userListener: (user: List<User>) -> Unit = { userAdapter.items = it }

    private lateinit var binding: FragmentUserListBinding

    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        setListeners()
    }

    override fun onDestroy() {
        usersListManager.removeListener(userListener)
        super.onDestroy()
    }


    private fun setListeners() {
        usersListManager.addListener(userListener)

        setFragmentResultListener(FRAGMENT_RESULT_EXTRA) { _, bundle ->

            val userExtra =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    bundle.getParcelable(USER_EXTRA, User::class.java)
                else
                    bundle.getParcelable(USER_EXTRA)

            if (userExtra != null) usersListManager.updateUser(userExtra)

        }
    }

    private fun initList() {
        userAdapter = UserAdapter { user ->
            (requireContext() as Navigator).navigateToDetailUserFragment(user)
        }

        with(binding.usersRecyclerView) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        }

    }

    companion object {
        const val USER_LIST_FRAGMENT_TAG = "USER_LIST_FRAGMENT_TAG"
    }
}