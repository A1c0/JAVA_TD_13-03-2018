public class TestMatrice {
    public static void main(String[] args) {
        /*Matrice m1 = new Matrice(2, 2, new float[][]{{1, 0}, {-1, 3}});
        Matrice m2 = new Matrice(2,2, new float[][]{{3,1}, {2,1}});
        System.out.println(m1);
        System.out.println(m2);
        Matrice test0 = m1.additionner(m2);
        System.out.println(test0);
        Matrice test = m1.multiplier(m2);
        System.out.println(test);
        test = test.multiplierScalaire(2);
        System.out.println(test);
        Matrice m3 = new Matrice(2,3, new float[][]{{3,1,7}, {2,8,4}});
        System.out.println(m3);
        Matrice m3t = m3.transposer();
        System.out.println(m3t);*/


        Matrice m4 = new Matrice(2, 2, new float[][]{{1, 2}, {3, 4}});
        System.out.println(m4);
        System.out.println(new Matrice(2, 2, new float[][]{{1, 2}, {3, 4}}){
            @Override
            public String toString() {
                String text = super.toString() + "\n";
                Matrice.Noyau n1 = this.new Noyau(2, 2, new float[][]{{1, 2}, {6, 3}});
                text += n1.toString();
                return text;
            }
        });
    }

}
