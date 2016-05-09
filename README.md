# JumpAndShootProductions
Use for Capstone Class. Assignment was to make a game that was 2d platformer with some complex mechanics.

<p>One of our classes at nscc was based around one project that should incorporate previously learned technologies. Our group's idea was to develop a small game about a character who kidnaps princesses. This character has a bag that he uses to store objects such as things laying about or an enemy's projectile and can later use them as his own set of projectiles.</P>
The framework used was LIBGDX which has quite a few tools for use as well an already implemented game loop that sets the screen to 
~60 fps. All the graphics was drawn by us and the mechanics of the game were developped from scratch.
-Gravity: Works the same as "the real world" with a minor difference, constant force that pulls objects down but when they have something that they collide with underneath, they stop being pulled down.
-Collisions and hitboxs: Use rectangle shapes to be apart of any of the game objects and when rectangles overlap, there is a collision. To make sure that objects can't pass through walls or floors and such, I have smaller hitboxs just outside of the main on all four sides that detects whether or not there is a collision. **Since this project I have discovered other, possibly better, ways of doing collision detection.**
-Projectiles: Straight projectiles just travel along an axis, homing projectiles check distance x1, y1 and x2,y2 and with some pythagorean theorem, adjusts it's x and y velocitys based on where the target is so that it has a constant total velocity in all directions.
