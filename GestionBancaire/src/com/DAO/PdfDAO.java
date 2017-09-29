/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Model.Retrait;
import com.Model.Versement;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Djazz jah
 */
public class PdfDAO {

    private static String FILE = "D:\\";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    Calendar Cal = new GregorianCalendar();
    int year = Cal.get(Calendar.YEAR);
    int month = Cal.get(Calendar.MONTH);
    int day = Cal.get(Calendar.DAY_OF_MONTH);
    Date dt = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String dts = sdf.format(dt);
    public PdfDAO() {
    }

    public void pdf(Integer id,String nomfichier) {

        try {
//nom+"_"+prenom+day+"/"+(month + 1)+"/"+year+
            Document document = new Document(PageSize.A3);
            document.setMargins(20, 20, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(FILE +nomfichier+dts+".pdf"));
            document.open();
            //entete(document, id, nom, prenom, idchambre, date, montant);
            entete(document,id);
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void addMetaData(Document document) {
        document.addTitle("Version imprimer document");
        document.addSubject("Note");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Maoris");
        document.addCreator("Maoris");
    }

    public void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // On ajoute un ligne vide
        addEmptyLine(preface, 1);
        // Grand titre
        preface.add(new Paragraph("Listes", catFont));

        addEmptyLine(preface, 1);
        // Start a new page
        document.newPage();
    }

    public void addContent(Document document) throws DocumentException {

        // Pour creer le tableau
        createTable(document);

    }

    public void entete(Document document,Integer id) throws DocumentException {
        try {
            document.add(new Paragraph("                                                                                                                                         "));
            document.add(new Paragraph("                                        "
                    + "             GESTION BANCAIRE ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 23, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("                                                                                                                                         "));
            document.add(new Paragraph("                                                                                "
                    + "    Fianarantsoa le : " + day + "/" + (month + 1) + "/" + year, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("                                                                                                                                         "));
            float[] taille = new float[]{100, 100, 100, 100};//,70,90,70,70,90,90,100,70,70,90,90};
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(taille, PageSize.A3);

            PdfPCell c1 = new PdfPCell(new Phrase(" N° compte ", subFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(" Versement ", subFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(" Retrait ", subFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("  Date    ", subFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(c1);
            
            RetraitDAO dao_retrait = new RetraitImpl();
            VersementDAO dao_versement = new VersementImpl();
            
            List<Retrait> retraits = new ArrayList<>();
            retraits = dao_retrait.getSearch(id);
            for (Retrait object : retraits) {                                
                table.addCell(new Phrase(String.valueOf(object.getIdRetrait()), subFont));
                table.addCell(new Phrase("", subFont));
                table.addCell(new Phrase(String.valueOf(Long.valueOf(object.getMontant())), subFont));
                table.addCell(new Phrase(String.valueOf(object.getDate()), subFont));               
            }
            List<Versement> versements = new ArrayList<>();
            versements = dao_versement.getsearch(id);
            for (Versement object : versements) {                                
                table.addCell(new Phrase(String.valueOf(object.getIdVersement()), subFont));
                table.addCell(new Phrase(String.valueOf(Long.valueOf(object.getMontant())), subFont));
                table.addCell(new Phrase("", subFont));
                table.addCell(new Phrase(String.valueOf(object.getDate()), subFont));          
            }
            document.add(table);
            JOptionPane.showMessageDialog(null, "Export avec succes" );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur" + e.getMessage());
        }
    }

    public void createTable(Document document) throws DocumentException {
        /*float[] taille=new float[]{100,100,100,100};//,70,90,70,70,90,90,100,70,70,90,90};
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(taille,PageSize.A3);
        
        PdfPCell c1 = new PdfPCell(new Phrase(" MATIERES ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase(" COEFFICIENTS ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase(" NOTES DEFINITIVES ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("  NOTESMAX   ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("  APPRECIATION DES PROFS    ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("  SIGNATURES  ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("   Date avoir   ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        
        c1 = new PdfPCell(new Phrase("  Date ouverture  ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("  Date releve  ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("  Agence  ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("  Rib  ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("  Montant bloqué  ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("  Montant certifié ",subFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);*/
        //for(int i=0; i<10; i++)
          /* int i=0;
        try
        {
        ResultSet res=this.selectNote();
        while(res.next())
        {
        
        
        table.addCell(new Phrase(res.getString("matiere"),subFont));
        table.addCell(new Phrase(res.getString("coefficient"),subFont));
        table.addCell(new Phrase(res.getString(3),subFont));
        table.addCell(new Phrase(res.getString(4),subFont));
        table.addCell(new Phrase(res.getString(5),subFont));
        table.addCell(new Phrase(res.getString(6),subFont));
        table.addCell(new Phrase(res.getString(4),subFont));
        table.addCell(new Phrase(res.getString(4),subFont));
        
        table.addCell(new Phrase(res.getString(4),subFont));
        table.addCell(new Phrase(res.getString(4),subFont));
        table.addCell(new Phrase(res.getString(4),subFont));
        table.addCell(new Phrase(res.getString(4),subFont));
        table.addCell(new Phrase(res.getString(4),subFont));
        i++;
        
        }
        }
        catch(Exception ex)
        {
        ex.printStackTrace();
        }
        
        
        Paragraph Para = new Paragraph();
        Para.setAlignment(Element.ALIGN_CENTER);
        addEmptyLine(Para,2);
        document.add(Para);
        document.add(table);
         */
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
