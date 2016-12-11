package io.khasang.archivarius.entity;

import javax.persistence.Embeddable;

public enum  LifeCycle {
    RECEIVE,
    WRITEN,
    NEED_WRITE,
    REJECTED,
    DOC_ERROR,
    FINISHED
}
