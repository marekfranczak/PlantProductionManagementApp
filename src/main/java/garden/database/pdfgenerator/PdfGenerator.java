package garden.database.pdfgenerator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import garden.database.entity.Flowers;
import garden.database.entity.Passports;
import garden.database.entity.Shops;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PdfGenerator {

    private Passports passport;
    private Set<Flowers> flowers;
    private Shops shop;

    public PdfGenerator(Passports passport, Shops shop, Set<Flowers> flowers){
        this.passport = passport;
        this.flowers = flowers;
        this.shop = shop;
    }

    public void generatePassport(){
        try {
            Document document = new Document();
            int passportID = passport.getId();
            List<Integer> flowerIdList = new ArrayList<>();

            String fileName = shop.getName()+"_"+passport.getData();


            PdfWriter.getInstance(document, new FileOutputStream("..\\"+fileName+".pdf"));
            document.open();
            PdfPTable pdfPTable = new PdfPTable(3);

            Font font = FontFactory.getFont(String.valueOf(Font.FontFamily.TIMES_ROMAN), BaseFont.IDENTITY_H, true);

            try {
                String classPath = PdfGenerator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                System.out.println("classPath: "+classPath);
                //BaseFont bf = BaseFont.createFont("C:\\Users\\X\\Moje\\Java\\Projekty\\GardenDataBase\\GardenDataBase\\arial.ttf", BaseFont.CP1250, BaseFont.EMBEDDED);
                BaseFont bf = BaseFont.createFont(".\\GardenDataBase\\arial.ttf", BaseFont.CP1250, BaseFont.EMBEDDED);
                font = new Font(bf, 8);
            }catch(IOException e){
                System.out.println("Font loading error.."+e.getMessage());
                e.printStackTrace();
            }


            for(Flowers flower : flowers){

                    PdfPTable upCell = new PdfPTable(2);

                    PdfPCell imageCell = new PdfPCell(new Paragraph());
                    try {
                        Image img = Image.getInstance(".\\GardenDataBase\\pobrane.png");
                        imageCell.addElement(img);
                        imageCell.setBorderColor(BaseColor.WHITE);
                        upCell.addCell(imageCell);
                    } catch (IOException e){
                        System.out.println("Image loading error.. "+e.getMessage());
                        e.printStackTrace();
                    }
                    PdfPCell textCell = new PdfPCell(new Paragraph());
                    Chunk chunk1 = new Chunk("Paszport ro\u015Blin/\nPlant Passport", font);
                    textCell.addElement(chunk1);
                    textCell.setBorderColor(BaseColor.WHITE);
                    upCell.addCell(textCell);


                    PdfPCell mainCell = new PdfPCell(new Paragraph());
                    mainCell.addElement(upCell);
                    mainCell.setBorderColor(BaseColor.WHITE);

                    PdfPCell downCell = new PdfPCell(new Paragraph());
                    Chunk dataText = new Chunk("A. "+flower.getNameLA()+"\nB. Numer gospodarstwa\nC. "+flower.getId()+"\nD. PL", font);
                    downCell.addElement(dataText);
                    downCell.setBorderColor(BaseColor.WHITE);
                    PdfPTable pt = new PdfPTable(1);
                    pt.addCell(mainCell);
                    pt.addCell(downCell);

                    pdfPTable.addCell(pt);
                System.out.println("Add flower: "+flower.getNamePL());
                }

            pdfPTable.completeRow();
            if(document.add(pdfPTable))
                System.out.println("Pdf add table");
            System.out.println("document.close()");
            document.close();
        }catch (FileNotFoundException | DocumentException e){
            System.out.println(e.getMessage());
        }
    }
}
