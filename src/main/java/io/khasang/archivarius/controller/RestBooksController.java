package io.khasang.archivarius.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.khasang.archivarius.entity.Books;
import io.khasang.archivarius.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/books")
public class RestBooksController {
    @Autowired
    BooksService booksService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; encoding=UTF-8")
    @ResponseBody
    public Object getQuestion(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            int questionId = Integer.valueOf(inputId);
            Books books = booksService.getBooksById(questionId);
            if (books != null) {
                return books;
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "Book with id: " + questionId + " not found.";
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Bad book id format: " + inputId;
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Books> getAllQuestion() {
        return booksService.getBooksList();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addQuestion(@RequestBody Books books, HttpServletResponse response) {
        try {
            booksService.addBooks(books);

            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(response.toString());

            while (!parser.isClosed()) {
                JsonToken jsonToken = parser.nextToken();

                System.out.println("jsonToken = " + jsonToken);
            }

            return books;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error adding question: " + e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Object updateQuestion(@RequestBody Books books, HttpServletResponse response) {
        try {
            booksService.updateBooks(books);
            return books;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error updating user: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteQuestion(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            int booksId = Integer.valueOf(inputId);
            Books books = booksService.getBooksById(booksId);
            if (books != null) {
                booksService.deleteBooks(books);
                return "Book #" + booksId + " successfully deleted.";
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "Book with id: " + booksId + " not found.";
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Bad book id format: " + inputId;
        }
    }
}
