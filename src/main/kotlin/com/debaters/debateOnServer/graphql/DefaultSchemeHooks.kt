package com.debaters.debateOnServer.graphql

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLType
import java.time.OffsetDateTime
import kotlin.reflect.KType
import kotlin.reflect.KClass

class DefaultSchemeHooks : SchemaGeneratorHooks {
    override fun willGenerateGraphQLType(type: KType): GraphQLType? {
        return when (type.classifier as? KClass<*>) {
            OffsetDateTime::class -> ExtendedScalars.DateTime
            else -> null
        }
    }
}