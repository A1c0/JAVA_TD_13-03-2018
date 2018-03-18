public interface OperationArithmetique {
    public Matrice additionner(Matrice mat) throws MatriceException;
    public Matrice multiplier (Matrice mat) throws MatriceException;
    public Matrice multiplierScalaire (int facteur);
}
