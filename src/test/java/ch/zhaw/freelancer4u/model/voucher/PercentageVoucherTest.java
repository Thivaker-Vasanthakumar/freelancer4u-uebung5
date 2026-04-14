package ch.zhaw.freelancer4u.model.voucher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.zhaw.freelancer4u.model.Job;

public class PercentageVoucherTest {

    @Test
    void testEmpty() {
        PercentageVoucher voucher = new PercentageVoucher(42);

        double result = voucher.getDiscount(List.of());

        assertEquals(0.0, result);
    }

    @Test
    void testPercentageLessThanZero() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(-1);
        });

        assertEquals("Error: Discount value must be greater zero.", exception.getMessage());
    }

    @Test
    void testPercentageEqualZero() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(0);
        });

        assertEquals("Error: Discount value must be greater zero.", exception.getMessage());
    }

    @Test
    void testPercentageGreaterThanFifty() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(51);
        });

        assertEquals("Error: Discount value must less or equal 50.", exception.getMessage());
    }

    @Test
    void testTwoJobsWithMockito() {
        Job job1 = Mockito.mock(Job.class);
        Job job2 = Mockito.mock(Job.class);

        Mockito.when(job1.getEarnings()).thenReturn(42.0);
        Mockito.when(job2.getEarnings()).thenReturn(77.0);

        PercentageVoucher voucher = new PercentageVoucher(42);
        double result = voucher.getDiscount(List.of(job1, job2));

        assertEquals(49.98, result, 0.0001);
    }
}