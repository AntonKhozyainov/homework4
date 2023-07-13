package ru.khozyainov.homework4

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.khozyainov.homework4.databinding.ItemUserBinding

class UserAdapter(
    onItemClicked: (user: User) -> Unit
) : AsyncListDifferDelegationAdapter<User>(UserDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(getUserAdapterDelegate(onItemClicked = onItemClicked))
    }

    private fun getUserAdapterDelegate(onItemClicked: (user: User) -> Unit) =
        adapterDelegateViewBinding<User, User, ItemUserBinding>(viewBinding = { layoutInflater, root ->
            ItemUserBinding.inflate(layoutInflater, root, false)
        }) {

            binding.root.setOnClickListener {
                onItemClicked(item)
            }

            bind {
                with(binding) {
                    userItemIdTextView.text = getString(R.string.item_id, item.id)
                    userItemPhoneNumberTextView.text = item.phoneNumber
                    userItemFirstNameTextView.text = item.firstName
                    userItemLastNameTextView.text = item.lastName

                    itemView.context.setImageWithGlide(userItemImageView, item.photoUrl)
                }
            }
        }

    class UserDiffUtilCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
    }
}