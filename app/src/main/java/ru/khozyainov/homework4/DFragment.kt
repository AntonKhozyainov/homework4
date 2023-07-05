package ru.khozyainov.homework4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.khozyainov.homework4.databinding.FragmentDBinding
import java.lang.RuntimeException

class DFragment : Fragment() {

    private lateinit var binding: FragmentDBinding

    private lateinit var navigateClickListener: NavToFragmentBClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is NavToFragmentBClickListener)
            navigateClickListener = (requireActivity() as NavToFragmentBClickListener)
        else
            throw RuntimeException("Context is not NavigateClickListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentDNavToFragmentBButton.setOnClickListener {
            navigateClickListener.onPopBackStackToFragmentBButtonClicked()
        }
    }

    interface NavToFragmentBClickListener{
        fun onPopBackStackToFragmentBButtonClicked()
    }

    companion object {

        const val FRAGMENT_D_TAG = "FRAGMENT_D_TAG"

        @JvmStatic
        fun newInstance() = DFragment()
    }
}