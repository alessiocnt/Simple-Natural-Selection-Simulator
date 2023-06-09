/**
 * 
 */
package model.entity;

/**
 * Represent an energy level.
 *
 */
public interface Energy {

    /**
     * @param a
     *      first element of the comparison
     * @param b
     *      second element of the comparison
     * @return
     *      true if 'a' is greater than 'b'
     *      false instead
     */
    static boolean greater(final Energy a, final Energy b) {
        return a.getEnergy() > b.getEnergy();
    }

    /**
     * @return the energy value.
     */
    int getEnergy();

    /**
     * @param energy
     *         the energy value to set.
     */
    void setEnergy(Energy energy);

    /**
     * @param energy
     *         the energy value that will be added to the current energy value.
     */
    void addEnergy(Energy energy);

    /**
     * @param energy
     *         the energy value that will be detracted to the current energy value.
     */
    void detractEnergy(Energy energy);

}
