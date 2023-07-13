package ru.khozyainov.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import ru.khozyainov.homework4.UserListFragment.Companion.USER_LIST_FRAGMENT_TAG

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToDetailUserFragment(user: User) {
        supportFragmentManager.commit {
            addToBackStack(USER_LIST_FRAGMENT_TAG)
            replace(R.id.fragmentContainerView, UserDetailFragment.newInstant(user))
        }
    }

    override fun navigateToPopBackStack() {
        supportFragmentManager.popBackStack(
            USER_LIST_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }
}