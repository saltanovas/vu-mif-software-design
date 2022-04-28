package vu.mif.saltenis.bartas.softwaredesign.persistence;

import vu.mif.saltenis.bartas.softwaredesign.share.BaseEntity;

import java.io.IOException;

public interface IBaseRepository<T extends BaseEntity> {
    T getById(int id);

    void removeAndFlush(T entity) throws IOException;

    void saveAndFlush(T entity) throws IOException;
}
