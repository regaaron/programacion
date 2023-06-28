import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class shader {
    public static void main(String[] args) {
        try {
            // Cargar la imagen
            BufferedImage image = ImageIO.read(new File("ConeHeadZombie.png"));

            // Obtener las dimensiones de la imagen
            int width = image.getWidth();
            int height = image.getHeight();

            // Iterar sobre cada píxel de la imagen
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Obtener el color original del píxel
                    Color color = new Color(image.getRGB(x, y), true);

                    // Obtener el nivel de opacidad original
                    int alpha = color.getAlpha();

                    // Aplicar el efecto de congelación: reducir la opacidad y agregar un tinte azul
                    int red = color.getRed() / 3;
                    int green = color.getGreen() / 3;
                    int blue = color.getBlue();
                    Color nuevoColor = new Color(red, green, blue, alpha );

                    // Establecer el nuevo color en el píxel de la imagen
                    image.setRGB(x, y, nuevoColor.getRGB());
                }
            }

            // Guardar la imagen con el efecto de congelación
            ImageIO.write(image, "png", new File("ConeHeadZombie_Frozen.png"));

            System.out.println("Efecto de congelación aplicado exitosamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
