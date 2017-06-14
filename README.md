# WarCardGame
Step 1: Edit application.properties under src/main/resources<br />
For Example:<br />
suits=diamond, heart<br />
ranks=ace, king, queen, jack, 10<br />
valuesOfRanks=100, 13, 12, 11, 10<br />
players=Jack, Tom, Hellen<br />
numFaceDownCardsIfDraw=1<br />

Step 2: Run App.java which has the main method.<br />
Example result:<br />
[INFO] 00:33:57 06-14 Config:75 - players is set to Jack, Tom, Hellen<br />
[INFO] 00:33:57 06-14 Config:75 - suits is set to diamond, heart<br />
[INFO] 00:33:57 06-14 Config:75 - ranks is set to ace, king, queen, jack, 10<br />
[INFO] 00:33:57 06-14 Config:75 - valuesOfRanks is set to 100, 13, 12, 11, 10<br />
[INFO] 00:33:57 06-14 Config:75 - numFaceDownCardsIfDraw is set to 1<br />
[INFO] 00:33:57 06-14 WarImpl:41 - cards.size() = 10<br />
[INFO] 00:33:57 06-14 WarImpl:48 - numCardsInEachPlayer = 3<br />
Jack has 3 cards: [jack-diamond, queen-heart, 10-diamond]<br />
Tom has 3 cards: [jack-heart, ace-heart, queen-diamond]<br />
Hellen has 3 cards: [10-heart, ace-diamond, king-heart]<br />

At game 1<br />
Jack has 5 cards: [queen-heart, 10-diamond, jack-diamond, jack-heart, 10-heart]<br />
Tom has 2 cards: [ace-heart, queen-diamond]<br />
Hellen has 2 cards: [ace-diamond, king-heart]<br />

At game 2<br />
Jack has 4 cards: [10-diamond, jack-diamond, jack-heart, 10-heart]<br />
Tom has 4 cards: [queen-diamond, ace-heart, ace-diamond, queen-heart]<br />
Hellen has 1 cards: [king-heart]<br />

At game 3<br />
Jack has 3 cards: [jack-diamond, jack-heart, 10-heart]<br />
Tom has 3 cards: [ace-heart, ace-diamond, queen-heart]<br />
Hellen has 3 cards: [king-heart, queen-diamond, 10-diamond]<br />

At game 4<br />
Jack has 2 cards: [jack-heart, 10-heart]<br />
Tom has 5 cards: [ace-diamond, queen-heart, ace-heart, king-heart, jack-diamond]<br />
Hellen has 2 cards: [queen-diamond, 10-diamond]<br />

At game 5<br />
Jack has 1 cards: [10-heart]<br />
Tom has 7 cards: [queen-heart, ace-heart, king-heart, jack-diamond, ace-diamond, queen-diamond, jack-heart]<br />
Hellen has 1 cards: [10-diamond]<br />

At game 6<br />
hasAFinalWinner = true. players = [Jack has 0 cards: [], Tom has 9 cards: [ace-heart, king-heart, jack-diamond, ace-diamond, queen-diamond,<br /> jack-heart, queen-heart, 10-heart, 10-diamond], Hellen has 0 cards: []]<br />
Jack has 0 cards: []<br />
Tom has 9 cards: [ace-heart, king-heart, jack-diamond, ace-diamond, queen-diamond, jack-heart, queen-heart, 10-heart, 10-diamond]<br />
Hellen has 0 cards: []<br />
