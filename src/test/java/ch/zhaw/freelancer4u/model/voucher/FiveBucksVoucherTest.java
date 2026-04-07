package ch.zhaw.freelancer4u.model.voucher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import ch.zhaw.freelancer4u.model.Job;
import ch.zhaw.freelancer4u.model.JobType;

public class FiveBucksVoucherTest {

    @Test
    void testEmpty() {
        FiveBucksVoucher voucher = new FiveBucksVoucher();

        double discount = voucher.getDiscount(List.of());

        assertEquals(0.0, discount);
    }

    @Test
    void testTen() {
        FiveBucksVoucher voucher = new FiveBucksVoucher();

        Job job = new Job("Job 1", "Test job", JobType.IMPLEMENT, 10.0, "");

        double discount = voucher.getDiscount(List.of(job));

        assertEquals(5.0, discount);
    }

    @Test
    void testBelowTen() {
        FiveBucksVoucher voucher = new FiveBucksVoucher();

        Job job = new Job("Job 1", "Test job", JobType.IMPLEMENT, 9.0, "");

        double discount = voucher.getDiscount(List.of(job));

        assertEquals(0.0, discount);
    }
}