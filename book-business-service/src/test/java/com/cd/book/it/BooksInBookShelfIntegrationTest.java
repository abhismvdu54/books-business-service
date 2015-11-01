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

import com.cd.book.dataOnDemand.BooksInBookShelfDataOnDemand;
import com.cd.book.entity.BooksInBookShelf;
import com.cd.book.repository.BooksInBookShelfRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class BooksInBookShelfIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    BooksInBookShelfDataOnDemand dod;

	@Autowired
    BooksInBookShelfRepository booksInBookShelfRepository;

	@Test
    public void testCount() {
        Assert.assertNotNull("Data on demand for 'BooksInBookShelf' failed to initialize correctly", dod.getRandomBooksInBookShelf());
        long count = booksInBookShelfRepository.count();
        Assert.assertTrue("Counter for 'BooksInBookShelf' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFind() {
        BooksInBookShelf obj = dod.getRandomBooksInBookShelf();
        Assert.assertNotNull("Data on demand for 'BooksInBookShelf' failed to initialize correctly", obj);
        Integer id = obj.getBooksInBookShelfId();
        Assert.assertNotNull("Data on demand for 'BooksInBookShelf' failed to provide an identifier", id);
        obj = booksInBookShelfRepository.findOne(id);
        Assert.assertNotNull("Find method for 'BooksInBookShelf' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'BooksInBookShelf' returned the incorrect identifier", id, obj.getBooksInBookShelfId());
    }

	@Test
    public void testFindAll() {
        Assert.assertNotNull("Data on demand for 'BooksInBookShelf' failed to initialize correctly", dod.getRandomBooksInBookShelf());
        long count = booksInBookShelfRepository.count();
        Assert.assertTrue("Too expensive to perform a find all test for 'BooksInBookShelf', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<BooksInBookShelf> result = booksInBookShelfRepository.findAll();
        Assert.assertNotNull("Find all method for 'BooksInBookShelf' illegally returned null", result);
        Assert.assertTrue("Find all method for 'BooksInBookShelf' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEntries() {
        Assert.assertNotNull("Data on demand for 'BooksInBookShelf' failed to initialize correctly", dod.getRandomBooksInBookShelf());
        long count = booksInBookShelfRepository.count();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<BooksInBookShelf> result = booksInBookShelfRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
        Assert.assertNotNull("Find entries method for 'BooksInBookShelf' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'BooksInBookShelf' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testSave() {
        Assert.assertNotNull("Data on demand for 'BooksInBookShelf' failed to initialize correctly", dod.getRandomBooksInBookShelf());
        BooksInBookShelf obj = dod.getNewTransientBooksInBookShelf(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'BooksInBookShelf' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'BooksInBookShelf' identifier to be null", obj.getBooksInBookShelfId());
        try {
            booksInBookShelfRepository.save(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        booksInBookShelfRepository.flush();
        Assert.assertNotNull("Expected 'BooksInBookShelf' identifier to no longer be null", obj.getBooksInBookShelfId());
    }

	@Test
    public void testDelete() {
        BooksInBookShelf obj = dod.getRandomBooksInBookShelf();
        Assert.assertNotNull("Data on demand for 'BooksInBookShelf' failed to initialize correctly", obj);
        Integer id = obj.getBooksInBookShelfId();
        Assert.assertNotNull("Data on demand for 'BooksInBookShelf' failed to provide an identifier", id);
        obj = booksInBookShelfRepository.findOne(id);
        booksInBookShelfRepository.delete(obj);
        booksInBookShelfRepository.flush();
        Assert.assertNull("Failed to remove 'BooksInBookShelf' with identifier '" + id + "'", booksInBookShelfRepository.findOne(id));
    }
}
