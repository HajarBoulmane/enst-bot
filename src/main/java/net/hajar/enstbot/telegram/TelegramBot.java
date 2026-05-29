package net.hajar.enstbot.telegram;

import jakarta.annotation.PostConstruct;
import net.hajar.enstbot.agent.AIAgent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.key}")
    private String telegramToken;

    private final AIAgent aiAgent;

    public TelegramBot(AIAgent aiAgent) {
        this.aiAgent = aiAgent;
    }

    @PostConstruct
    public void registerBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) return;

        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

        if (messageText == null) return;

        // Show "typing..." to user
        sendTypingAction(chatId);

        // Ask AI agent
        String response = aiAgent.askAgent(messageText);

        // Send response back
        sendTextMessage(chatId, response);
    }

    @Override
    public String getBotUsername() {
        return "enst_bot"; // replace with YOUR bot username from BotFather
    }

    @Override
    public String getBotToken() {
        return telegramToken;
    }

    private void sendTextMessage(Long chatId, String text) {
        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendTypingAction(Long chatId) {
        SendChatAction action = new SendChatAction();
        action.setChatId(String.valueOf(chatId));
        action.setAction(ActionType.TYPING);
        try {
            execute(action);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}