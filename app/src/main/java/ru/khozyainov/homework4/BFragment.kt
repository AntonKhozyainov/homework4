package ru.khozyainov.homework4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.khozyainov.homework4.databinding.FragmentBBinding
import java.lang.RuntimeException

class BFragment : Fragment() {

    private lateinit var binding: FragmentBBinding

    private lateinit var navigateClickListener: NavigateClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is NavigateClickListener)
            navigateClickListener = (requireActivity() as NavigateClickListener)
        else
            throw RuntimeException("Context is not NavigateClickListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            fragmentBNavToFragmentCButton.setOnClickListener {
                val messageForFragmentC = getString(R.string.message_for_c)
                navigateClickListener.onNavToFragmentCButtonClicked(messageForFragmentC)
            }

            fragmentBBackButton.setOnClickListener {
                navigateClickListener.onNavToBackButtonClicked()
            }
        }
    }

    interface NavigateClickListener {
        fun onNavToFragmentCButtonClicked(text: String)
        fun onNavToBackButtonClicked()
    }

    companion object {

        const val FRAGMENT_B_TAG = "FRAGMENT_B_TAG"

        @JvmStatic
        fun newInstance() = BFragment()
    }
}