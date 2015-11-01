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

import com.cd.book.dataOnDemand.BookReviewDataOnDemand;
import com.cd.book.entity.BookReview;
import com.cd.book.repository.BookReviewRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class BookReviewIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    BookReviewDataOnDemand dod;

	@Autowired
    BookReviewRepository bookReviewRepository;

	@Test
    public void testCount() {
        Assert.assertNotNull("Data on demand for 'BookReview' failed to initialize correctly", dod.getRandomBookReview());
        long count = bookReviewRepository.count();
        Assert.assertTrue("Counter for 'BookReview' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFind() {
        BookReview obj = dod.getRandomBookReview();
        Assert.assertNotNull("Data on demand for 'BookReview' failed to initialize correctly", obj);
        Integer id = obj.getBookReviewId();
        Assert.assertNotNull("Data on demand for 'BookReview' failed to provide an identifier", id);
        obj = bookReviewRepository.findOne(id);
        Assert.assertNotNull("Find method for 'BookReview' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'BookReview' returned the incorrect identifier", id, obj.getBookReviewId());
    }

	@Test
    public void testFindAll() {
        Assert.assertNotNull("Data on demand for 'BookReview' failed to initialize correctly", dod.getRandomBookReview());
        long count = bookReviewRepository.count();
        Assert.assertTrue("Too expensive to perform a find all test for 'BookReview', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<BookReview> result = bookReviewRepository.findAll();
        Assert.assertNotNull("Find all method for 'BookReview' illegally returned null", result);
        Assert.assertTrue("Find all method for 'BookReview' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEntries() {
        Assert.assertNotNull("Data on demand for 'BookReview' failed to initialize correctly", dod.getRandomBookReview());
        long count = bookReviewRepository.count();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<BookReview> result = bookReviewRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
        Assert.assertNotNull("Find entries method for 'BookReview' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'BookReview' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testSave() {
        Assert.assertNotNull("Data on demand for 'BookReview' failed to initialize correctly", dod.getRandomBookReview());
        BookReview obj = dod.getNewTransientBookReview(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'BookReview' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'BookReview' identifier to be null", obj.getBookReviewId());
        try {
            bookReviewRepository.save(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        bookReviewRepository.flush();
        Assert.assertNotNull("Expected 'BookReview' identifier to no longer be null", obj.getBookReviewId());
    }

	@Test
    public void testDelete() {
        BookReview obj = dod.getRandomBookReview();
        Assert.assertNotNull("Data on demand for 'BookReview' failed to initialize correctly", obj);
        Integer id = obj.getBookReviewId();
        Assert.assertNotNull("Data on demand for 'BookReview' failed to provide an identifier", id);
        obj = bookReviewRepository.findOne(id);
        bookReviewRepository.delete(obj);
        bookReviewRepository.flush();
        Assert.assertNull("Failed to remove 'BookReview' with identifier '" + id + "'", bookReviewRepository.findOne(id));
    }
}
