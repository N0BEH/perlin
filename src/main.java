import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {

        //max 12288

        int largeur = 12288;
        int hauteur = 12288;

        generateur bruit = new generateur(hauteur, largeur);

        //arrayToPng atp = new arrayToPng(bruit.getHauteur(), bruit.getLargeur(), bruit.getNoise(), "test");

        /*YX*/

        //int[][] tt = bruit.zoom(bruit.getNoise());

        //System.out.println(bruit.getNoise()[0][0] + "," + bruit.getNoise()[0][1] + "," + bruit.getNoise()[0][2] + "," + bruit.getNoise()[0][3]);
        //System.out.println(bruit.getNoise()[1][0] + "," + bruit.getNoise()[1][1] + "," + bruit.getNoise()[1][2] + "," + bruit.getNoise()[1][3]);
        //System.out.println(bruit.getNoise()[2][0] + "," + bruit.getNoise()[2][1] + "," + bruit.getNoise()[2][2]);
        //System.out.println(bruit.getNoise()[0][(bruit.getLargeur()/2)]);

        /*
        for(int i = 0; i < bruit.getHauteur(); i++)
        {
            for(int j = 0; j < bruit.getLargeur(); j++)
            {
                System.out.print(tt[i][j] + "|");
            }
            System.out.println("");
        }*/

        bruit.superposition();



    }
}
