package sherpa.bot;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 DiscordClient client = new DiscordClientBuilder("NjQwNjI4MzEyNDM1MDY0ODMy.Xb8l9A.yvh06thUdOp5DNRy7PfGf-8_G2c").build(); // args[0] being the token.
    	   // This makes the client to actually login

    	 client.getEventDispatcher().on(ReadyEvent.class)
         .subscribe(event -> {
           User self = event.getSelf();
           System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
         });

     client.getEventDispatcher().on(MessageCreateEvent.class)
         .map(MessageCreateEvent::getMessage)
         .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
         .filter(message -> message.getContent().orElse("").equalsIgnoreCase("!ping"))
         .flatMap(Message::getChannel)
         .flatMap(channel -> channel.createMessage("Pong!"))
         .subscribe();


     client.login().block();
    }
}
