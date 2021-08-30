package com.debaters.debateOnServer.domains.review.dao

import com.debaters.debateOnServer.domains.review.domain.Review
import org.springframework.data.mongodb.repository.MongoRepository

interface ReviewRepository : MongoRepository<Review, String>