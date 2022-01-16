package com.debaters.debateOnServer.mongodb

import OffsetDateTimeReadConverter
import OffsetDateTimeWriteConverter
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@Configuration
@EnableMongoRepositories(basePackages = ["com.debaters.debateOnServer.repositories"])
class MongoClientConfiguration : AbstractMongoClientConfiguration() {

    @Value("\${spring.data.mongodb.uri}")
    lateinit var mongoUri: String

    override fun getDatabaseName(): String {
        return "debates"
    }

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(mongoUri)
        val mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build()

        return MongoClients.create(mongoClientSettings)
    }

    override fun customConversions(): MongoCustomConversions {
        val converters = listOf(
                OffsetDateTimeReadConverter(),
                OffsetDateTimeWriteConverter(),
        )

        return MongoCustomConversions(converters)
    }
}

