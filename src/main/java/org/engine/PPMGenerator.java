package org.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PPMGenerator {

    public void generate(int img_width,int img_height,String img_name) throws IOException {
        File outputImage = new File("./src/main/java/org/engine/RenderedImagesOutput/"+img_name);
        System.out.println(outputImage.createNewFile());
        try (FileWriter fileWriter = new FileWriter(outputImage,true)){
            fileWriter.write("P3\n"+img_width+" "+img_height+"\n255\n");
            for (int i=0 ; i < img_height ; i++ ){
                for (int j = 0; j < img_width; j++) {

                    double r = (double) j /(img_width-1) ,g= (double) i/(img_height-1),b = 0.0;

                    int scaled_r = (int) (255.999 * r),scaled_g = (int) (255.999*g),scaled_b = (int)(255.999*b);
                    String outputRow = scaled_r+" "+scaled_g+" "+scaled_b+"\n";
                    fileWriter.write(outputRow);

                }
            }


        }




    }
}
