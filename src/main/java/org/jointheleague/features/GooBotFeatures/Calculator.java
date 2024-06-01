package org.jointheleague.features.GooBotFeatures;

import org.javacord.api.event.message.MessageCreateEvent;
import org.jointheleague.features.abstract_classes.Feature;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;

import java.lang.Math;

public class Calculator extends Feature {

    public final String COMMAND = "!calculator";
    public static double answer;

    public Calculator(String channelName) {
        super(channelName);

        //Create a help embed to describe feature when !help command is sent
        helpEmbed = new HelpEmbed(COMMAND, "Give GooBot a number, an operation symbol, and" +
                "another number, and GooBot will perform the operation for you! This includes: +, x, /, " +
                "-, ^, %. Please have no spaces in the operation provided. Have fun! Eg: !Calculator 55,+,12");
    }

    @Override
    public void handle(MessageCreateEvent event) {
        String messageContent = event.getMessageContent();
        int num1;
        String op;
        int num2;
        //respond to message here
        if (messageContent.startsWith(COMMAND)) {
            int index1 = messageContent.indexOf(",");
            int index2 = messageContent.indexOf(",", 15);
            String sub1 = messageContent.substring(12, index1);
            String subOp2 = messageContent.substring(index1 + 1, index2);
            String sub2 = messageContent.substring(index2 + 1);
            num1 = Integer.parseInt(sub1);
            num2 = Integer.parseInt(sub2);
            answer = calculate(event, num1, num2, subOp2);
            event.getChannel().sendMessage("I got the answer! It is... " + answer);
        }
    }


    public static double calculate(MessageCreateEvent event, double num1, double num2, String subOp2) {
        answer = 0;
        switch (subOp2) {
            case "+":
                answer = num1 + num2;
                break;

            case "-":
                answer = num1 - num2;
                break;

            case "/":
                answer = (double) num1 / num2;
                break;

            case "x":
                answer = num1 * num2;
                break;

            case "^":
                answer = Math.pow(num1, num2);
                break;

            case "%":
                answer = num1 % num2;
                break;

            default:
                event.getChannel().sendMessage("Sorry, I couldn't understand your request." +
                        "Please try again.");
                break;
        }
        return answer;
    }
}

