package com.m6findjobbackend.repository;

import com.m6findjobbackend.dto.request.SearchJob;
import com.m6findjobbackend.dto.response.RecuitmentNewDTO;
import com.m6findjobbackend.model.RecuitmentNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class IRecruitmentNewDAOImpl implements IRecruitmentNewDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public List<RecuitmentNewDTO> findJob(SearchJob searchJob) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("produces")
                .returningResultSet("recuitmentnew", (RowMapper<RecuitmentNewDTO>) (rs, rowNum) -> {
                    RecuitmentNewDTO recuitmentNewDTO = new RecuitmentNewDTO();
                    recuitmentNewDTO.setId(rs.getLong("id"));
                    recuitmentNewDTO.setTitle(rs.getString("title"));
                    recuitmentNewDTO.setDescription(rs.getString("description"));
                    recuitmentNewDTO.setStatus(rs.getString("status"));
                    recuitmentNewDTO.setCompanyId(rs.getLong("companyId"));
                    recuitmentNewDTO.setCityId(rs.getLong("cityId"));
                    recuitmentNewDTO.setCityName(rs.getString("cityName"));
                    return recuitmentNewDTO;
                });
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("title", searchJob.getTitle())
                .addValue("cityId", searchJob.getCityId())
                .addValue("fieldId", searchJob.getFieldId())
                .addValue("companyId", searchJob.getCompanyId())
                .addValue("vacancies", searchJob.getVacancies())
                .addValue("workingTimeId", searchJob.getWorkingTimeId())
                .addValue("start", searchJob.getStart())
                .addValue("page_size", searchJob.getPageSize());
        Map out = simpleJdbcCall.execute(in);
        return (List<RecuitmentNewDTO>) out.get("recuitmentnew");
    }
}
