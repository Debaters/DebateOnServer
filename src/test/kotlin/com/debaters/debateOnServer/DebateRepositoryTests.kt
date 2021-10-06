package com.debaters.debateOnServer

import com.debaters.debateOnServer.repositories.DebatesRepository
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ContextConfiguration
class DebateRepositoryTests {

    @Autowired
    lateinit var repository: DebatesRepository

    @Test
    fun readFirstPage() {
        val debates = repository.findAll(PageRequest.of(0, 10))
        assert(debates.isFirst)
    }
}