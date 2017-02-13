/**
 * Created by z00295r on 2/10/17.
 */

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class twitterFeed {

    public static void main(String[] args) throws TwitterException {
        int count = 0;
        int columnCount = 0;
        int rowCount = 0;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");



        TwitterFactory tf = new TwitterFactory(cb.build());
//        Twitter twitterHash = tf.getInstance();
        Twitter twitter = tf.getInstance();

//        Query query = new Query("#target");
        QueryResult result;
        Paging pg = new Paging();
        pg.setCount(200);
        String accountName = "@unity3d";
        List<Status> status = twitter.getUserTimeline(accountName, pg);
        System.out.println(status.size());
        for (Status st : status) {
            System.out.println(st.getUser().getName() + "-------" + st.getText());
        }
        for (Status tweet : status) {
            count++;
//                    for (Object aBook : feedData) {
            Row row = sheet.createRow(rowCount++);

//                    for(Object aBook : tweet){
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(tweet.getUser().getScreenName());
            Cell cell1 = row.createCell(columnCount);
            cell1.setCellValue(tweet.getText());
            columnCount = 0;
            System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
        }
        try{
            FileOutputStream outputStream = new FileOutputStream(new File("FilePath/JavaBooks.csv"));
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



