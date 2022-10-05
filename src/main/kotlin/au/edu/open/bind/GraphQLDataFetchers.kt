package au.edu.open.bind

import au.edu.open.model.Author
import au.edu.open.model.Book
import au.edu.open.repository.DbRepository
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import io.micronaut.http.annotation.Controller
import jakarta.inject.Singleton

@Singleton
class GraphQLDataFetchers(private val dbRepository: DbRepository) {

    fun bookByIdDataFetcher(): DataFetcher<Book> {
        return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val bookId: String = dataFetchingEnvironment.getArgument("id")
            dbRepository.findAllBooks()
                .firstOrNull { book: Book -> (book.id == bookId) }
        }
    }

    fun authorDataFetcher(): DataFetcher<Author> {
        return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val book: Book = dataFetchingEnvironment.getSource()
            val authorBook: Author = book.author
            dbRepository.findAllAuthors()
                .firstOrNull {author: Author -> (author.id == authorBook.id) }
        }
    }

}