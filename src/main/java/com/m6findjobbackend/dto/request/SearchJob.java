package com.m6findjobbackend.dto.request;

public class SearchJob {
    private String title;
    private Long cityId;
    private Long fieldId;
    private Long companyId;
    private Long vacancies;
    private Long workingTimeId;
    private Integer start;
    private Integer pageSize;

    public SearchJob() {
    }

    public SearchJob(String title, Long cityId, Long fieldId, Long companyId, Long vacancies, Long workingTimeId) {
        this.title = title;
        this.cityId = cityId;
        this.fieldId = fieldId;
        this.companyId = companyId;
        this.vacancies = vacancies;
        this.workingTimeId = workingTimeId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVacancies() {
        return vacancies;
    }

    public void setVacancies(Long vacancies) {
        this.vacancies = vacancies;
    }

    public Long getWorkingTimeId() {
        return workingTimeId;
    }

    public void setWorkingTimeId(Long workingTimeId) {
        this.workingTimeId = workingTimeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
