package com.debaters.debateOnServer.domains.snack.domain

import com.debaters.debateOnServer.domains.review.domain.Review
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "snack")
data class Snack (
    var name: String,
    var amount: Float
    ){
    @Id
    var id: String = ""

    @Transient
    var reviews: List<Review> = ArrayList()
}