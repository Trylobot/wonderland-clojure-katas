(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= 
      ; given input + actual output
      (play-round
        [ [[2 :spade] [:ace :heart]]
          [[2 :club] [:ace :diamond]] ] ) 
      ; expected output
      [ [[:ace :diamond] [:ace :heart] [2 :spade]]
        [[2 :club]] ] )) )
  (testing "queens are higher rank than jacks"
    (is (compare-cards [:queen :club] [:jack :club])))
  (testing "kings are higher rank than queens"
    (is (compare-cards [:king :club] [:queen :club])))
  (testing "aces are higher rank than kings"
    (is (compare-cards [:ace :club] [:king :club])))
  (testing "if the ranks are equal, clubs beat spades"
    (is (compare-cards [:queen :club] [:queen :spade])))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (compare-cards [:queen :diamond] [:queen :club])))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (compare-cards [:queen :heart] [:queen :diamond]))) )

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= 
      ; given input + actual output
      (play-game
        [ [[2 :spade] [:ace :heart]]
          [[2 :club] [:ace :diamond]] ] ) 
      ; expected output
      {:winner "Player 1",
       :cards [[2 :spade] [:ace :diamond] [2 :club] [:ace :heart]]} ))) )
;
;     start
;
;     :p1[ [2 :spade] [:ace :heart]  ]
;     :p2[ [2 :club] [:ace :diamond] ]
;
;     :p1[ [:ace :diamond] [:ace :heart] [2 :spade] ]
;     :p2[                               [2 :club]  ]
;
;     :p1[ [:ace :diamond] [:ace :heart] ]
;     :p2[ [2 :spade]      [2 :club]     ]
;
;     :p1[ [2 :club] [:ace :heart] [:ace :diamond] ]
;     :p2[                         [2 :spade]      ]
;
;     :p1[ [2 :spade] [:ace :diamond] [2 :club] [:ace :heart] ]   <-- winner
;     :p2[]
;

