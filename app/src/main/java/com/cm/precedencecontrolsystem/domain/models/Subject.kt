package com.cm.precedencecontrolsystem.domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Subject
@Ignore constructor(
    var name: String = "",
    var credits: Int = 0,
    @PrimaryKey var id: Int? = null
) {
    constructor(): this("")
}
