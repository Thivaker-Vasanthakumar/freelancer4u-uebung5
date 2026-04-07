package ch.zhaw.freelancer4u.model.voucher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.zhaw.freelancer4u.model.Job;
import ch.zhaw.freelancer4u.model.JobType;

public class PercentageVoucherTest {

    @Test
    void testEmpty() {
        PercentageVoucher voucher = new PercentageVoucher(42);

        double result = voucher.getDiscount(List.of());

        assertEquals(0.0, result);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 5, 20, 49, 50 })
    void testOneJobWithValue50(int percentage) {
        PercentageVoucher voucher = new PercentageVoucher(percentage);
        Job job = new Job("Job 1", "Test job", JobType.IMPLEMENT, 50.0, "");

        double result = voucher.getDiscount(List.of(job));
        double expected = 50.0 * percentage / 100.0;

        assertEquals(expected, result, 0.0001);
    }

    @Test
    void testTwoJobsWith42Percent() {
        PercentageVoucher voucher = new PercentageVoucher(42);

        Job job1 = new Job("Job 1", "Test job 1", JobType.IMPLEMENT, 42.0, "");
        Job job2 = new Job("Job 2", "Test job 2", JobType.REVIEW, 77.0, "");

        double result = voucher.getDiscount(List.of(job1, job2));

        assertEquals(49.98, result, 0.0001);
    }
}