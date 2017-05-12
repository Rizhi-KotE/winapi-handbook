package common.service.impl;

import common.exception.HandbookException;
import model.WinApiFunction;
import model.WinApiFunctionRequirement;
import model.WinApiParameter;
import model.WinApiUserElement;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class WinApiHandbookServiceJdbc implements WinApiHandbookService {

    public static final String SELECT_PARAMETERS_BY_FUNCTION = "SELECT * FROM WINAPI_PARAMETER WHERE function_id = ?";
    public static final String DELETE_PARAM = "DELETE FROM WINAPI_PARAMETER WHERE id=?";
    public static final String UPDATE_PARAM = "UPDATE WINAPI_PARAMETER SET first_definition=?, type_definition=?, description=? WHERE ID=?";
    public static final String DELETE_FUNCTION = "DELETE FROM WINAPI_FUNCTION WHERE ID=?";
    public static final String UPDATE_CLASS = "UPDATE WINAPI_USER_ELEMENT SET NAME=?, DESCRIPTION=? WHERE ID=?";
    public static final String UPDATE_FUNCTION = "UPDATE WINAPI_FUNCTION SET NAME=?, DESCRIPTION=? WHERE id=?";
    public static final String REQUIREMENT = "REQUIREMET";
    public static final String UPDATE_REQUIREMENT = "UPDATE REQUIREMET SET category=?, category_value=? WHERE REQUIREMET.id=?";
    public static final String WINAPI_FUNCTION = "WINAPI_FUNCTION";
    public static final String WINAPI_PARAMETER = "WINAPI_PARAMETER";
    public static final String WINAPI_USER_ELEMENT = "WINAPI_USER_ELEMENT";
    private static final String SELECT_BY_ID = "SELECT * FROM WINAPI_USER_ELEMENT WHERE id=?";
    private static final String SELECT_BY_CLASS = "SELECT * FROM WINAPI_FUNCTION WHERE class_id=?";
    public static final String GET_REQUIREMENT_BY_FUNCTION = "SELECT * FROM REQUIREMET WHERE function_id=?";
    public static final String DELETE_REQUIREMENT = "DELETE FROM REQUIREMET WHERE id=?";
    public static Logger log = Logger.getLogger(WinApiHandbookServiceJdbc.class);
    private final JdbcTemplate template;
    private RowMapper<WinApiUserElement> winApiClassRowMapper = (rs, rowNum) -> {
        WinApiUserElement clazz = new WinApiUserElement();
        clazz.setId(rs.getLong("id"));
        clazz.setName(rs.getString("name"));
        clazz.setDescription(rs.getString("description"));
        return clazz;
    };
    private RowMapper<WinApiFunction> winApiFunctionRowMapper = (rs, rowNum) -> {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String syntax = rs.getString("syntax");
        String returnType = rs.getString("return_type");
        String returnTypeDescription = rs.getString("return_type_description");
        return new WinApiFunction(id,
                name,
                description,
                syntax,
                null,
                returnType,
                returnTypeDescription,
                new ArrayList<>());
    };

    private RowMapper<WinApiParameter> winApiParameterRowMapper = (rs, rowNum) -> {
        long id = rs.getLong("id");
        String firstDefinition = rs.getString("first_definition");
        String typeDefinition = rs.getString("type_definition");
        String description = rs.getString("description");
        return new WinApiParameter(id, firstDefinition, typeDefinition, description);
    };
    private RowMapper<WinApiFunctionRequirement> winApiRequirementRowMapper = (rs, rowNum)->{
        long id = rs.getLong("id");
        String category = rs.getString("category");
        String categoryValue = rs.getString("category_value");
        return new WinApiFunctionRequirement(id, category, categoryValue);
    };

    public WinApiHandbookServiceJdbc(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<WinApiUserElement> getAll() throws HandbookException {
        List<WinApiUserElement> elements = template.query("SELECT * FROM WINAPI_USER_ELEMENT", winApiClassRowMapper);
        for (WinApiUserElement e : elements) {
            List<WinApiFunction> functionByClass = getFunctionByClass(e);
            e.setFunctions(functionByClass);
        }
        return elements;
    }

    @Override
    public WinApiUserElement getUserElement(long id) throws HandbookException {
        WinApiUserElement winApiUserElement = this.template.queryForObject(SELECT_BY_ID, new Object[]{id}, winApiClassRowMapper);
        winApiUserElement.setFunctions(getFunctionByClass(winApiUserElement));
        return winApiUserElement;
    }

    @Override
    public WinApiUserElement saveOrUpdateUserElement(WinApiUserElement winApiUserElement) throws HandbookException {
        log.debug(format("save or update class [id=%d]", winApiUserElement.getId()));
        long id = winApiUserElement.getId();
        if (winApiUserElement.getId() == 0) {
            id = createNewClass(winApiUserElement).getId();
        } else {
            updateClass(winApiUserElement);
        }
        return getUserElement(id);
    }

    @Override
    public int removeElement(long id) throws HandbookException {
        return template.update("DELETE FROM WINAPI_USER_ELEMENT WHERE ID=?", id);
    }

    @Override
    public WinApiFunction createFunction(long topicId, WinApiFunction f) throws HandbookException {
        SimpleJdbcInsert functionInsert = new SimpleJdbcInsert(template)
                .usingGeneratedKeyColumns("id")
                .withTableName(WINAPI_FUNCTION);
        MapSqlParameterSource mapSqlParameterSource = mapFunctionToRow(topicId, f);
        Number number = functionInsert
                .executeAndReturnKey(mapSqlParameterSource);
        f.setId(number.longValue());
        for (WinApiParameter winApiParameter : f.getParams()) {
            createParam(f.getId(), winApiParameter);
        }
        for (WinApiFunctionRequirement p : f.getRequirements()) {
            createRequirement(f.getId(), p);
        }
        return f;
    }

    @Override
    public int updateFunction(WinApiFunction function) throws HandbookException {
        log.debug(format("update function [id=%d]", function.getId()));
        int update = template.update(UPDATE_FUNCTION,
                function.getName(), function.getDescription(), function.getId());
        for (WinApiParameter parameter : function.getParams()) {
            if (parameter.getId() == 0) {
                createParam(function.getId(), parameter);
            } else {
                if (updateParam(parameter) == 0)
                    throw new HandbookException(
                            format("Cannot find parameter [id=%d]", parameter.getId()));
            }
        }
        for (WinApiFunctionRequirement requirement : function.getRequirements()) {
            if (requirement.getId() == 0) {
                createRequirement(function.getId(), requirement);
            } else {
                if (updateRequirement(requirement) == 0)
                    throw new HandbookException(
                            format("Cannot find requirement [id=%d]", requirement.getId()));
            }
        }
        return update;
    }

    @Override
    public int removeWinApiFunction(long id) throws HandbookException {
        return template.update(DELETE_FUNCTION, id);
    }

    @Override
    public WinApiParameter createParam(long functionId, WinApiParameter p) {
        SimpleJdbcInsert paramsInsert = new SimpleJdbcInsert(template)
                .usingGeneratedKeyColumns("id")
                .withTableName(WINAPI_PARAMETER);
        MapSqlParameterSource parameterParams = mapParameterToRow(functionId, p);
        Number parameterId = paramsInsert
                .executeAndReturnKey(parameterParams);
        p.setId(parameterId.longValue());
        return p;
    }

    @Override
    public int updateParam(WinApiParameter parameter) throws HandbookException {
        return template.update(UPDATE_PARAM,
                parameter.getFirstDefinition(), parameter.getTypeDefinition(), parameter.getDescription(), parameter.getId());
    }

    @Override
    public int removeWinApiParameter(long id) throws HandbookException {
        return template.update(DELETE_PARAM, id);
    }

    @Override
    public WinApiFunctionRequirement createRequirement(long functionId, WinApiFunctionRequirement r) throws HandbookException {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template)
                .usingGeneratedKeyColumns("id")
                .withTableName(REQUIREMENT);
        MapSqlParameterSource requirementToRow = mapRequirementToRow(functionId, r);
        Number parameterId = insert
                .executeAndReturnKey(requirementToRow);
        r.setId(parameterId.longValue());
        return r;
    }

    @Override
    public int updateRequirement(WinApiFunctionRequirement requirement) throws HandbookException {
        return template.update(UPDATE_REQUIREMENT,
                requirement.getKey(), requirement.getValue(), requirement.getId());
    }

    @Override
    public int removeRequirement(long id) throws HandbookException {
        return template.update(DELETE_REQUIREMENT, id);
    }

    private MapSqlParameterSource mapRequirementToRow(long functionId, WinApiFunctionRequirement r) {
        return new MapSqlParameterSource()
                .addValue("category", r.getKey())
                .addValue("category_value", r.getValue())
                .addValue("function_id", functionId);
    }

    public List<WinApiFunction> getFunctionByClass(WinApiUserElement winApiUserElement) {
        List<WinApiFunction> query = template.query(SELECT_BY_CLASS, new Object[]{winApiUserElement.getId()}, winApiFunctionRowMapper);
        return query.stream().map(f -> {
            List<WinApiParameter> params = getParameterByFunction(f.getId());
            List<WinApiFunctionRequirement> requirements = getRequirementsByFunction(f.getId());
            f.setRequirements(requirements);
            f.setParams(params);
            return f;
        }).collect(toList());
    }

    private List<WinApiFunctionRequirement> getRequirementsByFunction(long id) {
        return template.query(GET_REQUIREMENT_BY_FUNCTION, new Object[]{id}, winApiRequirementRowMapper);
    }

    public List<WinApiParameter> getParameterByFunction(long functionId) {
        return template.query(SELECT_PARAMETERS_BY_FUNCTION, new Object[]{functionId}, winApiParameterRowMapper);
    }

    private WinApiUserElement createNewClass(WinApiUserElement topic) throws HandbookException {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);

        MapSqlParameterSource params = mapClassToRow(topic);
        Number id = insert.usingGeneratedKeyColumns("id")
                .withTableName(WINAPI_USER_ELEMENT)
                .executeAndReturnKey(params);
        topic.setId(id.longValue());
        for (WinApiFunction f : topic.getFunctions()) {
            createFunction(topic.getId(), f);
        }

        return topic;
    }

    private MapSqlParameterSource mapClassToRow(WinApiUserElement topic) {
        return new MapSqlParameterSource()
                .addValue("name", topic.getName())
                .addValue("description", topic.getDescription());
    }

    private MapSqlParameterSource mapFunctionToRow(long classId, WinApiFunction f) {
        return new MapSqlParameterSource()
                .addValue("name", f.getName())
                .addValue("syntax", f.getSyntax())
                .addValue("return_type", f.getReturnType())
                .addValue("return_type_description", f.getReturnTypeDescription())
                .addValue("description", f.getDescription())
                .addValue("class_id", classId);
    }

    private MapSqlParameterSource mapParameterToRow(long functionId, WinApiParameter p) {
        return new MapSqlParameterSource()
                .addValue("first_definition", p.getFirstDefinition())
                .addValue("type_definition", p.getTypeDefinition())
                .addValue("description", p.getDescription())
                .addValue("function_id", functionId);
    }

    public int updateClass(WinApiUserElement winApiUserElement) throws HandbookException {
        int update = template.update(UPDATE_CLASS,
                winApiUserElement.getName(), winApiUserElement.getDescription(), winApiUserElement.getId());
        for (WinApiFunction function : winApiUserElement.getFunctions()) {
            if (function.getId() == 0) {
                createFunction(winApiUserElement.getId(), function);
            } else {
                updateFunction(function);
            }
        }
        return update;
    }
}
