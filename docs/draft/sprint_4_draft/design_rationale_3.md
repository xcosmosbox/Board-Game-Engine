

### 4.3 Explain when your advanced feature was finalised (e.g. it is the same as we decided from sprint one; or we changed it in Sprint 3) and how easy/difficult it was to implement.

Our advanced features were confirmed in Sprint1 and no changes have been made since then. Implementation of our advanced features is extremely easy due to our good practices.

Based on the following good design practices/patterns, we can implement advanced features very easily.

#### 4.3.1 MVC Framework

Our project implements the overall MVC architecture. The MVC architecture helps us reduce the coupling between the various classes of the project. At the same time, the relationship between Controller and Model. We can divide the functions inside the class more carefully. For example, if we were not using the MVC architecture, we would only have a Player class. With the help of the MVC architecture, the Player will be split into two classes. One of these two classes implements the operation logic of the player, and the other implements the data storage logic of the player.

When we expand the advanced functions, the operation logic of the AI player remains unchanged, and it needs to complete the functions of the Human Player. This means that the Turn class does not have to be concerned with the concrete implementation of the class it calls. Because both the Human Player and the AI player will have the same function for Turn to call, and can always get the same return type. And Turn does not need to care about the difference between AI and Human in data storage.

At the same time, since the controller and model of the AI player are divided into two different classes. Therefore, when our team is developing, it can be carried out simultaneously. Because we have provided a unified protocol in advance through the interface.

This is where MVC comes in handy when we extend advanced functionality.

#### 4.3.2 Full Interface Development

Our project has achieved full interface development. Full interface development focuses the entire project on the interface layer. This frees us from the implementation details of the project. Instead, focus on the interface interaction of the project as a whole.

More specifically, both the Human Player Controller and the AI Player Controller implement the Player interface. This is the uniform constraint on which they implement their internal functions. At the same time, they will also implement the Pollable interface, which is the unified constraint for their interaction with Turn.

The full interface development method allows us to directly connect to the existing game system only by making the new class implement the provided interface when extending advanced functions. The scalability of the entire game has been greatly improved.

![playerAIPollable](https://raw.githubusercontent.com/xavierfrankland/PicRepo/master/uPic/STkN8na77hwcplayerAIPollable.png)