
# ENST-Bot 

A Telegram bot powered by Large Language Models (LLM) for intelligent conversations and assistance.

## Tech Stack
- Java
- Spring Boot
- Telegram Bot API
- LLM Integration (LLama)
- Maven

##  Project Structure

``` bash
enst-bot/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/
│   │   │       ├── agent/
│   │   │       ├── telegram/
│   │   │       ├── tools/
│   │   │       └── web/
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
└── README.md
```

###  Features
-  Telegram bot integration
-  LLM-powered conversations


###  Prerequisites
- Java 11 or higher
- Maven
- Telegram Bot Token (from [@BotFather](https://t.me/BotFather))
- LLM API Key (OpenAI/Gemini/llama)

###  Installation & Setup

### 1. Clone the repository
```bash
git clone https://github.com/HajarBoulmane/enst-bot.git
cd enst-bot
```

### 2. Configure the bot
Create or update `src/main/resources/application.properties`:


### Telegram Bot Configuration
```
telegram.bot.token=YOUR_BOT_TOKEN_FROM_BOTFATHER
telegram.bot.username=YOUR_BOT_USERNAME
```

### LLM Configuration 

```
llm.api.key=YOUR_OPENAI_API_KEY
llm.model=gpt-3.5-turbo
```

### Server Configuration
server.port=8080


#### 3. Get your credentials
- **Telegram Bot Token**: Message [@BotFather](https://t.me/BotFather) on Telegram
- **LLM API Key**: Sign up at [OpenAI](https://platform.openai.com/) or [Google AI Studio](https://makersuite.google.com/)

### 4. Run the bot
```bash
# Using Maven wrapper
./mvnw spring-boot:run
```

### 5. Start chatting
Search for your bot on Telegram and send a message!



##  Environment Variables (Alternative)

Instead of hardcoding credentials in `application.properties`, use environment variables:

```bash
export TELEGRAM_BOT_TOKEN="your_token_here"
export LLM_API_KEY="your_api_key_here"

./mvnw spring-boot:run
