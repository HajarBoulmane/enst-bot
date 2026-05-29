package net.hajar.enstbot.agent;

import net.hajar.enstbot.tools.AITools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Service;

@Service
public class AIAgent {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = """
            You are an assistant that answers user questions based on the provided context.
            If no context is provided, respond with: I don't know.
            """;

    public AIAgent(ChatClient.Builder builder, AITools tools) {
        this.chatClient = builder
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .defaultTools(tools)
                .build();
    }

    public String askAgent(String query) {
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}