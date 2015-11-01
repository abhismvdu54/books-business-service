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

import com.cd.book.entity.Book;
import com.cd.book.entity.BookShelf;
import com.cd.book.entity.BooksInBookShelf;
import com.cd.book.repository.BooksInBookShelfRepository;

@Component
@Configurable
public class BooksInBookShelfDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<BooksInBookShelf> data;

	@Autowired
    BookDataOnDemand bookDataOnDemand;

	@Autowired
    BookShelfDataOnDemand bookShelfDataOnDemand;

	@Autowired
    BooksInBookShelfRepository booksInBookShelfRepository;

	public BooksInBookShelf getNewTransientBooksInBookShelf(int index) {
        BooksInBookShelf obj = new BooksInBookShelf();
        setBookId(obj, index);
        setBookShelfId(obj, index);
        setCreatedBy(obj, index);
        setCreatedOn(obj, index);
        setIsActive(obj, index);
        setLastUpdatedBy(obj, index);
        setLastUpdatedOn(obj, index);
        return obj;
    }

	public void setBookId(BooksInBookShelf obj, int index) {
        Book bookId = bookDataOnDemand.getRandomBook();
        obj.setBookId(bookId);
    }

	public void setBookShelfId(BooksInBookShelf obj, int index) {
        BookShelf bookShelfId = bookShelfDataOnDemand.getRandomBookShelf();
        obj.setBookShelfId(bookShelfId);
    }

	public void setCreatedBy(BooksInBookShelf obj, int index) {
        String createdBy = "createdBy_" + index;
        if (createdBy.length() > 100) {
            createdBy = createdBy.substring(0, 100);
        }
        obj.setCreatedBy(createdBy);
    }

	public void setCreatedOn(BooksInBookShelf obj, int index) {
        Calendar createdOn = Calendar.getInstance();
        obj.setCreatedOn(createdOn);
    }

	public void setIsActive(BooksInBookShelf obj, int index) {
        Boolean isActive = Boolean.TRUE;
        obj.setIsActive(isActive);
    }

	public void setLastUpdatedBy(BooksInBookShelf obj, int index) {
        String lastUpdatedBy = "lastUpdatedBy_" + index;
        if (lastUpdatedBy.length() > 100) {
            lastUpdatedBy = lastUpdatedBy.substring(0, 100);
        }
        obj.setLastUpdatedBy(lastUpdatedBy);
    }

	public void setLastUpdatedOn(BooksInBookShelf obj, int index) {
        Calendar lastUpdatedOn = Calendar.getInstance();
        obj.setLastUpdatedOn(lastUpdatedOn);
    }

	public BooksInBookShelf getSpecificBooksInBookShelf(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        BooksInBookShelf obj = data.get(index);
        Integer id = obj.getBooksInBookShelfId();
        return booksInBookShelfRepository.findOne(id);
    }

	public BooksInBookShelf getRandomBooksInBookShelf() {
        init();
        BooksInBookShelf obj = data.get(rnd.nextInt(data.size()));
        Integer id = obj.getBooksInBookShelfId();
        return booksInBookShelfRepository.findOne(id);
    }

	public boolean modifyBooksInBookShelf(BooksInBookShelf obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = booksInBookShelfRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'BooksInBookShelf' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<BooksInBookShelf>();
        for (int i = 0; i < 10; i++) {
            BooksInBookShelf obj = getNewTransientBooksInBookShelf(i);
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
            data.add(obj);
        }
    }
}
