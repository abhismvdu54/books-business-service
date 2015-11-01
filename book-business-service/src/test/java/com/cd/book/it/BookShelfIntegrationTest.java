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

import com.cd.book.dataOnDemand.BookShelfDataOnDemand;
import com.cd.book.entity.BookShelf;
import com.cd.book.repository.BookShelfRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class BookShelfIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    BookShelfDataOnDemand dod;

	@Autowired
    BookShelfRepository bookShelfRepository;

	@Test
    public void testCount() {
        Assert.assertNotNull("Data on demand for 'BookShelf' failed to initialize correctly", dod.getRandomBookShelf());
        long count = bookShelfRepository.count();
        Assert.assertTrue("Counter for 'BookShelf' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFind() {
        BookShelf obj = dod.getRandomBookShelf();
        Assert.assertNotNull("Data on demand for 'BookShelf' failed to initialize correctly", obj);
        Integer id = obj.getBookShelfId();
        Assert.assertNotNull("Data on demand for 'BookShelf' failed to provide an identifier", id);
        obj = bookShelfRepository.findOne(id);
        Assert.assertNotNull("Find method for 'BookShelf' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'BookShelf' returned the incorrect identifier", id, obj.getBookShelfId());
    }

	@Test
    public void testFindAll() {
        Assert.assertNotNull("Data on demand for 'BookShelf' failed to initialize correctly", dod.getRandomBookShelf());
        long count = bookShelfRepository.count();
        Assert.assertTrue("Too expensive to perform a find all test for 'BookShelf', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<BookShelf> result = bookShelfRepository.findAll();
        Assert.assertNotNull("Find all method for 'BookShelf' illegally returned null", result);
        Assert.assertTrue("Find all method for 'BookShelf' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEntries() {
        Assert.assertNotNull("Data on demand for 'BookShelf' failed to initialize correctly", dod.getRandomBookShelf());
        long count = bookShelfRepository.count();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<BookShelf> result = bookShelfRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
        Assert.assertNotNull("Find entries method for 'BookShelf' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'BookShelf' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testSave() {
        Assert.assertNotNull("Data on demand for 'BookShelf' failed to initialize correctly", dod.getRandomBookShelf());
        BookShelf obj = dod.getNewTransientBookShelf(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'BookShelf' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'BookShelf' identifier to be null", obj.getBookShelfId());
        try {
            bookShelfRepository.save(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        bookShelfRepository.flush();
        Assert.assertNotNull("Expected 'BookShelf' identifier to no longer be null", obj.getBookShelfId());
    }

	@Test
    public void testDelete() {
        BookShelf obj = dod.getRandomBookShelf();
        Assert.assertNotNull("Data on demand for 'BookShelf' failed to initialize correctly", obj);
        Integer id = obj.getBookShelfId();
        Assert.assertNotNull("Data on demand for 'BookShelf' failed to provide an identifier", id);
        obj = bookShelfRepository.findOne(id);
        bookShelfRepository.delete(obj);
        bookShelfRepository.flush();
        Assert.assertNull("Failed to remove 'BookShelf' with identifier '" + id + "'", bookShelfRepository.findOne(id));
    }
}
