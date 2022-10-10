package sk.ness.academy.springboothibernate.dao.impl;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import sk.ness.academy.config.TestDataSourceConfig;
import sk.ness.academy.springboothibernate.dao.BookDao;
import sk.ness.academy.springboothibernate.model.Book;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = { TestDataSourceConfig.class, BookDaoImpl.class })
@Transactional
@Sql({"/initdb.sql"})
class BookDaoImplTest {

  @Autowired
  private BookDao bookDao;

  @BeforeEach
  public void beforeEach() {
    System.out.println("### BeforeEach ###");
  }

  @Test
  void findAllTest() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertEquals(3, books.size());
  }

  @Test
  void testFalseSize() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertNotEquals(5, books.size());
  }

  @Test
  void testId() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertEquals(3, books.size());
    Assertions.assertEquals(1L, books.get(0).getId());

  }
  @Test
  void testIdOfTwoBooks() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertEquals(3, books.size());
    Assertions.assertEquals(2L, books.get(1).getId());
    Assertions.assertEquals(1L, books.get(0).getId());

  }

  @Test
  void testFalseId() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertEquals(3, books.size());
    Assertions.assertNotEquals(7L, books.get(1).getId());
    Assertions.assertNotEquals(10L, books.get(0).getId());

  }

  @Test
  void testFalseName() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertEquals(3, books.size());
    Assertions.assertNotEquals("Book10", books.get(1).getName());
    Assertions.assertNotEquals("Book7", books.get(0).getName());

  }

  @Test
  void TestChangeName() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertEquals(3, books.size());
    books.get(0).setName("Book6");
    Assertions.assertEquals("Book6", books.get(0).getName());

  }


}