public class Matrice implements OperationArithmetique, OperationStatistique, OperationVectorielle, Transformable {
    private int nbrLignes;
    private int nbrColonnes;
    private float[][] elementMatrice;
    Noyau noyau;

    public class Noyau{
        private int nbrLignes;
        private int nbrColonnes;
        private float[][] noyau;

        public Noyau(int nbrLignes, int nbrColonnes, float[][] noyau){
            this.nbrColonnes = nbrColonnes;
            this.nbrLignes = nbrLignes;
            this.noyau = new float[nbrColonnes][nbrLignes];
            for (int i = 0; i < nbrLignes; i++){
                for (int j = 0; j < nbrColonnes; j++){
                    elementMatrice[j][i] = noyau[j][i];
                }
            }
        }

        @Override
        public String toString() {
            String text = "Noyau " + nbrLignes + "x" + nbrColonnes + ":\n";
            for (int i = 0; i < nbrLignes; i++){
                for (int j = 0; j < nbrColonnes; j++){
                    if (j != nbrColonnes - 1)
                        text += elementMatrice[j][i] + "|";
                    else
                        text += elementMatrice[j][i];
                }
                text += "\n";
            }
            return text;
        }
    }

    public Matrice(int lignes, int colonnes) {
        this.nbrLignes = lignes;
        this.nbrColonnes = colonnes;
        elementMatrice = new float[colonnes][lignes];
    }

    public Matrice(int lignes, int colonnes, float[][] reels) {
        this(lignes, colonnes);
        for (int i = 0; i < lignes; i++){
            for (int j = 0; j < colonnes; j++){
                elementMatrice[j][i] = reels[j][i];
            }
        }
    }

    public float[][] getElementMatrice() {
        return elementMatrice;
    }

    public int getNbrColonnes() {
        return nbrColonnes;
    }

    public int getNbrLignes() {
        return nbrLignes;
    }

    public void setElementMatrice(float[][] elementMatrice) {
        this.elementMatrice = elementMatrice;
    }

    public void setNbrColonnes(int nbrColonnes) {
        this.nbrColonnes = nbrColonnes;
    }

    public void setNbrLignes(int nbrLignes) {
        this.nbrLignes = nbrLignes;
    }

    @Override
    public String toString() {
        String text = "Matrice " + nbrLignes + "x" + nbrColonnes + ":\n";
        for (int j = 0; j < nbrLignes; j++){
            for (int i = 0; i < nbrColonnes; i++){
                if (i != nbrColonnes - 1)
                    text += elementMatrice[j][i] + "|";
                else
                    text += elementMatrice[j][i];
            }
            text += "\n";
        }
        return text;
    }

    public void zeros(){
        for (int i = 0; i < nbrLignes; i++) {
            for (int j = 0; j < nbrColonnes; j++) {
                elementMatrice[j][i] = 0;
            }
        }
    }

    public void identifie() throws MatriceException{
        if (nbrLignes != nbrColonnes)
            throw new MatriceException("La matrice n'est pas identité");
        for (int i = 0; i < nbrLignes; i++)
            elementMatrice[i][i] = 1;
    }

    @Override
    public Matrice additionner(Matrice mat) throws MatriceException{
        Matrice somme = new Matrice(1,1);
        if (this.nbrLignes != mat.nbrLignes || this.nbrColonnes != mat.nbrColonnes)
            throw new MatriceException("Les matrice n'ont pas la même taille.");
        somme = new Matrice(nbrLignes,nbrColonnes);
        for (int i = 0; i < nbrLignes; i++) {
            for (int j = 0; j < nbrColonnes; j++) {
                somme.elementMatrice[j][i] = elementMatrice[j][i] + mat.elementMatrice[j][i];
            }
        }
        return somme;
    }

    @Override
    public Matrice multiplier(Matrice mat) throws MatriceException{
        Matrice produit = new Matrice(1,1);
        if (this.nbrColonnes == mat.nbrLignes)
            throw new MatriceException("Les matrices ne peuvent pas être multipliés");
        produit = new Matrice(this.nbrLignes, mat.nbrColonnes);
        for (int i = 0; i < this.nbrLignes; i++){
            for (int j = 0; j < mat.nbrColonnes; j++){
                float somme = 0;
                for (int k = 0; k < nbrColonnes; k++){
                    somme += this.elementMatrice[j][k]*mat.elementMatrice[k][i];
                }
                produit.elementMatrice[j][i] = somme;
            }
        }
        return produit;
    }

    @Override
    public Matrice multiplierScalaire(int facteur) {
        Matrice out = this;
        for (int i = 0; i < this.nbrLignes; i++){
            for (int j = 0; j < this.nbrColonnes; j++) {
                this.elementMatrice[j][i] *= facteur;
            }
        }
        return out;
    }

    @Override
    public double moyenne() {
        double moyenne = 0;
        for (int i = 0; i < this.getNbrLignes(); i++){
            for (int j = 0; j < this.getNbrColonnes(); j++)
                moyenne += this.elementMatrice[j][i];
        }
        moyenne *= 1/(this.getNbrColonnes() * this.getNbrLignes());
        return moyenne;
    }

    @Override
    public double ecartType() {
        double ecartType = 0;

        double m = this.moyenne();
        int M = this.nbrColonnes;
        int N = this.nbrLignes;

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++)
                ecartType += Math.pow(this.elementMatrice[j][i] - m, 2);
        }
        ecartType*= 1/(N*M);
        ecartType = Math.sqrt(ecartType);

        return ecartType;
    }

    @Override
    public double norme() {
        double norme = 0;

        double m = this.moyenne();
        int M = this.nbrColonnes;
        int N = this.nbrLignes;

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++)
                norme += Math.pow(this.elementMatrice[j][i] - m, 2);
        }
        norme = Math.sqrt(norme);

        return norme;
    }

    @Override
    public Matrice transposer() {
        Matrice mt = new Matrice(this.nbrColonnes, this.nbrLignes);
        for (int i = 0; i < this.nbrLignes; i++){
            for (int j = 0; j < this.nbrColonnes; j++){
                mt.elementMatrice[j][i] = elementMatrice[i][j];
            }
        }
        return mt;
    }
}
