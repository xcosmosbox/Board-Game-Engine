### 3.2 Explain quality attributes. Why are they relevant and important to your game?

#### 3.2.1 Maintainability

We have adopted the development method combining MVC architecture and full interface development. This makes the game system modular. At the same time, we write corresponding abstraction layer interfaces for different functional modules. After the interface is designed, the entire game system will be highly abstract. The implementation details of each different functional module will be completely hidden. This allows the coupling of the entire game system to be fully reduced.

When we reduce the degree of coupling to this level, the superiority of maintainability will be highlighted. Because the specific details of each class are hidden. Each class (including its concrete concrete class) will only communicate through the methods provided by the interface. This means that a unified constraint will be formed between each class. Problems with any one module will be caught quickly.

For example, when I modify the `initCoordinatePositionMapping()` function in the Board Controller. I accidentally put

```java
int x = Integer. parseInt(coordinateArray[0].trim());
int y = Integer. parseInt(coordinateArray[1].trim());
```

to the wrong writing

```java
int x = Integer. parseInt(coordinateArray[1].trim());
int y = Integer. parseInt(coordinateArray[0].trim());
```

This will reverse the Coordinate's x and y mapping.

Then when the user enters the coordinates (3,6) on the chessboard, the position of the chess piece will become (6,3).
Since the only class that reads board mapping files in the entire game is BoardModel, we can quickly determine the location of the problem and fix it. This is the benefit that MVC brings us. MVC makes each class have a smaller scope of responsibility. After reducing the granularity of each function, the scope of responsibility of each class is limited. Problems always appear in a small area, and we can quickly locate and solve them.

<img src="https://raw.githubusercontent.com/xavierfrankland/PicRepo/master/uPic/AMA4XPCzokywimage-20230518014657014.png" alt="image-20230518014657014" style="zoom:33%;" />

<img src="https://raw.githubusercontent.com/xavierfrankland/PicRepo/master/uPic/ItNnr6image-20230518014829734.png" alt="image-20230518014829734" style="zoom:33%;" />

This is the excellent performance of maintainability after the combination of MVC and full interface development.

Maintainability is very relevant and important to our project. If our project has a high maintainability, we will always be able to quickly discover and solve problems in the subsequent development process. Reduced code development and maintenance costs. At the same time, maintainability is also an important indicator for evaluating code quality.[1] So maintainability is very important in our project.

#### 3.2.2 Usability

Our projects excel in usability. This is because we have designed an intuitive interface and made full use of prompts and interaction methods that conform to operating habits. 

In order to more convincingly explain how our project has excellent performance in usability, I will use the five indicators summarized by ISO and Jakob Nielsen for evaluation. [2] [3]

- **Learnability**: User can easily start his first play. This is because the entire project is output based on Terminal. This is a simple and straightforward output method.  We provide rich and satisfying hints. We put numbers before each option. Since the coordinate system on the chessboard is the same as the coordinate system used in real life, users can easily understand the coordinate system of the chessboard and perform input operations. 

- **Efficiency**: Users will be able to operate the game quickly. There will be no more than 3 options in the different menus of the game, and each option has an independent function, so the user only needs to enter a number to enter the function of the corresponding option. All options in the game are manipulated with numbers. Therefore, the player always performs fast operations in the number area of the keyboard.

- **Memorability**: The difficulty for users to re-establish proficiency is very low. Because the operation of the whole game is extremely simple. And because we have a lot of text prompts as mentioned above, users can quickly recall past operations.

- **Errors**: Users can easily recover from errors and continue the game seamlessly. Our project has a perfect error catching mechanism. When the user enters coordinates that do not exist or enters an incorrect format. Our game system can always detect and catch errors at the first time and will prompt the user to guide the user to make the correct input. At the same time, since we manually catch user errors, the program will not crash due to wrong sending. Therefore, when the user sends an error, the program will not crash and exit, but will prompt the user to enter again after returning to the previous input state after the correct input.

  <img src="https://raw.githubusercontent.com/xavierfrankland/PicRepo/master/uPic/VkahIAimage-20230518022802876.png" alt="image-20230518022802876" style="zoom:33%;" />    <img src="https://raw.githubusercontent.com/xavierfrankland/PicRepo/master/uPic/rhEWgjimage-20230518022947726.png" alt="image-20230518022947726" style="zoom:33%;" /> 

- **Satisfaction**: The whole game is very enjoyable. We have done a lot of design in the game's start and end screens. And when the mill is formed, we will use eye-catching prompts to tell the player that a "kill" can be performed. This will increase the entertainment of the game and make the user more enjoyable.

To sum up, our program has extremely high usability.

#### 3.2.3 Scalability

When we use the MVC architecture pattern, the coupling between modules is reduced, so that different modules can be developed separately. At the same time, because we use full interface development, dependency inversion and interface isolation are realized. The above reasons provide good scalability for our project.

Specifically, although our View is only output on the Terminal at this stage, we can expand the entire project to have a richer GUI mode. We just need to make a new class implement any of the other visualization techniques. This class needs to implement the View interface. Then, we only need to replace the products in the corresponding factory class.

Similarly, even on the Terminal, we can also expand. For example, now we are a 9MM game, we can expand it into a twelve men's morris game [4] [5], but we don't need to modify any source code, we only need to modify the configuration file of the required resources. This is because in the design process of the entire interface, we have carried out a higher level of abstraction, which makes the interface of the entire game system not only applicable to 9MM, but also applicable to many board games. This design perspective provides a strong guarantee for scalability.

<img src="https://raw.githubusercontent.com/xavierfrankland/PicRepo/master/uPic/X70X0UKpPNa4Snipaste_2023-05-18_02-42-10.png" alt="Snipaste_2023-05-18_02-42-10" style="zoom:50%;" />

Since the source code does not need to be modified, we also provide excellent scalability while satisfying the open-closed principle.

Scalability is extremely important in this project. Because we need to expand the basic functions in the next development. In this process, excellent scalability allows us not to have too much involvement with other classes in the expansion of functions. At the same time, we can easily expand without modifying other parts of the original system.