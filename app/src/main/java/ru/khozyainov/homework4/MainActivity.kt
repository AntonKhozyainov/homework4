package ru.khozyainov.homework4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), AFragment.NavToFragmentBClickListener,
    BFragment.NavigateClickListener, CFragment.NavigateClickListener,
    DFragment.NavToFragmentBClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPopBackStackToFragmentBButtonClicked() {
        supportFragmentManager.popBackStack(
            BFragment.FRAGMENT_B_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onNavToFragmentAButtonClicked() {
        supportFragmentManager.popBackStack(
            AFragment.FRAGMENT_A_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onNavToFragmentDButtonClicked() {
        supportFragmentManager.commit {
            addToBackStack(CFragment.FRAGMENT_C_TAG)
            replace(R.id.fragmentContainerView, DFragment.newInstance())
        }
    }

    override fun onNavToBackButtonClicked() {
        supportFragmentManager.popBackStack(
            AFragment.FRAGMENT_A_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onNavToFragmentCButtonClicked(text: String) {
        supportFragmentManager.commit {
            addToBackStack(BFragment.FRAGMENT_B_TAG)
            replace(R.id.fragmentContainerView, CFragment.newInstance(text))
        }
    }

    override fun onNavToFragmentBButtonClicked() {
        supportFragmentManager.commit {
            addToBackStack(AFragment.FRAGMENT_A_TAG)
            replace(R.id.fragmentContainerView, BFragment.newInstance())
        }
    }

    companion object {

        @JvmStatic
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

}