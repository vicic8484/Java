package TP1;

public class TP1 {
	public static void main(String[] args) {
        double[] coefs = {1.0,-3.0,1.0,0.0,0.0};
        double[] coefs2 = {1.0,2.0,3.0,4.0,5.0};
        poly p = new poly(coefs);
        polyOperations operateur = new polyOperations();

        //new poly();
        //new poly();

        System.out.println(p);
        System.out.println(p.Val(2));
        System.out.println(p.ValHornerRecursive(2));

        poly p2 = new poly(coefs2);
        System.out.println(operateur.addPoly(p,p2));
        System.out.println(p.addPolyA(p2));
        System.out.println(p.produitExt(4));
        System.out.println(p.derivePoly());
        System.out.println(p.primitivePoly()); 

        System.out.println(p.dicho(0, 1.5, 0.0001));
        System.out.println(p.newton(0, 0.0001));
        
        //poly[] polys = operateur.saisieDeMasse(2);
        /* for(int i = 0; i < polys.length; i++){
            System.out.println(polys[i]);
        }
        System.out.println(operateur.sommeTableauP(polys)); */

        //System.out.println(new polyCreux());

        //System.out.println(operateur.produitTableauP(polys));

	}



}

