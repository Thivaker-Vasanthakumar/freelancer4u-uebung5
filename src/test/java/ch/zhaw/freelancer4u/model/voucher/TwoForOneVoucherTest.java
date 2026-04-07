package ch.zhaw.freelancer4u.model.voucher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import ch.zhaw.freelancer4u.model.Job;
import ch.zhaw.freelancer4u.model.JobType;

public class TwoForOneVoucherTest {

    @Test
    void testTwoDifferentTypes() {
        TwoForOneVoucher voucher = new TwoForOneVoucher(JobType.TEST);

        Job job1 = new Job("Job 1", "Test job 1", JobType.IMPLEMENT, 77.0, "");
        Job job2 = new Job("Job 2", "Test job 2", JobType.REVIEW, 33.0, "");

        double result = voucher.getDiscount(List.of(job1, job2));

        assertEquals(0.0, result);
    }

    @Test
    void testTwoSameType() {
        TwoForOneVoucher voucher = new TwoForOneVoucher(JobType.TEST);

        Job job1 = new Job("Job 1", "Test job 1", JobType.TEST, 77.0, "");
        Job job2 = new Job("Job 2", "Test job 2", JobType.TEST, 33.0, "");

        double result = voucher.getDiscount(List.of(job1, job2));

        assertEquals(55.0, result, 0.0001);
    }

    @Test
    void testThreeSameType() {
        TwoForOneVoucher voucher = new TwoForOneVoucher(JobType.REVIEW);

        Job job1 = new Job("Job 1", "Test job 1", JobType.REVIEW, 77.0, "");
        Job job2 = new Job("Job 2", "Test job 2", JobType.REVIEW, 33.0, "");
        Job job3 = new Job("Job 3", "Test job 3", JobType.REVIEW, 99.0, "");

        double result = voucher.getDiscount(List.of(job1, job2, job3));

        assertEquals(104.5, result, 0.0001);
    }

    @Test
    void testOnlyFirstTwoMatchType() {
        TwoForOneVoucher voucher = new TwoForOneVoucher(JobType.REVIEW);

        Job job1 = new Job("Job 1", "Test job 1", JobType.REVIEW, 77.0, "");
        Job job2 = new Job("Job 2", "Test job 2", JobType.REVIEW, 33.0, "");
        Job job3 = new Job("Job 3", "Test job 3", JobType.TEST, 99.0, "");

        double result = voucher.getDiscount(List.of(job1, job2, job3));

        assertEquals(55.0, result, 0.0001);
    }

    @ParameterizedTest
    @CsvSource({ "0,0", "1,0", "2,77", "3,115.5", "4,154" })
    void testParameterized(ArgumentsAccessor arguments) {
        int numberOfJobs = arguments.getInteger(0);
        double expected = arguments.getDouble(1);

        TwoForOneVoucher voucher = new TwoForOneVoucher(JobType.TEST);
        List<Job> jobs = new ArrayList<>();

        for (int i = 0; i < numberOfJobs; i++) {
            jobs.add(new Job("Job " + i, "Test job", JobType.TEST, 77.0, ""));
        }

        double result = voucher.getDiscount(jobs);

        assertEquals(expected, result, 0.0001);
    }
}