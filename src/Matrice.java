public class Matrice implements OperationArithmetique {
    private int nbrLignes;
    private int nbrColonnes;
    private float[][] elementMatrice;

    public Matrice(int lignes, int colonnes) {
        this.nbrLignes = lignes;
        this.nbrColonnes = colonnes;
        elementMatrice = new float[lignes][colonnes];
    }

    public Matrice(int lignes, int colonnes, float[][] reels) {
        this(lignes, colonnes);
        for (int j = 0; j < lignes; j++){
            for (int i = 0; i < colonnes; i++){
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

    public void identifie(){
        if (nbrLignes == nbrColonnes){
            for (int i = 0; i < nbrLignes; i++)
                elementMatrice[i][i] = 1;
        }
    }

    @Override
    public Matrice additionner(Matrice mat) {
        Matrice somme = new Matrice(1,1);
        if (this.nbrLignes == mat.nbrLignes && this.nbrColonnes == mat.nbrColonnes){
            somme = new Matrice(nbrLignes,nbrColonnes);
            for (int i = 0; i < nbrLignes; i++) {
                for (int j = 0; j < nbrColonnes; j++) {
                    somme.elementMatrice[j][i] = elementMatrice[j][i] + mat.elementMatrice[j][i];
                }
            }
        }
        else{
            System.out.println("Les matrices n'ont pas la même taille ");
        }
        return somme;
    }

    @Override
    public Matrice multiplier(Matrice mat) {
        Matrice produit = new Matrice(1,1);
        if (this.nbrColonnes == mat.nbrLignes){
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
        }
        return produit;
    }

    @Override
    public Matrice multiplierScalaire(int facteur) {
        Matrice out = new Matrice(1,1);
        return out;
    }

    //MAIN DE TEST
    public static void main(String[] args) {
        Matrice m1 = new Matrice(2, 2, new float[][]{{1, 0}, {-1, 3}});
        Matrice m2 = new Matrice(2,2, new float[][]{{3,1}, {2,1}});
        System.out.println(m1);
        System.out.println(m2);
        Matrice test0 = m1.additionner(m2);
        System.out.println(test0);
        Matrice test = m1.multiplier(m2);
        System.out.println(test);
    }
}
