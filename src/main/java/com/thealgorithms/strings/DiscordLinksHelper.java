public class DiscordLinksHelper {

    public static final String DISCORD_URL = "https://discord.com/";
    private static final String CHANNELS_SEGMENT = "channels/" ;

    public static String createChannelLink(String guildID, String channelID) {
        return DISCORD_URL + CHANNELS_SEGMENT + guildID + "/" + channelID;
    }

    public static String createChannelLink(long guildID, long channelID) {
        return DISCORD_URL + CHANNELS_SEGMENT + guildID + "/" + channelID;
    }

    public static String createMessageLink(String guildID, String channelID, String messageID) {
        return DISCORD_URL + CHANNELS_SEGMENT + guildID + "/" + channelID + "/" + messageID;
    }

    public static String createMessageLink(long guildID, long channelID, long messageID) {
        return DISCORD_URL + CHANNELS_SEGMENT + guildID + "/" + channelID + "/" + messageID;
    }
}
