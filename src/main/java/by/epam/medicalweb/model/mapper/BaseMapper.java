package by.epam.medicalweb.model.mapper;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.entity.AbstractEntity;

import java.sql.ResultSet;
import java.util.Optional;

public interface BaseMapper<T extends AbstractEntity> {
    Optional<T> mapEntity(ResultSet resultSet) throws DaoException;
}
