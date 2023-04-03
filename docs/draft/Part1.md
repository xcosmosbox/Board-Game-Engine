## Team Information

### Team Name and Photo 

- Team Name: **G**enerative **A**dversarial **N**etwork (short: GAN)
- Team Photo

![4561680343746_.pic_hd](https://raw.githubusercontent.com/xavierfrankland/PicRepo/master/uPic/pOVTneiZmC0N4561680343746_.pic_hd.jpg)



### Team Membership 

| Name                | Contact Detail                                       | Tech Stack                                           | Fun Fact                                                     |
| ------------------- | ---------------------------------------------------- | ---------------------------------------------------- | ------------------------------------------------------------ |
| Yifan(Charles) Wang | ID: 32459238<br />Email: ywan0687@student.monash.edu | python,java,MEAN Stack, IOS app dev, Android app dev | I like to play tachibana mahjong and joined the Monash Mahjong Club in order to be able to play tachibana mahjong offline. I like to play single player games, especially JRPG. I am addicted to the persona series, dragon quest series, octopath series and final fantasy series. Also love soul games, platinum achievement Elden Ring, is also waiting for Bloodborne to be played on PC. Is managing a thousands of people’s idol support club. :video_game: |
| Tianyi Liu          | ID: 27936619<br />Email: tliu135@student.monash.edu  | Android dev, MEAN stack, python, git                 | I acquired Platinum Trophies of Elden Ring before the beginning of S1 2023. I am a dog lover who has a 4-year-old golden retriever who needs to be walked every single day. I often swim as exercise and to keep myself healthy. I have not learnt java systematically before this semester and I am currently taking both FIT2099 and FIT3077. :bookmark: |
| Yuxiang Feng        | ID: 32431813<br />Email: yfen0056@student.monash.edu | iOS dev, MEAN Stack and Spring full stack            | I am an avid cyclist. I once spent 7 hours cycling around my hometown, with a total mileage of 50 kilometers. Incidentally, the average temperature that day was 38 degrees Celsius. I'm a game "collector" and I buy a lot of video games, but most of them I don't have time to play.:collision: |



### Team  Schedule

#### Weekly Meeting

Team meetings are to be held twice every week, one taking place on campus at 10:00 a.m. Wednesdays and the other online at 10:00 a.m. Saturdays.



#### Workload Distribution

Since we are adopting the agile approach, no tasks are delegated to a specific team member instead we are picking tasks based on our tech skills and everyone is mandatory for contributing to every single task at hand to a degree. A detailed wiki is documented in our group git-lab repository.



### 1.4 Team Tech Stack and Justification

Members of our team have rich experience in Java development, for example, Yifan and Tianyi have experience in Android development. Yuxiang has experience in Java Web development. At the same time, we are all students who love object-oriented technologies such as Java.

Therefore, our group plans to adopt a Java-based technology stack. For example, the Java language itself and the IDEA integrated development environment.

In terms of visualization technology, we initially intend to use Terminal as the main output interface. 

During development, we intend to use Maven as our project management tool. As a very large package management platform, Maven can easily manage project dependencies. We can add JUnit or Spring dependencies directly in Maven, and any dependencies we may add. If there are new requirements or external dependencies in the subsequent agile development, we can integrate these dependencies into the project through Maven at any time.

We plan to use JUnit as our testing tool. JUnit provides a very simple and fast unit testing method that allows us to quickly test the functions we have completed. Guarantee rapid development, rapid testing and rapid iteration of the entire project.

We plan to introduce Spring dependencies in Sprint3. This is because Spring provides an excellent IoC container that can help us further reduce the coupling between classes. IoC is a well known and common design pattern. Although the IoC design pattern is not one of GoF23, it has been adopted by many well-known companies and open source frameworks in the world so far. We want to improve the overall scalability of the project.

We plan to introduce MVC framework in Sprint3 or Sprint4. The games we design require the player to interact with the game, and the game requires visual output. Therefore, we believe that the MVC framework can further reduce the coupling between the view layer and the control layer. When our requirements expand, we only need to modify the control layer or view layer related to this requirement. The scalability of the project will be further expanded and the maintenance cost will be reduced.

To sum up, we set 3 different stage goals for our group's delivery. In the first stage, basic functions and Terminal-based GUI implementation are completed. The second stage is to introduce the Spring IoC container to further reduce the coupling between classes. In the third stage, the MVC framework is built to further reduce the overall coupling degree of the project and reduce maintenance costs. Separation of functions and responsibilities through the MVC framework improves the scalability of the project.

We are very much looking forward to the teacher's ability to teach us the GUI and MVC framework in the course. If he can lead us to implement a simple IoC framework, it will be of great help to us. In this way, we can use our own simple IoC container to complete the management of the class in the earlier delivery.

#### Tech Justification

The reasons for choosing Maven, Spring and MVC frameworks have been explained above and will not be repeated here. Here is an explanation of our team's choice of visualization form for the project's minimum viable product. Early in our group discussion, we listed three options, Terminal, JavaFx, and Web. These three technologies have their own advantages.

The advantage of Terminal is that what you see is what you get, we can just manage our visual screens, such as chessboards and chess pieces, in the early stage of development. This form of development is the lowest cost, because we hardly need any additional learning to complete the development. But the disadvantage is also obvious, that is, terminal-based development is very primitive. We cannot perform effective visualization, and this form is very strongly coupled with game logic. We have to embed this visual part in our function code. This means that once important functions are changed in later iterations, we need to refactor the terminal part, and the maintenance cost is very high.

The advantage of JavaFx is that it is a GUI development framework mainly promoted by Oracle in this era as a Java GUI development framework, and it is still being updated with more modern documents and development tools. The downside is that we need extra study. We cannot predict the cost of such learning, which may greatly delay our development progress, or even seriously miss the delivery date.

The advantage of the Web is that it can easily complete the separation of the front and back ends and the MVC framework. Web technology is the hottest technology at the moment, and we have a lot of learning resources to find. And Web technology itself is for more intuitive development of front-end pages, so it is very friendly to our game visualization development. Its disadvantage is similar to that of JavaFx, that is, we cannot predict whether its learning cost is acceptable to us.

To sum up, our initial choice is to use Terminal as our MVP (minimum viable product) visualization technology. But this does not mean that we abandoned JavaFx or Web. On the contrary, every member of our group is a student who loves technology and has the courage to innovate and challenge. We have planned to try to learn these two techniques in our spare time, and try to make a game board demo if possible. If all goes well, it is not ruled out that we will use one of these two technologies in MVP.