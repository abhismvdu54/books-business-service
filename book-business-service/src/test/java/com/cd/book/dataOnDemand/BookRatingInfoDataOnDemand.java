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

import com.cd.book.entity.BookRatingInfo;
import com.cd.book.repository.BookRatingInfoRepository;

@Component
@Configurable
public class BookRatingInfoDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<BookRatingInfo> data;

	@Autowired
    BookRatingInfoRepository bookRatingInfoRepository;

	public BookRatingInfo getNewTransientBookRatingInfo(int index) {
        BookRatingInfo obj = new BookRatingInfo();
        setBookRatingLabel(obj, index);
        setBookRatingNo(obj, index);
        setCreatedBy(obj, index);
        setCreatedOn(obj, index);
        setLastUpdatedBy(obj, index);
        setLastUpdatedOn(obj, index);
        return obj;
    }

	public void setBookRatingLabel(BookRatingInfo obj, int index) {
        String bookRatingLabel = "bookRatingLabel_" + index;
        if (bookRatingLabel.length() > 30) {
            bookRatingLabel = bookRatingLabel.substring(0, 30);
        }
        obj.setBookRatingLabel(bookRatingLabel);
    }

	public void setBookRatingNo(BookRatingInfo obj, int index) {
        Integer bookRatingNo = new Integer(index);
        obj.setBookRatingNo(bookRatingNo);
    }

	public void setCreatedBy(BookRatingInfo obj, int index) {
        String createdBy = "createdBy_" + index;
        if (createdBy.length() > 100) {
            createdBy = createdBy.substring(0, 100);
        }
        obj.setCreatedBy(createdBy);
    }

	public void setCreatedOn(BookRatingInfo obj, int index) {
        Calendar createdOn = Calendar.getInstance();
        obj.setCreatedOn(createdOn);
    }

	public void setLastUpdatedBy(BookRatingInfo obj, int index) {
        String lastUpdatedBy = "lastUpdatedBy_" + index;
        if (lastUpdatedBy.length() > 100) {
            lastUpdatedBy = lastUpdatedBy.substring(0, 100);
        }
        obj.setLastUpdatedBy(lastUpdatedBy);
    }

	public void setLastUpdatedOn(BookRatingInfo obj, int index) {
        Calendar lastUpdatedOn = Calendar.getInstance();
        obj.setLastUpdatedOn(lastUpdatedOn);
    }

	public BookRatingInfo getSpecificBookRatingInfo(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        BookRatingInfo obj = data.get(index);
        Integer id = obj.getBookRatingInfoId();
        return bookRatingInfoRepository.findOne(id);
    }

	public BookRatingInfo getRandomBookRatingInfo() {
        init();
        BookRatingInfo obj = data.get(rnd.nextInt(data.size()));
        Integer id = obj.getBookRatingInfoId();
        return bookRatingInfoRepository.findOne(id);
    }

	public boolean modifyBookRatingInfo(BookRatingInfo obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = bookRatingInfoRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'BookRatingInfo' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<BookRatingInfo>();
        for (int i = 0; i < 10; i++) {
            BookRatingInfo obj = getNewTransientBookRatingInfo(i);
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
            data.add(obj);
        }
    }
}
