# text-based-java-game
A Java program that, given a specfically formatted text file, creates a basic text-based game using the contents of ```game.txt```.

__Structure of the ```game.txt``` file:__
* player: { your player name }
* map: { your starting room id, map grid length, map grid width, starting X-position, starting Y-position }   (**when noting the starting X/Y positions, for whatever position you intend to put, n, instead put 2n as the grid is twice as big to allow it to be spaced out**)
* room: { room id, room name, room description, is room hidden?, is this the final room? }
* exit: { exit id, exit name, exit description, id of the room the exit leads to, is this exit initially meant to be hidden?}
**-- OPTIONAL ROOM-SPECIFIC FEATURES: add these fields below before adding another ```room:``` line to add to the current room --**
* item: { item id, item name, item description, is item hidden? }
* container: { container id, container name, container description, is container hidden? }
* equipment: { equipment id, equipment name, equipment description, is equipment hidden?, what action does the equipment perform, id of container the equipment acts on, the id of item/equipment the container gives you, description of message displayed when equipment is used }
* person: { person id, person name, person description, is person hidden?, person dialogue when interacted with }
**--------------------------------**
* missions: { mission objective, mission points reward, is mission completed? (keep false), message displayed when mission is completed, id of item needed to complete mission }
* combination: {id of first item needed, id of second item needed, is new object item/equipment?, **(if new object is an item, fill in with same details as you did for item, and likewise for equipment)** }

**Downloading the files:**
* You will need to download all folders and files (except the ```README.md``` file) in order to run the game successfully.
* If you would like to add more commands, you will need to edit the ``TokenType.java``, ``Tokeniser.java``, ``Parser.java``, ``GameStateFileParser.java`` and the ``data.txt`` files accordingly.

 



