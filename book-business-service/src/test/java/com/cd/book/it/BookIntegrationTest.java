package com.cd.book.it;
import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cd.book.dataOnDemand.BookDataOnDemand;
import com.cd.book.entity.Book;
import com.cd.book.repository.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class BookIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    BookDataOnDemand dod;

	@Autowired
    BookRepository bookRepository;

	@Test
    public void testCount() {
        Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", dod.getRandomBook());
        long count = bookRepository.count();
        Assert.assertTrue("Counter for 'Book' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFind() {
        Book obj = dod.getRandomBook();
        Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", obj);
        Integer id = obj.getBookId();
        Assert.assertNotNull("Data on demand for 'Book' failed to provide an identifier", id);
        obj = bookRepository.findOne(id);
        Assert.assertNotNull("Find method for 'Book' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Book' returned the incorrect identifier", id, obj.getBookId());
    }

	@Test
    public void testFindAll() {
        Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", dod.getRandomBook());
        long count = bookRepository.count();
        Assert.assertTrue("Too expensive to perform a find all test for 'Book', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Book> result = bookRepository.findAll();
        Assert.assertNotNull("Find all method for 'Book' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Book' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEntries() {
        Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", dod.getRandomBook());
        long count = bookRepository.count();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Book> result = bookRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
        Assert.assertNotNull("Find entries method for 'Book' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Book' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testSave() {
        Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", dod.getRandomBook());
        Book obj = dod.getNewTransientBook(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Book' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Book' identifier to be null", obj.getBookId());
        try {
            bookRepository.save(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        bookRepository.flush();
        Assert.assertNotNull("Expected 'Book' identifier to no longer be null", obj.getBookId());
    }

	@Test
    public void testDelete() {
        Book obj = dod.getRandomBook();
        Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", obj);
        Integer id = obj.getBookId();
        Assert.assertNotNull("Data on demand for 'Book' failed to provide an identifier", id);
        obj = bookRepository.findOne(id);
        bookRepository.delete(obj);
        bookRepository.flush();
        Assert.assertNull("Failed to remove 'Book' with identifier '" + id + "'", bookRepository.findOne(id));
    }
}