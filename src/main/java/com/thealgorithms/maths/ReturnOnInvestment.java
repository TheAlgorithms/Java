package com.thealgorithms.maths;

/**
 * Return on Investment (ROI) calculations for evaluating investment profitability.
 *
 * <p>This class provides two related computations:
 * <ul>
 *   <li><b>Simple ROI</b> – measures total gain relative to cost:
 *       {@code ROI = (Gain - Cost) / Cost × 100}</li>
 *   <li><b>Annualized ROI</b> – converts a total ROI over multiple years into
 *       an equivalent annual rate using the geometric mean:
 *       {@code Annualized ROI = ((1 + ROI/100)^(1/n) - 1) × 100}</li>
 * </ul>
 *
 * @see <a href="https://www.investopedia.com/terms/r/returnoninvestment.asp">Investopedia – ROI</a>
 * @see <a href="https://www.investopedia.com/terms/a/annualized-total-return.asp">Investopedia – Annualized Return</a>
 */
public final class ReturnOnInvestment {

    private ReturnOnInvestment() {
    }

    /**
     * Calculates the simple return on investment as a percentage.
     *
     * @param gainFromInvestment the total value received from the investment
     * @param costOfInvestment   the total cost of the investment (must be positive)
     * @return ROI as a percentage; negative when a loss occurred
     * @throws IllegalArgumentException if {@code costOfInvestment} is not positive
     */
    public static double returnOnInvestment(final double gainFromInvestment, final double costOfInvestment) {
        if (costOfInvestment <= 0) {
            throw new IllegalArgumentException("costOfInvestment must be greater than 0");
        }
        return (gainFromInvestment - costOfInvestment) / costOfInvestment * 100.0;
    }

    /**
     * Calculates the annualized (per-year) return on investment.
     *
     * <p>While simple ROI tells you the total gain over an entire holding period,
     * annualized ROI normalizes that gain to a yearly rate so that investments
     * held for different lengths of time can be compared on equal footing.
     * It applies the geometric-mean formula:
     *
     * <pre>
     *   Annualized ROI = ((1 + simpleROI / 100) ^ (1 / years) - 1) × 100
     * </pre>
     *
     * @param gainFromInvestment the total value received from the investment
     * @param costOfInvestment   the total cost of the investment (must be positive)
     * @param years              the number of years the investment was held (must be positive)
     * @return annualized ROI as a percentage
     * @throws IllegalArgumentException if {@code costOfInvestment} or {@code years} is not positive
     */
    public static double annualizedReturnOnInvestment(final double gainFromInvestment, final double costOfInvestment, final double years) {
        if (costOfInvestment <= 0) {
            throw new IllegalArgumentException("costOfInvestment must be greater than 0");
        }
        if (years <= 0) {
            throw new IllegalArgumentException("years must be greater than 0");
        }
        final double simpleRoi = returnOnInvestment(gainFromInvestment, costOfInvestment);
        return (Math.pow(1.0 + simpleRoi / 100.0, 1.0 / years) - 1.0) * 100.0;
    }
}
