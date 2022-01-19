package com.debaters.debateOnServer.service

import com.debaters.debateOnServer.models.Debate
import com.debaters.debateOnServer.repositories.DebatesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class DebateService {

    @Autowired
    private lateinit var repository: DebatesRepository

    /**
     * 현재 mock 데이터를 리턴함.
     */
    @ExperimentalStdlibApi
    suspend fun getDebates(offset: Int = 0, size: Int = 10): List<Debate> {
        return repository.findAll(PageRequest.of(offset, size)).toList()
    }

    @ExperimentalStdlibApi
    suspend fun createDebate(
            title: String,
            description: String,
            creatorName: String,
    ): Boolean {
        val debate = Debate(
                title = title,
                description = description,
                creatorName = creatorName,
                creatorId = "abc",
        )

        return try {
            repository.save(debate)
            true
        } catch (ex: Exception) {

            false
        }
    }

    @ExperimentalStdlibApi
    suspend fun findDebate(id: String): Debate? {
        return repository.findById(id).get()
    }
}