# WarCardGame
Step 1: Edit application.properties under src/main/resources
For Example:
suits=diamond, heart
ranks=ace, king, queen, jack, 10
valuesOfRanks=100, 13, 12, 11, 10
players=Jack, Tom, Hellen
numFaceDownCardsIfDraw=1

Step 2: Run App.java which has the main method.
Example result:
[INFO] 00:33:57 06-14 Config:75 - players is set to Jack, Tom, Hellen
[INFO] 00:33:57 06-14 Config:75 - suits is set to diamond, heart
[INFO] 00:33:57 06-14 Config:75 - ranks is set to ace, king, queen, jack, 10
[INFO] 00:33:57 06-14 Config:75 - valuesOfRanks is set to 100, 13, 12, 11, 10
[INFO] 00:33:57 06-14 Config:75 - numFaceDownCardsIfDraw is set to 1
[INFO] 00:33:57 06-14 WarImpl:41 - cards.size() = 10
[INFO] 00:33:57 06-14 WarImpl:48 - numCardsInEachPlayer = 3
Jack has 3 cards: [jack-diamond, queen-heart, 10-diamond]
Tom has 3 cards: [jack-heart, ace-heart, queen-diamond]
Hellen has 3 cards: [10-heart, ace-diamond, king-heart]

At game 1
Jack has 5 cards: [queen-heart, 10-diamond, jack-diamond, jack-heart, 10-heart]
Tom has 2 cards: [ace-heart, queen-diamond]
Hellen has 2 cards: [ace-diamond, king-heart]

At game 2
Jack has 4 cards: [10-diamond, jack-diamond, jack-heart, 10-heart]
Tom has 4 cards: [queen-diamond, ace-heart, ace-diamond, queen-heart]
Hellen has 1 cards: [king-heart]

At game 3
Jack has 3 cards: [jack-diamond, jack-heart, 10-heart]
Tom has 3 cards: [ace-heart, ace-diamond, queen-heart]
Hellen has 3 cards: [king-heart, queen-diamond, 10-diamond]
-------------------------------------------------------------------------
At game 4
Jack has 2 cards: [jack-heart, 10-heart]
Tom has 5 cards: [ace-diamond, queen-heart, ace-heart, king-heart, jack-diamond]
Hellen has 2 cards: [queen-diamond, 10-diamond]
-------------------------------------------------------------------------
At game 5
Jack has 1 cards: [10-heart]
Tom has 7 cards: [queen-heart, ace-heart, king-heart, jack-diamond, ace-diamond, queen-diamond, jack-heart]
Hellen has 1 cards: [10-diamond]
-------------------------------------------------------------------------
At game 6
hasAFinalWinner = true. players = [Jack has 0 cards: [], Tom has 9 cards: [ace-heart, king-heart, jack-diamond, ace-diamond, queen-diamond, jack-heart, queen-heart, 10-heart, 10-diamond], Hellen has 0 cards: []]
Jack has 0 cards: []
Tom has 9 cards: [ace-heart, king-heart, jack-diamond, ace-diamond, queen-diamond, jack-heart, queen-heart, 10-heart, 10-diamond]
Hellen has 0 cards: []
