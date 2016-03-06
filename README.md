Important urls:

[http://localhost:8080/gol/springtime](Spring basic configuration check)

[http://localhost:8080/gol/ping](WebSocket ping endpoint) <Can "Upgrade" only to "WebSocket">

[http://localhost:8080/gol/stock](Spring stock examples endpoint)

[http://localhost:8080/gol/ws/info](WebSocket information)

    ::::json
    {"entropy":2113121810,"origins":["*:*"],"cookie_needed":true,"websocket":true}

[http://localhost:8080/gol/board](Game of Life board)

## Build game-of-life

### Dockerize the example

    ::::sh
    $ ./gradlew build :app:dockerize

### Run the dockerized example

    ::::sh
    $ docker run --rm -it -p 8080:8080 -p 2501:2501 --hostname gol --name gol game-of-life/app

Access the OSGi console of the running container with

    ::::ssh
    $ telnet localhost 2501
    OSGi>
