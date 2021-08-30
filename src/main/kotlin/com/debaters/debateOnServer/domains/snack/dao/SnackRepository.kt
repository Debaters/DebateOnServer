package com.debaters.debateOnServer.domains.snack.dao

import com.debaters.debateOnServer.domains.snack.domain.Snack
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SnackRepository : MongoRepository<Snack, String>