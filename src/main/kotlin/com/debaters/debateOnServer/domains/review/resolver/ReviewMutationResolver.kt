package com.debaters.debateOnServer.domains.review.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.debaters.debateOnServer.domains.review.dao.ReviewRepository
import com.debaters.debateOnServer.domains.review.domain.Review
import org.springframework.stereotype.Component

@Component
class ReviewMutationResolver (private val reviewRepository: ReviewRepository): GraphQLMutationResolver {
    fun newReview(snackId: String, rating: Int, text:String): Review {
        val review = Review(snackId, rating, text)
        reviewRepository.save(review)
        return review
    }
}