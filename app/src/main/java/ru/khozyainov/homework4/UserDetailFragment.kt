package ru.khozyainov.homework4

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import ru.khozyainov.homework4.databinding.FragmentUserDetailBinding
import java.lang.Exception

class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding

    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        getUserFromArguments()
        setFieldsValue()
    }

    private fun setListeners() {
        with(binding) {
            saveButton.setOnClickListener {
                updateUser()
                setFragmentResult(FRAGMENT_RESULT_EXTRA, bundleOf(USER_EXTRA to user))
                (requireContext() as Navigator).navigateToPopBackStack()
            }

            userInformationImageUrlEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    requireContext().setImageWithGlide(userInformationImageView, s.toString())
                }
            })
        }
    }

    private fun updateUser() {
        with(binding) {
            user = user.copy(
                firstName = userInformationFirstNameEditText.text.toString(),
                lastName = userInformationLastNameEditText.text.toString(),
                phoneNumber = userInformationPhoneNumberEditText.text.toString(),
                photoUrl = userInformationImageUrlEditText.text.toString()
            )
        }
    }

    private fun setFieldsValue() {
        with(binding) {
            userInformationImageUrlEditText.setText(user.photoUrl)
            userInformationPhoneNumberEditText.setText(user.phoneNumber)
            userInformationFirstNameEditText.setText(user.firstName)
            userInformationLastNameEditText.setText(user.lastName)

            requireContext().setImageWithGlide(userInformationImageView, user.photoUrl)
        }
    }


    private fun getUserFromArguments() {
        val userExtra =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                requireArguments().getParcelable(USER_EXTRA, User::class.java)
            else
                requireArguments().getParcelable(USER_EXTRA)

        user = userExtra ?: throw Exception("There is no value on the key = $USER_EXTRA")
    }

    companion object {

        const val FRAGMENT_RESULT_EXTRA = "FRAGMENT_RESULT_EXTRA"
        const val USER_EXTRA = "USER_EXTRA"

        @JvmStatic
        fun newInstant(user: User): UserDetailFragment {
            val userDetailFragment = UserDetailFragment()
            userDetailFragment.arguments = bundleOf(USER_EXTRA to user)
            return userDetailFragment
        }
    }
}