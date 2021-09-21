# java-spring-mysql-telegram-linkbot

Link Database Telegram Java Spring Bot

Setup: 

1. Create MySQL database: primari key ID, Link, Rate. 
2. Deploy code.
3. Register new Telegram bot (BotFather), add cred to env.
4. Run

Bot has command:

/help - send information about bot
/fun - send one link from your database
/top - sent top 10 rated links

Each links has rating - when you receive link from bot, message has inline button: +1 / -1. 
By clicking them you will vote for the article. Sorry, i have not completed the ability to vote only once.
