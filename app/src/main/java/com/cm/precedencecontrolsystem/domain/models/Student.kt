package com.cm.precedencecontrolsystem.domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Student
@Ignore constructor(
    var name: String = "",
    var gender: String = "",
    var nationality: String = "",
    var phoneNumber: String = "",
    var email: String = "",
    var studentType: String = "",
    var level: Int = 1,
    var tail: Boolean = false,
    @PrimaryKey var id: Int? = null
) {
    constructor() : this(
        ""
    )
}