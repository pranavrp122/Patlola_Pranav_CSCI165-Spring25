public interface Resizable {
    /**
     * Resize this object by the given percentage.
     * @param percent the percent to grow (e.g. 50 → 1.5×, –20 → 0.8×)
     */
    void resize(int percent);
}
