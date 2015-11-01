package com.cd.book.dataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.cd.book.entity.BookShelf;
import com.cd.book.repository.BookShelfRepository;

@Component
@Configurable
public class BookShelfDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<BookShelf> data;

	@Autowired
    BookShelfRepository bookShelfRepository;

	public BookShelf getNewTransientBookShelf(int index) {
        BookShelf obj = new BookShelf();
        setCreatedBy(obj, index);
        setCreatedOn(obj, index);
        setIsActive(obj, index);
        setLastUpdatedBy(obj, index);
        setLastUpdatedOn(obj, index);
        setShelfName(obj, index);
        setUserId(obj, index);
        return obj;
    }

	public void setCreatedBy(BookShelf obj, int index) {
        String createdBy = "createdBy_" + index;
        if (createdBy.length() > 100) {
            createdBy = createdBy.substring(0, 100);
        }
        obj.setCreatedBy(createdBy);
    }

	public void setCreatedOn(BookShelf obj, int index) {
        Calendar createdOn = Calendar.getInstance();
        obj.setCreatedOn(createdOn);
    }

	public void setIsActive(BookShelf obj, int index) {
        Boolean isActive = Boolean.TRUE;
        obj.setActive(isActive);
    }

	public void setLastUpdatedBy(BookShelf obj, int index) {
        String lastUpdatedBy = "lastUpdatedBy_" + index;
        if (lastUpdatedBy.length() > 100) {
            lastUpdatedBy = lastUpdatedBy.substring(0, 100);
        }
        obj.setLastUpdatedBy(lastUpdatedBy);
    }

	public void setLastUpdatedOn(BookShelf obj, int index) {
        Calendar lastUpdatedOn = Calendar.getInstance();
        obj.setLastUpdatedOn(lastUpdatedOn);
    }

	public void setShelfName(BookShelf obj, int index) {
        String shelfName = "shelfName_" + index;
        if (shelfName.length() > 30) {
            shelfName = new Random().nextInt(10) + shelfName.substring(1, 30);
        }
        obj.setShelfName(shelfName);
    }

	public void setUserId(BookShelf obj, int index) {
        Integer userId = new Integer(index);
        obj.setUserId(userId);
    }

	public BookShelf getSpecificBookShelf(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        BookShelf obj = data.get(index);
        Integer id = obj.getBookShelfId();
        return bookShelfRepository.findOne(id);
    }

	public BookShelf getRandomBookShelf() {
        init();
        BookShelf obj = data.get(rnd.nextInt(data.size()));
        Integer id = obj.getBookShelfId();
        return bookShelfRepository.findOne(id);
    }

	public boolean modifyBookShelf(BookShelf obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = bookShelfRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'BookShelf' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<BookShelf>();
        for (int i = 0; i < 10; i++) {
            BookShelf obj = getNewTransientBookShelf(i);
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
            data.add(obj);
        }
    }
}
