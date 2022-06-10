Simple console drawing
############################################################################################################
USAGE

Command 		Description
C w h           Create a new canvas of width w and height h.
L x1 y1 x2 y2   Create a new line of 'x' from (x1,y1) to (x2,y2). Only support 
                horizontal or vertical lines.
R x1 y1 x2 y2   Create a new rectangle, (x1,y1) is upper left corner & (x2,y2) is 
                lower right corner.
B x y c         Fill the entire area around (x,y) with "colour" c.
                Same as that of the "bucket fill" tool in paint programs.
Q               Quit.


example

enter command: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------

enter command: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------

enter command: L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------

enter command: R 14 1 18 3
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------

enter command: B 10 3 o
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------
#############################################################################################################

Build and Run Commands

  Jar building mvn package
  Run Test mvn test
  Run application java -jar simple-console-drawing-0.0.1-SNAPSHOT.jar
  
#############################################################################################################

External Libraries

1. Mockito -> to do some complex tests
2. Jupitor Junit 5 -> to do Junit test

Build Tool : Maven


#############################################################################################################

Design

1. Factory design pattern used to create the object of Command
2. Builder pattern used simplify factory object creation in CommandFactory class
   a. CommandFactory class also implements PropertyChangeListener so it will act as observer, 
     it's canvas property will be updated every time when a new canvas created
3. Command interface have a method execute() which will do perform the corresponding 
   execution according different implementation.
4. Canvas class can take any Entity class
    a. Entity class draw method use callback functional interface, so 
    Entity dose't need filling object instead it can pass filling data canvas.
    This gives the flexibility to the canvas, it can draw items on their way   
    
    
########################################################################################################

UML diagram add in the project folder     
File name : simple_console_drawing_uml.jpg
