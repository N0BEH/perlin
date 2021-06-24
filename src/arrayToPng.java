import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class arrayToPng {

    private int hauteur;
    private int largeur;
    private double[][] tableau;

    public arrayToPng(int hauteur, int largeur, double[][] tableau, String nom) throws IOException {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.tableau = tableau;

        /* Déclaration des attributs pour l'image */
        BufferedImage imageStock = new BufferedImage(this.largeur, this.hauteur, BufferedImage.TYPE_INT_RGB);

        /* Attribution des valeurs du tableau à l'image stocké (buffer) */
        for(int i = 0; i < this.hauteur; i++)
        {
            for(int j = 0; j < this.largeur; j++)
            {
                int tmpValeur = (int)this.tableau[i][j];
                if(tmpValeur < 60)
                {
                    imageStock.setRGB(i,j, new Color(15,94,156,255).getRGB());
                }
                 else if(tmpValeur < 80 && tmpValeur >= 60)
                {
                    imageStock.setRGB(i,j, new Color(35,137,218,255).getRGB());
                }
                else if(tmpValeur < 100 && tmpValeur >= 80)
                {
                    imageStock.setRGB(i,j, new Color(28,163,236,255).getRGB());
                }
                else if(tmpValeur >= 100 && tmpValeur < 108)
                {
                    imageStock.setRGB(i,j, new Color(202,188,145,255).getRGB());
                }
                else if(tmpValeur >= 108 && tmpValeur < 150)
                {
                    imageStock.setRGB(i,j, new Color(96,128,56,255).getRGB());
                }
                else if(tmpValeur >= 150 && tmpValeur < 175)
                {
                    imageStock.setRGB(i,j, new Color(114,111,110,255).getRGB());
                }
                else if(tmpValeur >= 175 && tmpValeur < 200)
                {
                    imageStock.setRGB(i,j, new Color(183,181,181,255).getRGB());
                }
                else if(tmpValeur >= 200 && tmpValeur < 225)
                {
                    imageStock.setRGB(i,j, new Color(218,217,217,255).getRGB());
                }
                else
                {
                    imageStock.setRGB(i,j, new Color(tmpValeur,tmpValeur,tmpValeur,255).getRGB());
                }

            }
        }

        File file = new File(nom +".png");
        ImageIO.write(imageStock, "png", file);

    }

}
