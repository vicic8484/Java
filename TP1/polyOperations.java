package TP1;

public class polyOperations {
    public poly[] saisieDeMasse(int nombreASaisir){
        poly[] polys = new poly[nombreASaisir];
        for(int i = 0; i < nombreASaisir; i++){
            polys[i] = new poly();
        }
        return polys;
    }

    public poly addPoly(poly poly1, poly poly2){
        double[] coefs;
        if(poly1.coefs.length >= poly2.coefs.length){
            coefs = new double[poly1.coefs.length];
            for (int i = 0; i < poly1.coefs.length; i++) {
                if(i < poly2.coefs.length){
                    coefs[i] = poly1.coefs[i] + poly2.coefs[i];
                }
                else{
                    coefs[i] = poly1.coefs[i];
                }
            }
        }
        else{
            coefs = new double[poly2.coefs.length];
            for (int i = 0; i < poly2.coefs.length; i++) {
                if(i < poly1.coefs.length){
                    coefs[i] = poly1.coefs[i] + poly2.coefs[i];
                }
                else{
                    coefs[i] = poly2.coefs[i];
                }
            }
        }
        return new poly(coefs);
    }

    public poly sommeTableauP(poly[] polys){
        poly res = new poly(polys[0].coefs);
        for(int i = 1; i < polys.length; i++){
            res = res.addPolyA(polys[i]);
        }
        return res;
    }

    public poly produitTableauP(poly[] polys){
        poly res = new poly(polys[0].coefs);
        for(int i = 1; i < polys.length; i++){
            res = res.produitPoly(polys[i]);
        }
        return res;
    }

    public poly[] produitTableauExt(poly[] poly1s, double facteur){
        poly[] res = new poly[poly1s.length];
        for(int i = 0; i < poly1s.length; i++){
            res[i] = poly1s[i].produitExt(facteur);
        }
        return res;
    }

    public poly produitPolys(poly poly1, poly poly2){
        double[] coefs = new double[poly1.coefs.length + poly2.coefs.length - 1];
        for(int i = 0; i < poly1.coefs.length; i++){
            for(int j = 0; j < poly2.coefs.length; j++){
                coefs[i+j] += poly1.coefs[i] * poly2.coefs[j];
            }
        }
        return new poly(coefs);
    }
    
}
