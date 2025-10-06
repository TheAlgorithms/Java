import java.util.*;

class Twitter {
    private static int timeStamp = 0;
    private static class Tweet {
        int id;
        int time;
        Tweet next;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    
    private Map<Integer, Set<Integer>> followMap;
   
    private Map<Integer, Tweet> tweetMap;

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    /** User posts a new tweet */
    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId, timeStamp++);
        if (tweetMap.containsKey(userId)) {
            newTweet.next = tweetMap.get(userId);
        }
        tweetMap.put(userId, newTweet);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        // Min-heap to get most recent tweets first
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.time - a.time);

        // Add own tweets
        if (tweetMap.containsKey(userId)) {
            pq.offer(tweetMap.get(userId));
        }

        // Add followees' tweets
        if (followMap.containsKey(userId)) {
            for (int followee : followMap.get(userId)) {
                if (tweetMap.containsKey(followee)) {
                    pq.offer(tweetMap.get(followee));
                }
            }
        }

        // Retrieve top 10 tweets
        while (!pq.isEmpty() && result.size() < 10) {
            Tweet t = pq.poll();
            result.add(t.id);
            if (t.next != null) pq.offer(t.next);
        }
        return result;
    }


    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return; // can’t follow self
        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee */
    public void unfollow(int followerId, int followeeId) {
        if (!followMap.containsKey(followerId)) return;
        followMap.get(followerId).remove(followeeId);
    }
}
