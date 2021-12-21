package by.epam.medicalweb.mapper;

import by.epam.medicalweb.entity.AbstractEntity;

import java.sql.ResultSet;
import java.util.Optional;

public interface BaseMapper<T extends AbstractEntity> {
    Optional<T> mapEntity(ResultSet resultSet);
}
