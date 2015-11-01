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

import com.cd.book.dataOnDemand.BookRatingInfoDataOnDemand;
import com.cd.book.entity.BookRatingInfo;
import com.cd.book.repository.BookRatingInfoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class BookRatingInfoIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    BookRatingInfoDataOnDemand dod;

	@Autowired
    BookRatingInfoRepository bookRatingInfoRepository;

	@Test
    public void testCount() {
        Assert.assertNotNull("Data on demand for 'BookRatingInfo' failed to initialize correctly", dod.getRandomBookRatingInfo());
        long count = bookRatingInfoRepository.count();
        Assert.assertTrue("Counter for 'BookRatingInfo' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFind() {
        BookRatingInfo obj = dod.getRandomBookRatingInfo();
        Assert.assertNotNull("Data on demand for 'BookRatingInfo' failed to initialize correctly", obj);
        Integer id = obj.getBookRatingInfoId();
        Assert.assertNotNull("Data on demand for 'BookRatingInfo' failed to provide an identifier", id);
        obj = bookRatingInfoRepository.findOne(id);
        Assert.assertNotNull("Find method for 'BookRatingInfo' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'BookRatingInfo' returned the incorrect identifier", id, obj.getBookRatingInfoId());
    }

	@Test
    public void testFindAll() {
        Assert.assertNotNull("Data on demand for 'BookRatingInfo' failed to initialize correctly", dod.getRandomBookRatingInfo());
        long count = bookRatingInfoRepository.count();
        Assert.assertTrue("Too expensive to perform a find all test for 'BookRatingInfo', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<BookRatingInfo> result = bookRatingInfoRepository.findAll();
        Assert.assertNotNull("Find all method for 'BookRatingInfo' illegally returned null", result);
        Assert.assertTrue("Find all method for 'BookRatingInfo' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEntries() {
        Assert.assertNotNull("Data on demand for 'BookRatingInfo' failed to initialize correctly", dod.getRandomBookRatingInfo());
        long count = bookRatingInfoRepository.count();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<BookRatingInfo> result = bookRatingInfoRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
        Assert.assertNotNull("Find entries method for 'BookRatingInfo' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'BookRatingInfo' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testSave() {
        Assert.assertNotNull("Data on demand for 'BookRatingInfo' failed to initialize correctly", dod.getRandomBookRatingInfo());
        BookRatingInfo obj = dod.getNewTransientBookRatingInfo(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'BookRatingInfo' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'BookRatingInfo' identifier to be null", obj.getBookRatingInfoId());
        try {
            bookRatingInfoRepository.save(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        bookRatingInfoRepository.flush();
        Assert.assertNotNull("Expected 'BookRatingInfo' identifier to no longer be null", obj.getBookRatingInfoId());
    }

	@Test
    public void testDelete() {
        BookRatingInfo obj = dod.getRandomBookRatingInfo();
        Assert.assertNotNull("Data on demand for 'BookRatingInfo' failed to initialize correctly", obj);
        Integer id = obj.getBookRatingInfoId();
        Assert.assertNotNull("Data on demand for 'BookRatingInfo' failed to provide an identifier", id);
        obj = bookRatingInfoRepository.findOne(id);
        bookRatingInfoRepository.delete(obj);
        bookRatingInfoRepository.flush();
        Assert.assertNull("Failed to remove 'BookRatingInfo' with identifier '" + id + "'", bookRatingInfoRepository.findOne(id));
    }
}
