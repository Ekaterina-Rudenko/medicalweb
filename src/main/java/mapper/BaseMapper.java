package mapper;

import entity.AbstractEntity;
import exception.DaoException;

import java.sql.ResultSet;
import java.util.Optional;

public interface BaseMapper<T extends AbstractEntity> {
    Optional<T> mapEntity(ResultSet resultSet) throws DaoException;
}
