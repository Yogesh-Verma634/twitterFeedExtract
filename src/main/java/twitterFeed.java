/**
 * Created by z00295r on 2/10/17.
 */

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class twitterFeed {

    public static void main(String[] args) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();



        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter  =  tf.getInstance();

        //get username, status

       List<Status> status = twitter.getHomeTimeline();
        System.out.println(status.size());
        for(Status st : status){
            System.out.println(st.getUser().getName()+ "-------" + st.getText());
        }

        twitter4j.Twitter twitterTimeline =  TwitterFactory.getSingleton();
        Query query = new Query("#target");
        QueryResult result = twitterTimeline.search(query);
        System.out.println(result.getCount());

        for (Status statuses : result.getTweets()) {
            System.out.println("@" + statuses.getUser().getScreenName() + " : " + statuses.getText() + " : " + statuses.getGeoLocation());
        }


    }
}
