import java.awt.image.BufferedImage;
import java.io.BufferedReader;

/**
 * This represents the model of image process.
 */
public interface ImageModel {
  /**
   * blur an input image.
   * @param origin the input image
   */
  void blur(BufferedImage origin);

  /**
   * sharpen an input image.
   * @param origin the input image
   */
  void sharpen(BufferedImage origin);

  /**
   * draw a checkboard image.
   * @param size the size of the checkerboard
   */
  void checkerBoard(int size);

  /**
   * generate the ranbow image as required.
   * @param direction vertical or horizontal ranbow strip
   * @param width the with of the image
   * @param high the hight of the image
   * @param stripBreadth the stripe breadth of the rainbow
   */
  void rainbow(String direction,int width,int high,int stripBreadth);


  /**
   * greyscale an input image
   * @param origin the input image
   * @param imageformat the format of the image
   */
  void greyscale(BufferedImage origin, String imageformat);

  /**
   * convert a normal color image into a sepia-toned image
   * @param origin the input image
   */
 // void sepia (BufferedImage origin);

  /**
   * get the result of the image processing.
   * @return the result image.
   */
  BufferedImage getResult();
}
