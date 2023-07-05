package ru.khozyainov.homework4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.khozyainov.homework4.databinding.FragmentCBinding
import java.lang.RuntimeException

class CFragment: Fragment() {

    private lateinit var binding: FragmentCBinding

    private lateinit var navigateClickListener: NavigateClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is NavigateClickListener)
            navigateClickListener = (requireActivity() as NavigateClickListener)
        else
            throw RuntimeException("Context is not NavigateClickListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            fragmentCTextView.text = requireArguments().getString(TEXT_RES_EXTRA)

            fragmentCNavToFragmentAButton.setOnClickListener {
                navigateClickListener.onNavToFragmentAButtonClicked()
            }

            fragmentCNavToFragmentDButton.setOnClickListener {
                navigateClickListener.onNavToFragmentDButtonClicked()
            }
        }
    }

    interface NavigateClickListener{
        fun onNavToFragmentAButtonClicked()
        fun onNavToFragmentDButtonClicked()
    }

    companion object {

        const val FRAGMENT_C_TAG = "FRAGMENT_C_TAG"

        private const val TEXT_RES_EXTRA = "ARG_TEXT"

        @JvmStatic
        fun newInstance(text: String) =
            CFragment().apply {
                arguments = bundleOf(TEXT_RES_EXTRA to text)
            }
    }
}