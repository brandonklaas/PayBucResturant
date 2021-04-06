package core.pdf;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 
import core.general.Transaction;
import core.utilities.Session;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils; 

public final class TransactionReportPDF {
    
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
    
    private DateTimeFormatter simpleFileDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    private Font smallFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new CMYKColor(75, 68, 67, 90));
    private Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.NORMAL, new CMYKColor(75, 68, 67, 90));
    private Font blueFontAddress = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new CMYKColor(75, 68, 67, 90));
    private Font blueFontFilter = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new CMYKColor(75, 68, 67, 90));
    
    private Font smallBlackBold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
    private Font smallBlackPlain = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
    private Font HeaderFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.GRAY);
    private static DecimalFormat df2 = new DecimalFormat("0.00");
    private String timePath;
    private Session session;
    private ArrayList<Transaction> transactions;
    private boolean showAll = true;
    
    private double totalSum = 0.00;

    public TransactionReportPDF(String filterdate, ArrayList<Transaction> transactions, Session session, boolean showAll) {
        Document document = new Document();
        this.session = session;
        this.showAll = showAll;
        this.transactions = transactions;
        
        emptyFolders();
        String clinicLogo = "Bin\\logo.png";
        try {
            timePath = "Bin\\Print\\Transactions"+simpleFileDateFormat.format(LocalDateTime.now())+".pdf";
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(timePath));
            document.setMargins(36, 36, 10, 25);
            document.open();
            header(clinicLogo, document, filterdate);
            records(document);
            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void emptyFolders() {
        try {
            File directory = new File("Bin\\Print");
            FileUtils.cleanDirectory(directory);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void header(String clinicLogo, Document document, String filterdate) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

            File logo = new File("Bin\\logo.png");

            //====================================================
            PdfPTable table = new PdfPTable(3); // 3 columns.
            table.setWidthPercentage(105); //Width 100%
            table.setSpacingBefore(5f); //Space before table
            table.setSpacingAfter(10f); //Space after table
            //Set Column widths
            float[] columnWidths = {0.7f, 3f, 0.7f};
            table.setWidths(columnWidths);
            Paragraph par = new Paragraph(session.getBranch().getName()+" \n", blueFont);
//            Phrase filter = new Phrase(filterdate);
            Phrase filter = new Phrase(session.getBranch().getAddress(),blueFontAddress);
            
            par.add(filter);
            PdfPCell cell2 = new PdfPCell(par);
            cell2.setBorder(0);
            cell2.setBackgroundColor(new CMYKColor(13, 6, 6, 0));
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            //Add Image
            Image imageC = Image.getInstance(logo.getAbsolutePath());
            imageC.scaleAbsolute(80, 80);
            PdfPCell cell1 = new PdfPCell(imageC);
            cell1.setBorder(0);
            cell1.setBackgroundColor(new CMYKColor(13, 6, 6, 0));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            
            PdfPCell cell3 = new PdfPCell(new Paragraph());
            cell3.setBorder(0);
            cell3.setBackgroundColor(new CMYKColor(13, 6, 6, 0));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell empty = new PdfPCell();
            empty.setBorder(0);
            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Phrase p = new Phrase(dtf.format(now), HeaderFont);
            PdfPCell printDate = new PdfPCell(p);
            printDate.setBorder(0);
            //To avoid having the cell border and the content overlap, if you are having thick cell borders
            table.addCell(printDate);
            table.addCell(empty);
            table.addCell(empty);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            //===================================================
            
            if(showAll == false) {
                PdfPCell filterCell = new PdfPCell(new Paragraph(filterdate, blueFontFilter));
                filterCell.setBorder(0);
                filterCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                filterCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                table.addCell(empty);
                table.addCell(filterCell);
                table.addCell(empty);
            }
            //Add Image
            //Image image1 = Image.getInstance("temp.jpg");
            //Fixed Positioning
//            image1.setAbsolutePosition(100f, 550f);
            //Scale to new height and new width of image
//            image1.scaleAbsolute(200, 200);
            //Add to document
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void records(Document document) {
        try {
            //====================================================
            PdfPTable table = new PdfPTable(8); // 3 columns.
            table.setWidthPercentage(105); //Width 100%
            table.setSpacingBefore(5f); //Space before table
            table.setSpacingAfter(10f); //Space after table
            //Set Column widths
//            float[] columnWidths = {0.7f, 0.2f};
//            table.setWidths(columnWidths);

            PdfPCell h1 = new PdfPCell(new Paragraph("Date", smallBlackBold));
            h1.setBorder(0);
            h1.setHorizontalAlignment(Element.ALIGN_LEFT);
            h1.setBackgroundColor(new CMYKColor(13, 6, 6, 0));

            PdfPCell h2 = new PdfPCell(new Paragraph("Description", smallBlackBold));
            h2.setBorder(0);
            h2.setHorizontalAlignment(Element.ALIGN_CENTER);
            h2.setBackgroundColor(new CMYKColor(13, 6, 6, 0));

            PdfPCell h3 = new PdfPCell(new Paragraph("Type", smallBlackBold));
            h3.setBorder(0);
            h3.setHorizontalAlignment(Element.ALIGN_CENTER);
            h3.setBackgroundColor(new CMYKColor(13, 6, 6, 0));

            PdfPCell h4 = new PdfPCell(new Paragraph("Payment", smallBlackBold));
            h4.setBorder(0);
            h4.setHorizontalAlignment(Element.ALIGN_CENTER);
            h4.setBackgroundColor(new CMYKColor(13, 6, 6, 0));

            PdfPCell h5 = new PdfPCell(new Paragraph("Customer", smallBlackBold));
            h5.setBorder(0);
            h5.setHorizontalAlignment(Element.ALIGN_CENTER);
            h5.setBackgroundColor(new CMYKColor(13, 6, 6, 0));

            PdfPCell h6 = new PdfPCell(new Paragraph("Customer Cell", smallBlackBold));
            h6.setBorder(0);
            h6.setHorizontalAlignment(Element.ALIGN_CENTER);
            h6.setBackgroundColor(new CMYKColor(13, 6, 6, 0));

            PdfPCell h7 = new PdfPCell(new Paragraph("Employee", smallBlackBold));
            h7.setBorder(0);
            h7.setHorizontalAlignment(Element.ALIGN_CENTER);
            h7.setBackgroundColor(new CMYKColor(13, 6, 6, 0));

            PdfPCell h8 = new PdfPCell(new Paragraph("Price", smallBlackBold));
            h8.setBorder(0);
            h8.setHorizontalAlignment(Element.ALIGN_RIGHT);
            h8.setBackgroundColor(new CMYKColor(13, 6, 6, 0));

            table.addCell(h1);
            table.addCell(h2);
            table.addCell(h3);
            table.addCell(h4);
            table.addCell(h5);
            table.addCell(h6);
            table.addCell(h7);
            table.addCell(h8);
            
            for(Transaction trans : transactions) {
                PdfPCell cell = new PdfPCell(new Paragraph(new String(simpleDateFormat.format(trans.getDate())), smallBlackPlain));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setBorder(Rectangle.BOTTOM);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(trans.getTitle(), smallBlackPlain));
                cell.setBorder(Rectangle.BOTTOM);
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(trans.getType(), smallBlackPlain));
                cell.setBorder(Rectangle.BOTTOM);
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(trans.getPayment(), smallBlackPlain));
                cell.setBorder(Rectangle.BOTTOM);
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(trans.getCustomerName(), smallBlackPlain));
                cell.setBorder(Rectangle.BOTTOM);
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(trans.getCustomerNumber(), smallBlackPlain));
                cell.setBorder(Rectangle.BOTTOM);
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(trans.getEmployee(), smallBlackPlain));
                cell.setBorder(Rectangle.BOTTOM);
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("R " + df2.format(trans.getPrice()), smallBlackPlain));
                cell.setBorder(Rectangle.BOTTOM);
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                
                totalSum+=trans.getPrice();
            }

            
            PdfPCell empty = new PdfPCell(new Paragraph("", smallBlackBold));
            empty.setBorder(0);
            empty.setHorizontalAlignment(Element.ALIGN_CENTER);
            empty.setBackgroundColor(new CMYKColor(13, 6, 6, 0));
            
            PdfPCell totalLabel = new PdfPCell(new Paragraph("Total :", smallBlackBold));
            totalLabel.setBorder(0);
            totalLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalLabel.setVerticalAlignment(Element.ALIGN_CENTER);
            totalLabel.setBackgroundColor(new CMYKColor(13, 6, 6, 0));
            
            PdfPCell sum = new PdfPCell(new Paragraph("R "+ df2.format(totalSum), smallBlackPlain));
            sum.setBorder(0);
            sum.setHorizontalAlignment(Element.ALIGN_RIGHT);
            sum.setVerticalAlignment(Element.ALIGN_CENTER);
            sum.setBackgroundColor(new CMYKColor(13, 6, 6, 0));
            
            table.addCell(empty);
            table.addCell(empty);
            table.addCell(empty);
            table.addCell(empty);
            table.addCell(empty);
            table.addCell(empty);
            table.addCell(totalLabel);
            table.addCell(sum);
            
            document.add(table);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }

    public String getPath(){
        return timePath;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
