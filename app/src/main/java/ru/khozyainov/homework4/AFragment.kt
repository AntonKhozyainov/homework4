package ru.khozyainov.homework4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.khozyainov.homework4.databinding.FragmentABinding
import java.lang.RuntimeException

class AFragment : Fragment() {

    private lateinit var binding: FragmentABinding

    private lateinit var navigateClickListener: NavToFragmentBClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BFragment.NavigateClickListener)
            navigateClickListener = (requireActivity() as NavToFragmentBClickListener)
        else
            throw RuntimeException("Context is not NavigateClickListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentANavToFragmentBButton.setOnClickListener {
            navigateClickListener.onNavToFragmentBButtonClicked()
        }
    }

    interface NavToFragmentBClickListener {
        fun onNavToFragmentBButtonClicked()
    }

    companion object {
        const val FRAGMENT_A_TAG = "FRAGMENT_A_TAG"
    }
}