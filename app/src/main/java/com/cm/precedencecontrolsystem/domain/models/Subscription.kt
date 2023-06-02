package com.cm.precedencecontrolsystem.domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Subscription
@Ignore constructor(
    var studentId: Int? = null,
    var subjectId: Int? = null,
    var entity: Int = 0,
    var reference: Long = 0,
    var amount: Double = 0.00,
    var date: String = "",
    @PrimaryKey var id: Int? = null
) {
    constructor(): this(null)
}
