package com.debaters.debateOnServer.repositories

import com.debaters.debateOnServer.models.Debate
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Component
import java.util.*

/**
 * 토론 목록을 조회 가능한 repository
 */
@Component
interface DebatesRepository : PagingAndSortingRepository<Debate, String>