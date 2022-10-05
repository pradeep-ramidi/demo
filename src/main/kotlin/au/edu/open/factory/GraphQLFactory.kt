package au.edu.open.factory

import au.edu.open.bind.GraphQLDataFetchers
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.*
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStreamReader

@Factory
class GraphQLFactory {

    @Bean
    @Singleton
    fun graphQL(resourceResolver: ResourceResolver, graphQLDataFetchers: GraphQLDataFetchers): GraphQL {
        val schemaParser = SchemaParser()

        val typeRegistry = TypeDefinitionRegistry()
        val graphqlSchema = resourceResolver.getResourceAsStream("classpath:book.graphqls")

        return if (graphqlSchema.isPresent) {
            typeRegistry.merge(schemaParser.parse(BufferedReader(InputStreamReader(graphqlSchema.get()))))
            val runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type(
                    TypeRuntimeWiring.newTypeWiring("Query")
                    .dataFetcher("bookById", graphQLDataFetchers.bookByIdDataFetcher()))
                .type(TypeRuntimeWiring.newTypeWiring("Book")
                    .dataFetcher("author", graphQLDataFetchers.authorDataFetcher()))
                .build()
            val schemaGenerator = SchemaGenerator()
            val graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
            GraphQL.newGraphQL(graphQLSchema).build()
        } else {
            LOG.debug("No GraphQL services found, returning empty schema")
            GraphQL.Builder(GraphQLSchema.newSchema().build()).build()
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(GraphQLFactory::class.java)
    }
}