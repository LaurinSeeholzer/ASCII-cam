import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

public class ASCIICam {
    public static void main(String args[])throws IOException{

        String characters = "MN@#$o;:,. ";
        int width = 20;
        int height = 20;
        BufferedImage image = null;
        File f = null;

        try {
            f = new File("sphere.jpg");
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(f);
            image = resizeImage(image, 20, 20);
            System.out.println("Reading complete.");
        } catch(IOException e) {
            System.out.println("Error: "+e);
        }

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
               int pixel = image.getRGB(x,y);
               Color color = new Color(pixel, true);
               int red = color.getRed();
               int green = color.getGreen();
               int blue = color.getBlue();
               double avg = (red + green + blue) / 3;
               int value = (int)(characters.length() * avg / 255);
               for (int i = 0; i<2; i++){
                System.out.print(characters.charAt(value-1));
               }
            }
            System.out.print("\n");
        }
    }

    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
}
