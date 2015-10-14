
package com.cd.book.entity;
/**
 * @author Mayank
 * 
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable, Cloneable, Comparable<Object> {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="Id", nullable=false )
    private Integer id;

    @Version
    private long version = 0;

    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public boolean equals(Object o) {
	return EqualsBuilder.reflectionEquals(this, o);
    }

    public int compareTo(Object o) {
	return CompareToBuilder.reflectionCompare(this, o);
    }

    public int hashCode(Object o) {
	return HashCodeBuilder.reflectionHashCode(o);
    }

    public Integer getId() {
    	if(id==null)
    		return 0;
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public long getVersion() {
	return version;
    }

    public void setVersion(long version) {
	this.version = version;
    }

}
