package com.thealgorithms.maths;

/**
 * Return on Investment (ROI) measures the profitability of an investment
 * relative to its cost, expressed as a percentage.
 *
 * <p>Formula: ROI = (Gain - Cost) / Cost × 100
 *
 * @see <a href="https://www.investopedia.com/terms/r/returnoninvestment.asp">Investopedia</a>
 */
public final class ReturnOnInvestment {

    private ReturnOnInvestment() {
    }

    /**
     * Calculates the return on investment as a percentage.
     *
     * @param gainFromInvestment the total value gained from the investment
     * @param costOfInvestment   the total cost of the investment
     * @return ROI as a percentage
     * @throws IllegalArgumentException if costOfInvestment is not positive
     */
    public static double returnOnInvestment(double gainFromInvestment, double costOfInvestment) {
        if (costOfInvestment <= 0) {
            throw new IllegalArgumentException("costOfInvestment must be greater than 0");
        }
        return (gainFromInvestment - costOfInvestment) / costOfInvestment * 100.0;
    }
}
