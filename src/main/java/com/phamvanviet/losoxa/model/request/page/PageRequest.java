package com.phamvanviet.losoxa.model.request.page;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Optional;

public class PageRequest implements Pageable, Serializable {
    private int limit;
    private int offset;
    private Sort sort;

    public PageRequest(int offset, int limit, Sort sort) {
        if(offset<0){
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }


    @Override
    public boolean isPaged() {
        return false;
    }

    @Override
    public boolean isUnpaged() {
        return false;
    }

    @Override
    public int getPageNumber() {
        return offset/limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return null;
    }

    @Override
    public Pageable next() {
        return new PageRequest((int)getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public PageRequest previous(){
        return hasPrevious()? new PageRequest((int)getOffset() - getPageSize(),getPageSize(), getSort()): this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious()? previous(): first();
    }

    @Override
    public Pageable first() {
        return new PageRequest(0, getPageSize(),getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset>limit;
    }

    @Override
    public Optional<Pageable> toOptional() {
        return Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PageRequest)) return false;

        PageRequest that = (PageRequest) o;

        return new EqualsBuilder()
                .append(limit, that.limit)
                .append(offset, that.offset)
                .append(sort, that.sort)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(limit)
                .append(offset)
                .append(sort)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("limit", limit)
                .append("offset", offset)
                .append("sort", sort)
                .toString();
    }

}
