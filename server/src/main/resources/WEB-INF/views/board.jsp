<!DOCTYPE html>
<html>
  <head>
    <title>Eclipse Virgo by Example: Game of Life</title>
    <link rel='stylesheet' href='/client/resources/style.css' />
    <link rel='stylesheet' href='/client/resources/octicons.css' />
  </head>
  <body>
    <header>
        <h1>Eclipse Virgo by Example: Game of Life</h1>
        <a href="https://www.eclipsecon.org/na2016/session/eclipse-virgo-example-game-life">
        <!--
            <i class="mega-octicon octicon-mark-github"></i>
        -->
        </a>
        <a href="http://www.theirondeveloper.com/"><i class="mega-octicon octicon-squirrel"></i></a>
        <p>This is a proof of concept build of Conway's game of life made using websockets.</p>
        <p>The server calculates and generates the next board, and the client draws whatever the server tells it to.</p>
    </header>

    <canvas id="gameOfLife" class="GoL_full" width="800" height="500"></canvas>

    <section id="broadcastedMessages" class="broadcastedMessages"></section>

    <script src="/client/resources/sockjs-0.3.min.js"></script>
    <script src="/client/resources/stomp.js"></script>
    <script src="/client/resources/jquery-1.11.0.min.js"></script>

    <script src="/client/resources/game-of-life.js"></script>
  </body>
</html>
