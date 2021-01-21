import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrendingHashTags {
	
	
	private static List<Tag> tags = new ArrayList<Tag>();
	private static Map<String, Integer> hashTags = new HashMap<String, Integer>();
	
	/**
	 * Method which extract the hashtags from the tweet and put it into the global map object.
	 * @param tweet
	 */
	private static void extractHashTagsFromTweet (String tweet ) {
		int counter= 0;
		
		if (tweet == null || tweet.length() <= 0) {
			return;
		}
		
		while (counter < tweet.length()) {
			
			if (tweet.charAt(counter) == '#') {
				String newTag = "";
				while (counter < tweet.length() && tweet.charAt(counter) != ' ') {
					newTag += tweet.charAt(counter);
					counter += 1;
				}
				if (hashTags.containsKey(newTag)) {
					hashTags.put(newTag, hashTags.get(newTag)+1);
				}
				else {
					hashTags.put(newTag, 1);
				}
			}
			else {
				counter += 1;
			}
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfTweets = Integer.parseInt(br.readLine());
		
		for(int i=0; i< numberOfTweets; i++) {
			String tweet = br.readLine();
			extractHashTagsFromTweet(tweet);
		}
		
		
		for(String key: hashTags.keySet() ) {
			Tag tag = new Tag(key, hashTags.get(key));
			tags.add(tag);
		}
		
		
		Collections.sort(tags, new Comparator<Tag>() {

			@Override
			public int compare(Tag a, Tag b) {
				if (a.tagCount.equals(b.tagCount)) {
					return a.tagValue.compareTo(b.tagValue);
				}
				else 
					return b.tagCount.compareTo(a.tagCount);
			}

			
		});
		
		int numberOfPrints = numberOfTweets >= 10 ? 10: numberOfTweets;
		for(int i = 0; i < numberOfPrints; i++){
			System.out.println(tags.get(i).tagValue);
		}
	}

}
