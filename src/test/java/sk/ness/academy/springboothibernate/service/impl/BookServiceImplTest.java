package sk.ness.academy.springboothibernate.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import sk.ness.academy.springboothibernate.dao.BookDao;
import sk.ness.academy.springboothibernate.dto.BookDto;
import sk.ness.academy.springboothibernate.model.Book;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class BookServiceImplTest {

  @Mock
  private BookDao bookDao;

  @InjectMocks
  private BookServiceImpl bookService;

  private List<Book> books;

  @Test
  void testFindAll() {
    Mockito.when(bookDao.findAll()).thenReturn(books);

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertEquals(2, bookDtos.size());
    Assertions.assertEquals("X_Book_1", bookDtos.get(0).getName());
    Assertions.assertEquals("X_Book_2", bookDtos.get(1).getName());
  }

  @Test
  void testFindAllEmpty() {
    Mockito.when(bookDao.findAll()).thenReturn(new ArrayList<>());

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertEquals(0, bookDtos.size());
  }


  @Test
  void testFindAllNull() {
    Mockito.when(bookDao.findAll()).thenReturn(null);

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertTrue(bookDtos.isEmpty());
  }



  @BeforeEach
  private void init() {
    final Book book1 = new Book();
    book1.setName("Book_1");
    book1.setId(1L);

    final Book book2 = new Book();
    book2.setName("Book_2");
    book2.setId(2L);

    books = new ArrayList<>();
    books.add(book1);
    books.add(book2);
  }


  @Test
  void testCheckNameLength() {
    Mockito.when(bookDao.findAll()).thenReturn(books);
    final List<BookDto> bookDtos = bookService.findAll();
    Assertions.assertTrue(bookDtos.get(0).getName().length()==8);
  }

  @Test
  void testCheckNameLengthAnotherBook() {
    Mockito.when(bookDao.findAll()).thenReturn(books);
    final List<BookDto> bookDtos = bookService.findAll();
    Assertions.assertEquals(8,bookDtos.get(1).getName().length());
  }

  @Test
  void testBookLength() {
    Mockito.when(bookDao.findAll()).thenReturn(books);

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertFalse(bookDtos.size()==10);
  }

  @Test
  void testFalseLength() {
    Mockito.when(bookDao.findAll()).thenReturn(books);

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertFalse(bookDtos.size()==20);
  }

  @Test
  void testTrueLength() {
    Mockito.when(bookDao.findAll()).thenReturn(books);

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertEquals(2,bookDtos.size());
  }

  @Test
  void testFindAllByNames() {
    Mockito.when(bookDao.findAll()).thenReturn(books);

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertEquals(2, bookDtos.size());
    Assertions.assertNotEquals("Book_1", bookDtos.get(0).getName());
    Assertions.assertNotEquals("Book_2", bookDtos.get(1).getName());
  }

  @Test
  void testFindAllByFalseNames() {
    Mockito.when(bookDao.findAll()).thenReturn(books);

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertEquals(2, bookDtos.size());
    Assertions.assertNotEquals("Book1", bookDtos.get(0).getName());
    Assertions.assertNotEquals("Book2", bookDtos.get(1).getName());
  }





}