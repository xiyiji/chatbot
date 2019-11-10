import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class ImageController {
  public static void main(String argv[]) {
    Scanner scan = new Scanner(System.in);
    ImageModel model = new ImageModelImpl();
    while (scan.hasNext()) {
      switch (scan.next()) {
        case "q":
        case "quit":
          return;
        case "save":
          System.out.println("please input the file name:");
          String filename = scan.next();
          String imageformat = filename.substring(filename.indexOf(".") + 1);
          try {
            ImageIO.write(model.getResult(), imageformat, new File(filename));
          } catch (IOException e) {
            System.out.println("file cannot be save.");
          }
          break;

        case "blur":
          System.out.println("please input the image name to blur");
          String originFileName = scan.next();
          File initialImage = new File(originFileName);
          BufferedImage originalImage;
          try {
            originalImage = ImageIO.read(initialImage);
          } catch (IOException e) {
            System.out.println("Image cannot be read");
            break;
          }
          model.blur(originalImage);
          break;

        case "sharpen":
          System.out.println("please input the image name to sharpen");
          String originSharpenFileName = scan.next();
          File initialSharpenImage = new File(originSharpenFileName);
          BufferedImage originalSharpenImage;
          try {
            originalSharpenImage = ImageIO.read(initialSharpenImage);
          } catch (IOException e) {
            System.out.println("Image cannot be read");
            break;
          }
          model.sharpen(originalSharpenImage);
          break;

        case "checkerboard":
          System.out.println("please input the checker size:");
          int size;
          try {
            size = scan.nextInt();
          } catch (InputMismatchException e) {
            System.out.println("checker size is wrong");
            break;
          }
          model.checkerBoard(size);
          break;
        case "rainbow":
          System.out.println("please input the direction of the checker:horizontal/vertical");
          String direction = scan.next();
          if (!(direction.equals("horizontal") || direction.equals("vertical"))) {
            System.out.println("direction not right");
            break;
          }
          int width, high, stripeBreadth;
          try {
            System.out.println("please input the width of the rainbow image");
            width = scan.nextInt();
            System.out.println("please input the height of the rainbow  image");
            high = scan.nextInt();
            System.out.println("please input the stripe breadth of the rainbow  image");
            stripeBreadth = scan.nextInt();
          } catch (InputMismatchException e) {
            System.out.println("rainbow image parameter not right.");
            break;
          }
          model.rainbow(direction, width, high, stripeBreadth);
          break;

        case "greyscale":
          System.out.println("please input the image name to grayscale");
          String originGrayFileName = scan.next();
          File initialGrayImage = new File(originGrayFileName);
          BufferedImage originalGrayImage;
          String format = originGrayFileName.substring(originGrayFileName.indexOf('.') + 1);
          try {
            originalGrayImage = ImageIO.read(initialGrayImage);
          } catch (IOException e) {
            System.out.println("Image cannot be read");
            break;
          }
          model.greyscale(originalGrayImage, format);
          break;

//        case "sepia":
//          System.out.println("please input the image name to sepia");
//          String originSepiaFileName = scan.next();
//          File initialSepiaImage = new File(originSepiaFileName);
//          BufferedImage originalSepiaImage;
//          try {
//            originalSepiaImage = ImageIO.read(initialSepiaImage);
//          } catch (IOException e) {
//            System.out.println("Image cannot be read");
//            break;
//          }
//          model.sepia(originalSepiaImage);
//          break;
        default:
          System.out.println("Command not right");
          break;
      }
    }
  }
}
