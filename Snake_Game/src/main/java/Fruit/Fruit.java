package Fruit;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Fruit {
     Image image1;
     Image image2;
     Image image3;
     Image image4;
     Image image5;
     Image image6;
     Image image7;
     Image image8;
     public Fruit(){

     }
   public Image addFruit1() throws FileNotFoundException {
       image1 = new Image(new FileInputStream("src/main/java/Fruit/images/ic_apple.png"));
       return image1;
   }
   public Image addFruit2() throws FileNotFoundException{
       image2 = new Image(new FileInputStream("src/main/java/Fruit/images/ic_berry.png"));
     return  image2;}
    public Image addFruit3() throws FileNotFoundException{
       image3 = new Image(new FileInputStream("src/main/java/Fruit/images/ic_cherry.png"));
     return image3;}
    public Image addFruit4()throws FileNotFoundException{
       image4 = new Image(new FileInputStream("src/main/java/Fruit/images/ic_coconut_.png"));
     return image4;}
    public Image addFruit5()throws FileNotFoundException{
       image5 = new Image(new FileInputStream("src/main/java/Fruit/images/ic_orange.png"));
     return image5;}
    public Image addFruit6() throws FileNotFoundException{
       image6 = new Image(new FileInputStream("src/main/java/Fruit/images/ic_peach.png"));
     return  image6;}
    public  Image addFruit7() throws FileNotFoundException{
       image7 = new Image(new FileInputStream("src/main/java/Fruit/images/ic_pomegranate.png"));
     return image7;}
    public Image addFruit8()throws FileNotFoundException{
       image8 = new Image(new FileInputStream("src/main/java/Fruit/images/ic_watermelon.png"));
       return image8;

   }

}
