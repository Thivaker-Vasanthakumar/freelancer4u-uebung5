package ch.zhaw.freelancer4u.model;

public class JobStateChangeDTO {

    private String jobId;
    private String freelancerId;

    public JobStateChangeDTO() {
    }

    public String getJobId() {
        return jobId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }
}