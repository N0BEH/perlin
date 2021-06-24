import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class generateur {

    private int hauteur;
    private int largeur;
    private int[][] noise;

    public generateur(int hauteur, int largeur)
    {
        this.hauteur = hauteur;
        this.largeur = largeur;

        this.noise = new int[this.hauteur][this.largeur];
        Random r = new Random();

        for(int i = 0; i < largeur; i++)
        {
            for(int j = 0; j < hauteur; j++)
            {
                int tmp = r.nextInt(256);
                noise[i][j] = tmp;
            }
        }
    }

    public void af(int value)
    {
        System.out.print("[" + value + "]");
    }

    public void af(int[] value, int size)
    {
        for(int t = 0; t < size; t++)
        {
            af(value[t]);
        }
    }

    public void af(double value)
    {
        System.out.println(value);
    }

    public void af(int[][] value, int size)
    {
        for(int i = 0; i < size; i++)
        {
            for(int y = 0; y < size; y++)
            {
                af(value[i][y]);
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }

    public int getHauteur()
    {
        return this.hauteur;
    }

    public int getLargeur()
    {
        return this.largeur;
    }

    public int[][] getNoise()
    {
        return this.noise;
    }

    public int[][] zoom(int[][] tab)
    {
        //int[][] tmptab = new int[(this.hauteur/2)+1][(this.largeur/2)+1];
        int[][] zoomed_tab = new int[this.hauteur][this.largeur];

        /* 1/4 + 1 de marge pour zoom */
        for(int[] ligne:zoomed_tab)
        {
            Arrays.fill(ligne,-1);
        }

        //af(tab, this.hauteur);

        int[][] new_numbers_x = new int[(this.largeur/2)][(this.hauteur/2)];
        int[][] new_numbers_y = new int[(this.largeur/2)][(this.hauteur/2)];


        for(int y = 0; y < (this.hauteur/2); y++)
        {
            for(int x = 0; x < (this.largeur/2); x++)
            {
                new_numbers_x[y][x] = (tab[y][x] + tab[y][x+1])/2;
                new_numbers_y[y][x] = (tab[y][x] + tab[y+1][x])/2;
            }
        }

        //af(new_numbers_x, this.hauteur/2);
        //af(new_numbers_y, this.hauteur/2);

        int h = 0;
        for(int y = 0; y < this.hauteur; y+=2)
        {
            int l = 0;
            for(int x = 1; x < this.largeur; x+=2)
            {
                if (x % 2 == 1)
                {
                    zoomed_tab[y][x] = new_numbers_x[h][l];
                    zoomed_tab[y+1][x-1] = new_numbers_y[h][l];
                }
                l++;
            }
            //af(tab[y-h][(this.largeur/2)]);
            h++;
        }

        int[] border_h = new int[(this.hauteur/2)+1];
        for(int i = 0; i < (this.hauteur/2)+1; i++)
        {
            border_h[i] = tab[i][(this.hauteur/2)];
        }
        //System.out.print("bh ");
        //af(border_h, (this.hauteur/2)+1);
        //System.out.println(" ");

        int[] border_l = new int[(this.largeur/2)+1];
        for(int i = 1; i < (this.largeur/2)+2; i++)
        {
            border_l[i-1] = tab[(this.largeur/2)][i-1];
        }
        //System.out.print("bl ");
        //af(border_l, (this.largeur/2)+1);
        //System.out.println(" ");
        
        int cc = 0;
        for(int y = 0; y < this.hauteur; y+=2)
        {
            int counter = 0;
            for(int x = 0; x < this.largeur/2; x++)
            {
                zoomed_tab[y][x+counter] = tab[y-cc][x];
                counter++;
            }
            cc++;
        }

        int ligne = 0;
        int colonne = 0;

        for(int y = 1; y < this.hauteur; y+=2)
        {
            for(int x = 1; x < this.largeur; x+=2)
            {
                if(zoomed_tab[y][x] == -1 && x < this.largeur-1 && y < this.hauteur-1)
                {
                    zoomed_tab[y][x] = (zoomed_tab[y-1][x] + zoomed_tab[y+1][x] + zoomed_tab[y][x-1] + zoomed_tab[y][x+1])/4;
                }
                else if(zoomed_tab[y][x] == -1 && x < this.largeur-1 && y < this.hauteur)
                {
                    //Ligne
                    //af((border_l[ligne] + border_l[ligne+1])/2);
                    zoomed_tab[y][x] = (zoomed_tab[y-1][x] + zoomed_tab[y][x-1] + zoomed_tab[y][x+1] + (border_l[ligne] + border_l[ligne+1])/2 )/4;
                    ligne++;
                }
                else if(zoomed_tab[y][x] == -1 && x < this.largeur && y < this.hauteur-1)
                {
                    //af((border_h[colonne] + border_h[colonne+1])/2);
                    zoomed_tab[y][x] = (zoomed_tab[y-1][x] + zoomed_tab[y][x-1] + zoomed_tab[y+1][x] + (border_h[colonne] + border_h[colonne+1])/2 )/4;
                    colonne++;
                }
                else if(zoomed_tab[y][x] == -1 && x < this.largeur && y < this.hauteur)
                {
                    //af(border_h[this.hauteur/2]);
                    int right = (border_h[this.hauteur/2] + border_h[(this.hauteur/2)-1])/2;
                    int bottom = (border_l[this.hauteur/2] + border_l[(this.hauteur/2)-1])/2;
                    zoomed_tab[y][x] = (zoomed_tab[y][x-1] + zoomed_tab[y-1][x] + right + bottom)/4;
                }
            }
        }

        return zoomed_tab;
    }

    public void superposition() throws IOException {

        double[][] bruit_final = new double[this.getHauteur()][this.getLargeur()];
        int[][] tab = this.getNoise();
        double coef = 0.00390625;
        int nb = 7;

        for(int y = 0; y < this.hauteur; y++)
        {
            for(int x = 0; x < this.largeur; x++)
            {
                bruit_final[y][x] = this.getNoise()[y][x] * coef;
            }
        }

        for(int i = 0; i < nb; i++)
        {
            tab = this.zoom(tab);
            coef*=2;
            af(coef);

            for(int y = 0; y < this.getHauteur(); y++)
            {
                for(int x = 0; x < this.getLargeur(); x++)
                {
                    bruit_final[y][x] += tab[y][x]*coef;
                }
            }


        }

        arrayToPng aa = new arrayToPng(this.getHauteur(),this.getLargeur(), bruit_final, "Perlin");

    }


}
