package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.BooksDAO;
import io.khasang.archivarius.entity.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component("BooksService")
@Transactional
public class BooksService {
    @Autowired
    BooksDAO booksDAO;

    public void addBooks(Books books) {
        booksDAO.addBooks(books);
    }

    public Books getBooksById(int id) {
        return booksDAO.getBooksById(id);
    }

    public List<Books> getBooksList() {
        return booksDAO.getBooksList();
    }

    public void updateBooks(Books books) {
        booksDAO.updateBooks(books);
    }

    public void deleteBooks(Books books) {
        booksDAO.deleteBooks(books);
    }
}
