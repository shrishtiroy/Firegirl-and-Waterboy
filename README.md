Level: Takes the level file and parses it into a grid of game objects which reflects the current state of the
    game. The array is changed everytime a player collects a gem and depending on what powerups are active.

    GameObj: The GameObj class has child classes: GroundObj, FireObj, WaterObj, SkyObj, WDoorObj, FDoorObj, and Player.
    A GameObj stores the position of each of these objects, the width and height of each object, the velocities, and the
    type of object. It also contains all of the collision functions which calculate if two objects are colliding and in
    what direction based on their current and projected positions.

    Player: The Player object has 2 child classes: Firegirl and Waterboy. The Player class itself has the additional fields
    gems, isJumping, maxPy, gameEnd, and endMsg which relates to the key functions canEnter, jump, applyGravity, and isGrounded.
    These methods dictate the players' movement and the game state depending on it such as if Firegirl walks into water
    the game will end and will print the end message. The Firegirl and Waterboy classes' functions are defined above.

    GameCourt: This class updates the game state every tick (35ms) based on the keys that are pressed
    and displays the updates such as the players' new positions and any changes in tiles
    (if gems are collected or fire becomes water). Additionally, it displays a popup window if
    the game ends and lets the user play again if they wish.

    RunFireGirlAndWaterboy: This class contains the main swing components of the games such as the Jlabels, the
    GameCourt, and the reset button. It initializes the GameCourt object and begins the game by starting the timer
    in the reset method.

    Game: The main class displays the instructions and runs the game.
