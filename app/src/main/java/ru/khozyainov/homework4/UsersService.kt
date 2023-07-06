package ru.khozyainov.homework4

import kotlin.random.Random

class UsersService {

    private var users = mutableListOf(
        User(
            id = Random.nextLong(),
            firstName = "Иван",
            lastName = "Иванов",
            phoneNumber = "8(800)700-60-50",
            photoUrl = "https://image.shutterstock.com/shutterstock/photos/571179172/display_1500/stock-vector-young-guy-curly-hair-pink-t-shirt-571179172.jpg"
        ),
        User(
            id = Random.nextLong(),
            firstName = "Петр",
            lastName = "Петров",
            phoneNumber = "8(900)800-64-45",
            photoUrl = "https://cdn4.vectorstock.com/i/1000x1000/47/78/happy-boy-with-shirt-and-pant-clothes-vector-21954778.jpg"
        ),
        User(
            id = Random.nextLong(),
            firstName = "Василий",
            lastName = "Сидоров",
            phoneNumber = "8(860)220-10-50",
            photoUrl = "https://us.123rf.com/450wm/yupiramos/yupiramos1910/yupiramos191013758/132737063-little-boy-avatar-character-vector-illustration-design.jpg?ver=6"
        ),
        User(
            id = Random.nextLong(),
            firstName = "Владимир",
            lastName = "Владимиров",
            phoneNumber = "8(400)799-20-10",
            photoUrl = "https://www.clipartmax.com/png/middle/401-4018529_man-crossing-his-arms-man-crossing-his-arms.png"
        )
    )

    private val listeners = mutableSetOf<(users: List<User>) -> Unit>()

    fun updateUser(user: User) {
        val indexToReplace = users.indexOfFirst { it.id == user.id }
        if (indexToReplace != -1) {
            users = ArrayList(users)
            users[indexToReplace] = user
            notifyChanges()
        }
    }

    fun addListener(listener: (userList: List<User>) -> Unit) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: (userList: List<User>) -> Unit) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }
    }

}