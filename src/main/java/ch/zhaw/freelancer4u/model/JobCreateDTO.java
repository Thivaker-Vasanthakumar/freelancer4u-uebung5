package ch.zhaw.freelancer4u.model;

public class JobCreateDTO {

    private String title;
    private String description;
    private JobType jobType;
    private Double earnings;
    private String companyId;

    public JobCreateDTO() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public JobType getJobType() {
        return jobType;
    }

    public Double getEarnings() {
        return earnings;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}