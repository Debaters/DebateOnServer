package com.debaters.debateOnServer.domains.review.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "reviews")
class Review(
    var snackId: String,
    var rating: Int,
    var text: String
)