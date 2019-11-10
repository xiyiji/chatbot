import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.function.DoubleToIntFunction;
import java.util.zip.ZipEntry;

import javax.imageio.ImageIO;

/**
 * implement the ImageModel Interface.
 */
public class ImageModelImpl implements ImageModel {
  private BufferedImage resultImg;

  public ImageModelImpl(){
    resultImg=new BufferedImage(800,600,BufferedImage.TYPE_3BYTE_BGR);
  }

  @Override
  public void blur(BufferedImage origin){
    float [] blurArr={1f/16,1f/8,1f/16,1f/8,1f/4,1f/8,1f/16,1f/8,1f/16};
    Kernel blurKernel=new Kernel(3,3,blurArr);
    ConvolveOp op=new ConvolveOp(blurKernel);
    this.resultImg=op.filter(origin,null);
  }

  @Override
  public void sharpen(BufferedImage origin){
    float [] sharpenArr={-1f/8,-1f/8,-1f/8,-1f/8,-1f/8,
            -1f/8,1f/4,1f/4,1f/4,-1f/8,
            -1f/8,1f/4,1f,1f/4,-1f/8,
            -1f/8,1f/4,1f/4,1f/4,-1f/8,
            -1f/8,-1f/8,-1f/8,-1f/8,-1f/8};
    Kernel sharpenKernel=new Kernel(5,5,sharpenArr);
    ConvolveOp op=new ConvolveOp(sharpenKernel);
    this.resultImg=op.filter(origin,null);
  }

  @Override
  public void rainbow(String direction,int width,int high,int stripBreadth){
    Color[] rainbow={new Color(  255, 0,   0),
            new Color(255, 165,   0),
            new Color(255,   255,   0),
            new Color(0,   255, 0),
            new Color(  0,   127, 255),
            new Color(  0, 0, 255),
            new Color(  139, 0,   255)};
      if(direction.equals(("horizontal"))){
        this.resultImg=new BufferedImage(width,high,BufferedImage.TYPE_3BYTE_BGR);
        for(int x=0;x<width;x++)
          for(int y=0;y<high;y++){
            int i=(int)(y/stripBreadth)%7;
            this.resultImg.setRGB(x,y,rainbow[i].getRGB());
          }
      } else {
      this.resultImg=new BufferedImage(width,high,BufferedImage.TYPE_3BYTE_BGR);
      for(int x=0;x<width;x++)
        for(int y=0;y<high;y++){
          int i=(int)(x/stripBreadth)%7;
          this.resultImg.setRGB(x,y,rainbow[i].getRGB());
        }
    }
  }

  @Override
  public void greyscale(BufferedImage origin, String format) {
    try {
      int height,width;
      height=origin.getHeight();
      width=origin.getWidth();
      int red,blue,green,avgRGB,newRGB;
      Color pixelColor,grayScale;

      for(int y=0;y<height;y++) {
        for(int x=0;x<width;x++) {
          pixelColor=new Color(origin.getRGB(x,y));
          red=pixelColor.getRed();
          blue=pixelColor.getBlue();
          green=pixelColor.getGreen();
          newRGB=(int)(0.2126*red)+(int)(0.0722*blue)+(int)(0.7152*green);
          avgRGB=(red+blue+green)/3;
          grayScale=new Color(newRGB,newRGB,newRGB);
          origin.setRGB(x,y,grayScale.getRGB());
        }
      }
      File outputFile=new File("newGrayScale.jpg");
      System.out.println(format);
      ImageIO.write(origin,format,outputFile);
    }catch(IOException ioException)
    {
      System.out.println(ioException.getMessage());
    }
  }

//  @Override
//  public void sepia(BufferedImage origin) {
//    try {
//      int height,width;
//      height=origin.getHeight();
//      width=origin.getWidth();
//      int red,blue,green,avgRGB,newRGB;
//      int newRed, newBlue, newGreen;
//      Color pixelColor,grayScale;
//
//      for(int y=0;y<height;y++) {
//        for(int x=0;x<width;x++) {
//          pixelColor=new Color(origin.getRGB(x,y));
//          red=pixelColor.getRed();
//          blue=pixelColor.getBlue();
//          green=pixelColor.getGreen();
//          newRed = (int)(0.393*red)+(int)(0.769*blue)+(int)(0.189*green);
//          newGreen =
//                  (int)(0.349*red)+(int)(0.686*blue)+(int)(0.168*green);
//          newBlue =
//                  (int)(0.272*red)+(int)(0.534*blue)+(int)(0.131*green) ;
//         // avgRGB=(red+blue+green)/3;
//          grayScale=new Color(newRed,newGreen,newBlue);
//          origin.setRGB(x,y,grayScale.getRGB());
//        }
//      }
//      File outputFile=new File("newSepiaStone.jpg");
//      ImageIO.write(origin,"jpg",outputFile);
//    }catch(IOException ioException)
//    {
//      System.out.println(ioException.getMessage());
//    }
//  }

  @Override
  public void checkerBoard(int size){
    int [] checkerArr={0xffffff,0x000000};
    int width=this.resultImg.getWidth();
    int high=this.resultImg.getHeight();
    for(int x=0;x<width;x++)
      for(int y=0;y<high;y++){
        int judge=((int)(x/size)+(int)(y/size))%2;
        this.resultImg.setRGB(x,y,checkerArr[judge]);
      }
  }

  @Override
  public BufferedImage getResult(){
    return this.resultImg;
  }

}
