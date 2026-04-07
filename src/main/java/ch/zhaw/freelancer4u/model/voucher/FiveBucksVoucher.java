package ch.zhaw.freelancer4u.model.voucher;

import java.util.List;

import ch.zhaw.freelancer4u.model.Job;

public class FiveBucksVoucher implements Voucher {

    @Override
    public double getDiscount(List<Job> jobs) {
        double total = jobs.stream()
                .mapToDouble(Job::getEarnings)
                .sum();

        return total >= 10.0 ? 5.0 : 0.0;
    }
}