package strategies;

import cantstop.Jeu;
        
/**
 * Votre Stratégie (copie de la Strat0 pour l'instant)
 *
 * Expliquez votre stratégie en une 20aine de lignes maximum.
 *
 * RENDU: Ce fichier, correctement nommé et rempli.
 * Votre Stratégie aura un numéro (pour être similaire à Strat0 qui sera votre position dans l'alphabet de la promo + 14. 
 * (attention calcul compliqué) Le premier aura donc pour numéro 15 et le dernier 334
 * Pour "préparer" votre stratégie, sur le fichier StratX.java, vous cliquez sur Bouton Droit, Refactor, Rename et vous 
 * nommez bien votre stratégie genre Strat245.java (pour le 231e).
 *
 * @author Victor Berhault
 */

public class Strat2 implements Strategie {
    private int [][] lesChoix = new int[6][2];
    private int [][] bonzes = new int[3][2];
    private int [] maximum = {3,5,7,9,11,13,11,9,7,5,3};
    private int [] avancementAdversaire = new int[11];
    private int [] avancement = new int[11];
    private boolean [] bloque = new boolean[11];
    private int probaLimite = 95;
    private int scoreDouble = 100;
    private double scoreDistMax = 2.25;
    private int scoreDistAdv = 1;
    private double scoreBonzeExiste = 45;
    private int scoreFinishLine = 225;
    private int scoreProba = 1;
    private int score = 0;
    private int scoreAdv = 0;
    private int tries = 0;
    private double[] probas = {.1320463,.2332046,.3559846,.4478764,.5613900,.6440154,.5613900,.4478764,.3559846,.2332046,.1320463};

    /**
    * @param j le jeu
    * @return toujours le 1er choix
    */
   public int choix(Jeu j) {
    this.lesChoix = j.getLesChoix();
    this.bonzes = j.getBonzes();
    int bonzesRestants = j.getBonzesRestants();
    this.avancement = j.avancementJoueurEnCours();
    int choix = 0;
    double max = 0;
    double temp;
    boolean done1 = false;
    boolean done2 = false;

    for(int i = 0; i < j.getNbChoix(); i++){
        temp = 0;
        done1 = false;
        done2 = false;
        for(int k = 0; k<3; k++){
            if(bonzes[k][0] == lesChoix[i][0] && lesChoix[i][0] != 0){
                temp += Math.pow(probas[lesChoix[i][0]-2],maximum[lesChoix[i][0]-2]-bonzes[k][1])*2;
                done1 = true;
            }
            else if(bonzes[k][0] == lesChoix[i][1] && lesChoix[i][1] != 0){
                temp += Math.pow(probas[lesChoix[i][1]-2],maximum[lesChoix[i][1]-2]-bonzes[k][1])*2;
                done2 = true;
            }
        }
        if(!done1){
            temp += Math.pow(probas[lesChoix[i][0]-2],maximum[lesChoix[i][0]-2]-avancement[lesChoix[i][0]-2]);
        }
        if(!done2 && lesChoix[i][1] != 0){
            temp += Math.pow(probas[lesChoix[i][1]-2],maximum[lesChoix[i][1]-2]-avancement[lesChoix[i][1]-2]);
        }
        if(temp > max){
            max = temp;
            choix = i;
        }
    }
    return choix;
   }

   /**
    * @param j le jeu
    * @return toujours vrai (pour s'arrêter)
    */
   public boolean stop(Jeu j) {
        double proba = 1;
        this.score = j.scoreJoueurEnCours();
        this.scoreAdv = j.scoreAutreJoueur();
        this.bonzes = j.getBonzes();
        this.bloque =j.getBloque();
        boolean score1 = false;

        if(j.getBonzesRestants()>0){
            tries = 0;
            return false;
        }

        for(int i = 0;i<3; i++){
            if(bonzes[i][1] == maximum[bonzes[i][0]-2]){
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if(!bloque[bonzes[i][0]-2]){
                proba *= 1-probas[bonzes[i][0]-2];
            }
            
        }
        if(proba*100>probaLimite){
            return false;
        }


       return j.getNbCoup()<5*(1+scoreAdv-0.5*score);
   }


   /**
    * @return vos noms
    */
   public String getName() {
       return "New Victor Berhault";
   }
}
