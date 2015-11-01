package com.cd.book.dataOnDemand;
import java.math.BigDecimal;
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
import com.cd.book.repository.BookRepository;

@Component
@Configurable
public class BookDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Book> data;

	@Autowired
    BookRepository bookRepository;

	public Book getNewTransientBook(int index) {
        Book obj = new Book();
        setAuthor(obj, index);
        setCdRating(obj, index);
        setCreatedBy(obj, index);
        setCreatedOn(obj, index);
        setIsbn10(obj, index);
        setIsbn13(obj, index);
        setLastUpdatedBy(obj, index);
        setLastUpdatedOn(obj, index);
        setPublisher(obj, index);
        setSearchCount(obj, index);
        setTitle(obj, index);
        return obj;
    }

	public void setAuthor(Book obj, int index) {
        String author = "author_" + index;
        if (author.length() > 200) {
            author = author.substring(0, 200);
        }
        obj.setAuthor(author);
    }

	public void setCdRating(Book obj, int index) {
        BigDecimal cdRating = BigDecimal.valueOf(index);
        if (cdRating.compareTo(new BigDecimal("9.99")) == 1) {
            cdRating = new BigDecimal("9.99");
        }
        obj.setCdRating(cdRating);
    }

	public void setCreatedBy(Book obj, int index) {
        String createdBy = "createdBy_" + index;
        if (createdBy.length() > 100) {
            createdBy = createdBy.substring(0, 100);
        }
        obj.setCreatedBy(createdBy);
    }

	public void setCreatedOn(Book obj, int index) {
        Calendar createdOn = Calendar.getInstance();
        obj.setCreatedOn(createdOn);
    }

	public void setIsbn10(Book obj, int index) {
        Integer isbn10 = new Integer(index);
        obj.setIsbn10(isbn10);
    }

	public void setIsbn13(Book obj, int index) {
        Integer isbn13 = new Integer(index);
        obj.setIsbn13(isbn13);
    }

	public void setLastUpdatedBy(Book obj, int index) {
        String lastUpdatedBy = "lastUpdatedBy_" + index;
        if (lastUpdatedBy.length() > 100) {
            lastUpdatedBy = lastUpdatedBy.substring(0, 100);
        }
        obj.setLastUpdatedBy(lastUpdatedBy);
    }

	public void setLastUpdatedOn(Book obj, int index) {
        Calendar lastUpdatedOn = Calendar.getInstance();
        obj.setLastUpdatedOn(lastUpdatedOn);
    }

	public void setPublisher(Book obj, int index) {
        String publisher = "publisher_" + index;
        if (publisher.length() > 200) {
            publisher = publisher.substring(0, 200);
        }
        obj.setPublisher(publisher);
    }

	public void setSearchCount(Book obj, int index) {
        Integer searchCount = new Integer(index);
        obj.setSearchCount(searchCount);
    }

	public void setTitle(Book obj, int index) {
        String title = "title_" + index;
        if (title.length() > 200) {
            title = new Random().nextInt(10) + title.substring(1, 200);
        }
        obj.setTitle(title);
    }

	public Book getSpecificBook(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Book obj = data.get(index);
        Integer id = obj.getBookId();
        return bookRepository.findOne(id);
    }

	public Book getRandomBook() {
        init();
        Book obj = data.get(rnd.nextInt(data.size()));
        Integer id = obj.getBookId();
        return bookRepository.findOne(id);
    }

	public boolean modifyBook(Book obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = bookRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Book' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Book>();
        for (int i = 0; i < 10; i++) {
            Book obj = getNewTransientBook(i);
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
            data.add(obj);
        }
    }
}
