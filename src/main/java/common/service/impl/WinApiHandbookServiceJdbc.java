package common.service.impl;

import common.exception.HandbookException;
import common.service.WinApiHandbookService;
import model.WinApiClass;
import model.WinApiFunction;
import model.WinApiParameter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;

public class WinApiHandbookServiceJdbc implements WinApiHandbookService {

    private final JdbcTemplate template;

    public WinApiHandbookServiceJdbc(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public WinApiClass getWinApiClass(long id) throws HandbookException {
        return null;
    }

    @Override
    public List<WinApiClass> findClasses(String keyword) throws HandbookException {
        return null;
    }

    @Override
    public WinApiClass createWinApiClass(WinApiClass topic) throws HandbookException {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", topic.getName())
                .addValue("description", topic.getDescription());
        Number id = insert.usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params);
        topic.setId(id.longValue());
        return topic;
    }

    @Override
    public void updateClass(WinApiClass topic) throws HandbookException {

    }

    @Override
    public void removeTopic(long id) throws HandbookException {

    }

    @Override
    public void updateWinApiFunction(WinApiFunction function) throws HandbookException {

    }

    @Override
    public void removeWinApiFunction(long id) throws HandbookException {

    }

    @Override
    public void updateWinApiParameter(WinApiParameter parameter) throws HandbookException {

    }

    @Override
    public void removeWinApiParameter(long id) throws HandbookException {

    }
}
